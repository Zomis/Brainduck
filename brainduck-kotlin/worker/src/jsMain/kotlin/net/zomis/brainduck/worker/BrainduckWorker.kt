package net.zomis.brainduck.worker

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.zomis.brainduck.Brainfuck
import net.zomis.brainduck.BrainfuckInput
import net.zomis.brainduck.BrainfuckOutput
import net.zomis.brainduck.BrainfuckProgram
import net.zomis.brainduck.analyze.BrainfuckRuntime
import net.zomis.brainduck.ast.Syntax
import net.zomis.brainduck.ast.SyntaxData
import net.zomis.brainduck.runner.BrainfuckListener
import net.zomis.brainduck.runner.Runner
import net.zomis.brainduck.runner.UntilEnd
import net.zomis.brainduck.view.WorkerEvent
import net.zomis.brainduck.view.WorkerRequest
import org.w3c.dom.Worker

fun main() {
    val scope = MainScope()
    var program: BrainfuckProgram = Brainfuck.code("").createProgram()
    val self: Worker = js("self")
    fun post(event: WorkerEvent) {
        self.postMessage(Json.encodeToString(event))
    }
    val listener: BrainfuckListener = object : BrainfuckListener {
        override fun before(syntax: Syntax, runtime: BrainfuckRuntime) {
        }

        override fun after(syntax: Syntax, runtime: BrainfuckRuntime) {
            when (syntax.data) {
                is SyntaxData.Advanced -> TODO()
                is SyntaxData.ChangeValue -> post(WorkerEvent.MemoryChange(runtime.memory.currentIndex, runtime.memory.currentValue()))
                SyntaxData.Comment -> {}
                SyntaxData.EndWhile -> {}
                is SyntaxData.Move -> post(WorkerEvent.PointerUpdate(runtime.memory.currentIndex))
                SyntaxData.Read -> {}
                is SyntaxData.Root -> {
                    println("Will this happen? Root after")
                }
                is SyntaxData.WhileNotZero -> {}
                SyntaxData.Write -> {
                    post(WorkerEvent.Output(runtime.memory.currentValue()))
                }
            }
        }
    }
    self.onmessage = { e ->
        val request = Json.decodeFromString<WorkerRequest>(e.data.asDynamic())
        fun bfRun(runner: Runner) {
            program.run(runner, BrainfuckInput.NoInput, BrainfuckOutput.NoOutput, listOf(listener))
        }
        when (request) {
            is WorkerRequest.UpdateCode -> {
                program = Brainfuck.code(request.code).createProgram()
            }
            is WorkerRequest.RunUntilEnd -> bfRun(UntilEnd)
            is WorkerRequest.NavigateCode -> {}
            WorkerRequest.RunStep -> {
                bfRun(Brainfuck.Run.stepSyntax)
            }
        }
    }
}

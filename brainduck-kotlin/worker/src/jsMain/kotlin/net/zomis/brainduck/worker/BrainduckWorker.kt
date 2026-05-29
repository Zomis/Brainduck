package net.zomis.brainduck.worker

import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
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
import kotlin.js.Promise
import kotlin.time.Duration.Companion.seconds

val self: Worker = js("self")

suspend fun yieldToWorkerEventLoop() {
    println("yieldToWorkerEventLoop")
    delay(2.seconds)
    yield()
    Promise<Unit> { resolve, _ ->
        self.asDynamic().setTimeout({
            resolve(Unit)
        }, 0)
    }.await()
}

fun main() {
    val scope = MainScope()
    var program: BrainfuckProgram = Brainfuck.code("").createProgram()
    var job: Job? = null
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
        println("Worker got message $request job is $job")
        fun bfRun(runner: Runner) {
            job?.cancel()
            job = scope.launch {
                post(WorkerEvent.Running(running = true))
                try {
                    println("running with runner $runner")
                    program.run(runner, BrainfuckInput.NoInput, BrainfuckOutput.NoOutput, listOf(listener), ::yieldToWorkerEventLoop)
                } finally {
                    post(WorkerEvent.Running(running = false))
                }
                println("job completed")
            }
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
            is WorkerRequest.StopRunning -> {
                println("CANCEL JOB! $job")
                job?.cancel()
            }
        }
    }
}

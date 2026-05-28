package net.zomis.brainduck.worker

import org.w3c.dom.MessageEvent

object BrainduckWorker {

    fun doSomething(): Int {
        return 42
    }

}

fun main() {
    val self = js("self")
    println("Worker init!")
    self.onmessage = { e: MessageEvent ->
        println("Worker got message: " + e.data)
        if (e.data != null) println("Worker message is of type: " + e.data!!::class.simpleName)
        self.postMessage(e.data)
    }
}

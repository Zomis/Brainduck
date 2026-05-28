package net.zomis.brainduck.worker

import org.w3c.dom.MessageEvent
import org.w3c.dom.Worker

// TODO: Use generics and auto-serialization to handle incoming and outgoing messages
// TODO: Preferably also either use Flows or make it easily compatible with Flows
class MyWebWorker<T>(onMessage: MyWebWorker<T>.(MessageEvent) -> Unit) {
    init {
        TODO("This is right now just a slimmed down version of the Worker type")
    }

    private val self: Worker = js("self")

    fun postMessage(value: T) = self.postMessage(value)

    init {
        self.onmessage = {
            onMessage.invoke(this, it)
        }
    }

}

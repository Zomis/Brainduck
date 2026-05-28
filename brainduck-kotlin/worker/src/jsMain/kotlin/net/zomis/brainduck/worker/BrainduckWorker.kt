package net.zomis.brainduck.worker

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Worker

fun main() {
    val scope = MainScope()
    val self: Worker = js("self")
    self.onmessage = { e ->
        scope.launch {
            repeat(3) {
                self.postMessage("${e.data}$it")
                delay(1000)
            }
        }
    }
}

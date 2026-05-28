package net.zomis.brainduck.view

sealed interface WorkerEvent {

    data class PointerUpdate(val address: Int) : WorkerEvent
    data class MemoryChange(val address: Int, val value: Int) : WorkerEvent
    data class Output(val value: Int) : WorkerEvent
    data class CodePositionUpdate(val position: Int, val size: Int) : WorkerEvent

}

@file:OptIn(ExperimentalJsExport::class)
package net.zomis.brainduck.view

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
sealed interface WorkerRequest {

    @Serializable
    data object GetState : WorkerRequest
    @Serializable
    data class UpdateCode(val code: String) : WorkerRequest
    @Serializable
    data class NavigateCode(val pos: Int) : WorkerRequest
    @Serializable
    data object RunUntilEnd : WorkerRequest
    @Serializable
    data object RunStep : WorkerRequest
    @Serializable
    data object StopRunning : WorkerRequest
}

@Serializable
sealed interface WorkerEvent {
    // TODO: ProgramRunning event (boolean) to indicate if it's running or not
    @Serializable
    @SerialName("pointer")
    data class PointerUpdate(
        @JsName("address")
        val address: Int
    ) : WorkerEvent

    @Serializable
    @SerialName("memory")
    data class MemoryChange(
        @JsName("address")
        val address: Int,
        @JsName("value")
        val value: Int,
    ) : WorkerEvent

    @Serializable
    @SerialName("output")
    data class Output(
        @JsName("value")
        val value: Int
    ) : WorkerEvent

    @Serializable
    @SerialName("codePos")
    data class CodePositionUpdate(
        val position: Int,
        val size: Int,
    ) : WorkerEvent

    @Serializable
    @SerialName("runStatus")
    data class Running(
        @JsName("running")
        val running: Boolean
    ) : WorkerEvent

    @Serializable
    @SerialName("state")
    class State(
        @JsName("memory")
        val memory: IntArray,
        @JsName("pointer")
        val pointer: Int,
        @JsName("codeIndex")
        val codeIndex: Int,
        @JsName("codeSize")
        val codeSize: Int,
    ) : WorkerEvent
}

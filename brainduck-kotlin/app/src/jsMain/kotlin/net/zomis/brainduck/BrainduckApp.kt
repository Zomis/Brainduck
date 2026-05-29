package net.zomis.brainduck

import kotlinx.serialization.json.Json
import net.zomis.brainduck.view.WorkerEvent
import net.zomis.brainduck.view.WorkerRequest

@OptIn(ExperimentalJsExport::class)
@JsExport
object BrainduckApp {
    private val json = Json {
        classDiscriminator = "type"
    }

    private fun serialize(request: WorkerRequest): String =
        json.encodeToString(WorkerRequest.serializer(), request)

    fun parseWorkerEvent(data: String) = json.decodeFromString(WorkerEvent.serializer(), data)

    fun codeUpdate(code: String): String = serialize(WorkerRequest.UpdateCode(code))
    fun runUntilEnd(): String = serialize(WorkerRequest.RunUntilEnd)
    fun runStep(): String = serialize(WorkerRequest.RunStep)

}

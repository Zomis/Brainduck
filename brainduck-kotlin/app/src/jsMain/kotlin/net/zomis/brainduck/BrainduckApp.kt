package net.zomis.brainduck

@OptIn(ExperimentalJsExport::class)
@JsExport
object BrainduckApp {

    init {
        println("Brainduck App init")
    }

    fun run() {
        println("Brainduck app run!")
    }

}

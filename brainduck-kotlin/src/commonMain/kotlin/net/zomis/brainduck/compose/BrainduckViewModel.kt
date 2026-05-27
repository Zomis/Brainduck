package net.zomis.brainduck.compose

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class BrainduckViewModel {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
//    val input: MutableState<String> = mutableStateOf("")
//    val output: State<String> = mutableStateOf("")
    val memory = (0..65535).map { MemoryCellImpl(it) }

//    lateinit var font: FontFamily
//
//    fun init(font: FontFamily) {
//        this.font = font
//        editorState.toggleSpanStyle(SpanStyle(fontFamily = font))
//        editorState.setText("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+.+..")
//    }
}

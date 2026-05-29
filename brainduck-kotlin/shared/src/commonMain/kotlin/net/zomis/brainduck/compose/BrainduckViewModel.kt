package net.zomis.brainduck.compose

class BrainduckViewModel {
//    val input: MutableState<String> = mutableStateOf("")
//    val output: State<String> = mutableStateOf("")
    val memory = (0..65535).map { MemoryCellImpl(it) }

}

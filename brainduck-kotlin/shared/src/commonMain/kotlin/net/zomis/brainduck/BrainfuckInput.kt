package net.zomis.brainduck

fun interface BrainfuckInput {
    object NoInput : BrainfuckInput {
        override suspend fun read(): Int = error("NoInput has no input")
    }

    class StringInput(private val text: String) : BrainfuckInput {
        private var index = 0
        override suspend fun read(): Int = if (index < text.length) text[index++].code else 0
    }

    suspend fun read(): Int
}

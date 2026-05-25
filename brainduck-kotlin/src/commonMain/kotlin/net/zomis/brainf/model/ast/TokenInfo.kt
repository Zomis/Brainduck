package net.zomis.brainf.model.ast;

data class TokenInfo(
    var position: Int,
    var length: Int,
    var line: Int,
    val column: Int,
    val file: String,
) {
    // TODO: Old code did not copy column - was that a bug or a feature?

    fun reset() {
        position += length;
        length = 1;
    }

    override fun toString(): String {
        return "TokenInfo{" +
                "position=" + position +
                ", length=" + length +
                ", line=" + line +
                ", column=" + column +
                ", file='" + file + '\'' +
                '}';
    }

    companion object {
        fun createNew(): TokenInfo = TokenInfo(
            position = 0,
            length = 0,
            line = 0,
            column = 0,
            file = "",
        )
    }
}

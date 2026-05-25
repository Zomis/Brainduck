package net.zomis.brainf.model.ast;

open class Token(tokenInfo: TokenInfo) {
    private val _info = tokenInfo.copy()
    val info: TokenInfo get() = _info.copy()

    override fun toString(): String {
        return "Token{" +
                "info=" + info +
                '}';
    }

}

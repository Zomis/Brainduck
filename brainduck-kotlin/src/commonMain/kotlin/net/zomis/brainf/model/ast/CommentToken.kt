package net.zomis.brainf.model.ast;

class CommentToken(tokenInfo: TokenInfo, val text: String) : Token(tokenInfo) {

    override fun toString(): String {
        return "CommentToken{" +
                "text='" + text + '\'' +
                "} " + super.toString();
    }

}

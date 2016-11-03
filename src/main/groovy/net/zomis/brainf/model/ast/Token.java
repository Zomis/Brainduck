package net.zomis.brainf.model.ast;

public class Token {

    private TokenInfo info;

    public Token(TokenInfo tokenInfo) {
        this.info = new TokenInfo(tokenInfo);
    }

    public TokenInfo getInfo() {
        return new TokenInfo(info);
    }

    @Override
    public String toString() {
        return "Token{" +
                "info=" + info +
                '}';
    }

}

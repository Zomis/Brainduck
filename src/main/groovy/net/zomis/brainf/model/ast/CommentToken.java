package net.zomis.brainf.model.ast;

public class CommentToken extends Token {

    public final String text;

    public CommentToken(TokenInfo tokenInfo, String text) {
        super(tokenInfo);
        this.text = text;
    }

    @Override
    public String toString() {
        return "CommentToken{" +
                "text='" + text + '\'' +
                "} " + super.toString();
    }

}

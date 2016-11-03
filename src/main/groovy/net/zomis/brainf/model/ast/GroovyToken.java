package net.zomis.brainf.model.ast;

public class GroovyToken extends Token {

    public final String code;

    public GroovyToken(TokenInfo tokenInfo, String groovyCode) {
        super(tokenInfo);
        this.code = groovyCode;
    }

    @Override
    public String toString() {
        return "GroovyToken{" +
                "code='" + code + '\'' +
                "} " + super.toString();
    }

}

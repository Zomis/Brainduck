package net.zomis.brainf.model.ast;

public class TokenInfo {

    int position;
    int length;
    int line;
    int column;
    String file;

    public TokenInfo(TokenInfo tokenInfo) {
        this.position = tokenInfo.position;
        this.length = tokenInfo.length;
        this.line = tokenInfo.line;
        this.file = tokenInfo.file;
    }

    public TokenInfo() {

    }

    public void reset() {
        position += length;
        length = 1;
    }

    public int getLength() {
        return length;
    }

    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }

    public String getFile() {
        return file;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "position=" + position +
                ", length=" + length +
                ", line=" + line +
                ", column=" + column +
                ", file='" + file + '\'' +
                '}';
    }
}

package net.zomis.brainf.model.ast;

import net.zomis.brainf.model.BrainfuckCommand;
import net.zomis.brainf.model.classic.BrainFCommand;

public class BFToken extends Token {

    public final BrainfuckCommand command;

    public BFToken(TokenInfo tokenInfo, BrainFCommand bfCommand) {
        super(tokenInfo);
        this.command = bfCommand;
    }

    @Override
    public String toString() {
        return "BFToken{" +
                "command=" + command +
                "} " + super.toString();
    }

}

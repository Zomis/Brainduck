package net.zomis.brainf.model.ast;

import net.zomis.brainf.model.classic.BrainFCommand;

class BFToken(
    tokenInfo: TokenInfo,
    val command: BrainFCommand,
) : Token(tokenInfo) {

    override fun toString(): String {
        return "BFToken{" +
                "command=" + command +
                "} " + super.toString();
    }

}

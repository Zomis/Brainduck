package net.zomis.brainf.model.input;

import net.zomis.brainf.model.BrainfuckException;
import net.zomis.brainf.model.BrainfuckInput;

public class NoInput implements BrainfuckInput {

    @Override
    public int read() throws BrainfuckException {
        throw new BrainfuckException("Not implemented");
    }

}

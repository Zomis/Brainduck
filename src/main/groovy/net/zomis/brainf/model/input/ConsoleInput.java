package net.zomis.brainf.model.input;

import net.zomis.brainf.model.BrainfuckException;
import net.zomis.brainf.model.BrainfuckInput;

import java.io.IOException;

public class ConsoleInput implements BrainfuckInput {

    @Override
    public int read() throws BrainfuckException {
        try {
            return System.in.read();
        } catch (IOException e) {
            throw new BrainfuckException(e);
        }
    }

}

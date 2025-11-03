package net.zomis.brainf.model.input

import net.zomis.brainf.model.BrainfuckException
import net.zomis.brainf.model.BrainfuckInput

class FixedInput implements BrainfuckInput {

    final String text
    private int pos

    public FixedInput(String input) {
        this.text = input;
    }

    @Override
    public int read() throws BrainfuckException {
        return text.charAt(pos++);
    }

}

package net.zomis.brainf.model.input;

import net.zomis.brainf.model.BrainfuckOutput;

public class ConsoleOutput implements BrainfuckOutput {
    @Override
    public void write(char value) {
        System.out.print(value);
    }
}

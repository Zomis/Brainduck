package net.zomis.brainf.model

import net.zomis.brainf.model.run.RunStrategy

class BrainfuckRunner {

    BrainfuckMemory memory
    BrainfuckCode code = new BrainfuckCode()
    final InputStream input;
    private final StringBuilder output = new StringBuilder();
    private BrainfuckListener listener = new BrainfuckListener() {
        @Override
        void beforePerform(BrainfuckRunner runner, BrainfuckCommand command) {}

        @Override
        void afterPerform(BrainfuckRunner runner, BrainfuckCommand command) {}
    }

    BrainfuckRunner(int memorySize, String code, InputStream input) {
        this(memorySize, input);
        this.code.addCommands(code);
    }

    BrainfuckRunner(int memorySize, InputStream input) {
        memory = new BrainfuckMemory(memorySize);
        this.input = input;
    }

    String getOutput() {
        return output.toString();
    }

    void run() {
        while (code.hasMoreCommands()) {
            step();
        }
    }

    BrainfuckCommand step() {
        BrainfuckCommand command = code.getNextCommand();
        if (command == null) {
            return null
        }
        perform(command);
        code.commandIndex++;
        return command;
    }

    void reset() {
        memory.reset();
        code.resetIndex();
        output.setLength(0);
    }

    void perform(BrainfuckCommand command) {
        listener.beforePerform(this, command)
        if (command) {
            command.perform(this)
        }
        listener.afterPerform(this, command)
    }

    void setListener(BrainfuckListener listener) {
        this.listener = listener
    }

    int run(RunStrategy strategy) {
        int count = 0;
        boolean repeat = strategy.start(this);
        while (repeat) {
            count++
            repeat = strategy.next(this)
        }
        count
    }

    void appendOutput(char write) {
        output.append(write);
    }

}

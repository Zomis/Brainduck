package net.zomis.brainf.model

import net.zomis.brainf.model.run.RunStrategy

class BrainfuckRunner {

    final BrainfuckMemory memory
    final BrainfuckCode code
    final InputStream input;
    private final StringBuilder output
    private BrainfuckListener listener = new BrainfuckListener() {
        @Override
        void beforePerform(BrainfuckRunner runner, BrainfuckCommand command) {}

        @Override
        void afterPerform(BrainfuckRunner runner, BrainfuckCommand command) {}
    }

    BrainfuckRunner(BrainfuckMemory memory, BrainfuckCode code, InputStream input, StringBuilder output) {
        this.memory = memory
        this.code = code
        this.input = input
        this.output = output
    }

    BrainfuckRunner(int memorySize, String code, InputStream input) {
        this(memorySize, input);
        this.code.addCommands(code);
    }

    BrainfuckRunner(int memorySize, InputStream input) {
        memory = new BrainfuckMemory(memorySize);
        this.input = input;
        this.output = new StringBuilder()
        this.code = new BrainfuckCode()
    }

    String getOutput() {
        return output.toString();
    }

    StringBuilder getOutputBuilder() {
        return output
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
        int index = code.commandIndex
        perform(command);
        code.commandIndex += code.source.getCommandLength(index);
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

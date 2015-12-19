package net.zomis.brainf.model

import net.zomis.brainf.model.input.StringBuilderOutput
import net.zomis.brainf.model.run.RunStrategy

class BrainfuckRunner {

    final BrainfuckMemory memory
    final BrainfuckCode code
    final BrainfuckInput input;
    private final BrainfuckOutput output
    private BrainfuckListener listener = new BrainfuckListener() {
        @Override
        void beforePerform(BrainfuckRunner runner, BrainfuckCommand command) {}

        @Override
        void afterPerform(BrainfuckRunner runner, BrainfuckCommand command) {}
    }

    BrainfuckRunner(BrainfuckMemory memory, BrainfuckCode code, BrainfuckInput input, BrainfuckOutput output) {
        this.memory = memory
        this.code = code
        this.input = input
        this.output = output
    }

    @Deprecated
    String getOutput() {
        return output.toString();
    }

    BrainfuckOutput getOutputBuilder() {
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
        int commandLength = code.source.getCommandLength(index)
        code.commandIndex += commandLength
        return command;
    }

    void reset() {
        memory.reset();
        code.resetIndex();
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

    void appendOutput(char value) {
        output.write(value);
    }

}

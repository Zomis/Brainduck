package net.zomis.brainf.model

import net.zomis.brainf.model.run.RunStrategy

class BrainfuckRunner {

    BrainfuckMemory memory
    BrainfuckCode code = new BrainfuckCode()
    private final InputStream input;
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
        switch (command) {
            case BrainFCommand.ADD:
                memory.changeMemory(1);
                break;
            case BrainFCommand.END_WHILE:
                if (memory.getMemory() != 0) {
                    code.gotoMatching(BrainFCommand.WHILE, BrainFCommand.END_WHILE, -1);
                }
                break;
            case BrainFCommand.NEXT:
                memory.memoryIndex++;
                memory.memoryIndexWraparound();
                break;
            case BrainFCommand.PREVIOUS:
                memory.memoryIndex--;
                memory.memoryIndexWraparound();
                break;
            case BrainFCommand.READ:
                int value = input.read();
                memory.setMemory(value);
                break;
            case BrainFCommand.SUBTRACT:
                memory.changeMemory(-1);
                break;
            case BrainFCommand.WHILE:
                if (memory.getMemory() == 0) {
                    code.gotoMatching(BrainFCommand.END_WHILE, BrainFCommand.WHILE, 1);
                }
                break;
            case BrainFCommand.WRITE:
                char write = (char) memory.getMemory();
                output.append(write);
                break;
            case SpecialCommand.class:
                SpecialCommand specialCommand = (SpecialCommand) command
                specialCommand.call(this)
                break
            case BrainFCommand.NONE:
            default:
                break;
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
}

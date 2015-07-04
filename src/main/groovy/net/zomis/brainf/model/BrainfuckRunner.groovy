package net.zomis.brainf.model

import net.zomis.brainf.BrainFCommand

import java.util.stream.Stream

class BrainfuckRunner {

    BrainfuckMemory memory
    BrainfuckCode code = new BrainfuckCode(null)
    private final Iterator<Byte> input;
    private final StringBuilder output = new StringBuilder();

    BrainfuckRunner(int memorySize, String code, Stream<Byte> input) {
        this(memorySize, input);
        this.code.addCommands(code);
    }

    BrainfuckRunner(int memorySize, Stream<Byte> input) {
        memory = new BrainfuckMemory(memorySize);
        this.input = input.iterator();
    }

    String getOutput() {
        return output.toString();
    }

    void runToEnd() {
        while (code.hasMoreCommands()) {
            step();
        }
    }

    BrainFCommand step() {
        if (!code.hasMoreCommands()) {
            return null;
        }
        BrainFCommand command = code.getNextCommand();
        perform(command);
        code.commandIndex++;
        return command;
    }

    void reset() {
        memory.reset();
        code.resetIndex();
        output.setLength(0);
    }

    void perform(BrainFCommand command) {
        switch (command) {
            case BrainFCommand.ADD:
                memory.changeMemory(1);
                break;
            case BrainFCommand.END_WHILE:
                if (memory.getMemory() != 0) {
                    code.findMatching(BrainFCommand.WHILE, BrainFCommand.END_WHILE, -1);
                }
                break;
            case BrainFCommand.NEXT:
                memory.memoryIndex++;
                memory.checkMemoryIndex();
                break;
            case BrainFCommand.PREVIOUS:
                memory.memoryIndex--;
                memory.checkMemoryIndex();
                break;
            case BrainFCommand.READ:
                byte value = input.next();
                memory.setMemory(value);
                break;
            case BrainFCommand.SUBTRACT:
                memory.changeMemory(-1);
                break;
            case BrainFCommand.WHILE:
                if (memory.getMemory() == 0) {
                    code.findMatching(BrainFCommand.END_WHILE, BrainFCommand.WHILE, 1);
                }
                break;
            case BrainFCommand.WRITE:
                char write = (char) memory.getMemory();
                output.append(write);
                break;
            case BrainFCommand.NONE:
            default:
                break;
        }
    }

}

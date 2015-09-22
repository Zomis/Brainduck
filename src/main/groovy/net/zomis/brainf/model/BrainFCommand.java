package net.zomis.brainf.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public enum BrainFCommand implements BrainfuckCommand {

	NONE((char) 0, r -> {}),
    NEXT('>', r -> {
        r.getMemory().setMemoryIndex(r.getMemory().getMemoryIndex() + 1);
        r.getMemory().memoryIndexWraparound();
    }),
    PREVIOUS('<', r -> {
        r.getMemory().setMemoryIndex(r.getMemory().getMemoryIndex() - 1);
        r.getMemory().memoryIndexWraparound();
    }),
    WRITE('.', r -> {
        char write = (char) r.getMemory().getMemory();
        r.appendOutput(write);
    }),
    READ(',', r -> {
        int value = 0;
        try {
            value = r.getInput().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        r.getMemory().setMemory(value);
    }),
    ADD('+', r -> r.getMemory().changeMemory(1)),
    SUBTRACT('-', r -> r.getMemory().changeMemory(-1)),
    WHILE('[', new BFLoop(1)),
    END_WHILE(']', new BFLoop(-1));

    private final char ch;
    private final BrainfuckCommand perform;
	private static final Map<Character, BrainFCommand> commands = new HashMap<>();
	
	static {
		for (BrainFCommand comm : BrainFCommand.values()) {
			commands.put(comm.ch, comm);
		}
	}

	private BrainFCommand(char ch, BrainfuckCommand perform) {
		this.ch = ch;
        this.perform = perform;
	}

    public boolean isLoop() {
        return this == WHILE || this == END_WHILE;
    }

    public static BrainFCommand getCommand(char ch) {
		return commands.getOrDefault(ch, NONE);
	}
	
    @Override
    public void perform(BrainfuckRunner runner) {
        this.perform.perform(runner);
    }

}

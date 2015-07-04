package net.zomis.brainf;

import java.util.HashMap;
import java.util.Map;

public enum BrainFCommand {

	NONE((char) 0),
    NEXT('>'),
    PREVIOUS('<'),
    WRITE('.'),
    READ(','),
    ADD('+'),
    SUBTRACT('-'),
    WHILE('['),
    END_WHILE(']');
	
	private final char ch;
	private static final Map<Character, BrainFCommand> commands = new HashMap<>();
	
	static {
		for (BrainFCommand comm : BrainFCommand.values()) {
			commands.put(comm.ch, comm);
		}
	}

	private BrainFCommand(char ch) {
		this.ch = ch;
	}
	
	public static BrainFCommand getCommand(char ch) {
		return commands.getOrDefault(ch, NONE);
	}
	
}

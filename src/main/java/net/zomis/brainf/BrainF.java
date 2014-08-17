package net.zomis.brainf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class BrainF {
	public static final int DEFAULT_MEMORY_SIZE = 0x1000;
	
	public BrainF(int memorySize, String code, Stream<Byte> in) {
		this(memorySize, in);
		addCommands(code);
	}
	
	public BrainF(int memorySize, Stream<Byte> in) {
		 memory = new byte[memorySize];
		 input = in.iterator();
	}

	private final List<BrainFCommand> commands = new ArrayList<>();
	private final Iterator<Byte> input;
	private int commandIndex;
	private final byte[] memory;
	private final StringBuilder output = new StringBuilder();

	private int memoryIndex;

	public void addCommands(String string) {
		string.chars().mapToObj(i -> BrainFCommand.getCommand((char) i)).filter(obj -> obj != null).forEachOrdered(commands::add);
	}

	private void changeMemory(int i) {
		checkMemoryIndex();
		memory[memoryIndex] += i;
	}

	private void findMatching(BrainFCommand decrease, BrainFCommand increase, int direction) {
		int matching = 1;
		while (true) {
			commandIndex += direction;
			BrainFCommand current = commands.get(commandIndex);

			if (current == decrease) {
				matching--;
				if (matching == 0) {
					break;
				}
			}
			else if (current == increase) {
				matching++;
			}
		}
	}

	public byte getMemory() {
		return memory[memoryIndex];
	}

	public void runToEnd() {
		while (commandIndex < commands.size()) {
			step();
		}
	}

	public BrainFCommand step() {
		if (commandIndex >= commands.size()) {
			return null;
		}
		BrainFCommand command = commands.get(commandIndex);
		perform(command);
		commandIndex++;
		return command;
	}

	public void setMemory(byte value) {
		memory[memoryIndex] = value;
	}

	public String getOutput() {
		return output.toString();
	}

	public int getMemoryIndex() {
		return memoryIndex;
	}
	
	public int getCommandIndex() {
		return commandIndex;
	}

	public void perform(BrainFCommand command) {
		switch (command) {
			case ADD:
				changeMemory(1);
				break;
			case END_WHILE:
				if (getMemory() != 0) {
					findMatching(BrainFCommand.WHILE, BrainFCommand.END_WHILE, -1);
				}
				break;
			case NEXT:
				memoryIndex++;
				checkMemoryIndex();
				break;
			case PREVIOUS:
				memoryIndex--;
				checkMemoryIndex();
				break;
			case READ:
				byte value = input.next();
				setMemory(value);
				break;
			case SUBSTRACT:
				changeMemory(-1);
				break;
			case WHILE:
				if (getMemory() == 0) {
					findMatching(BrainFCommand.END_WHILE, BrainFCommand.WHILE, 1);
				}
				break;
			case WRITE:
				char write = (char) getMemory();
				output.append(write);
				break;
			case NONE:
			default:
				break;
		}
	}

	private void checkMemoryIndex() {
		if (memoryIndex < 0) {
			memoryIndex += memory.length;
		}
		if (memoryIndex >= memory.length) {
			memoryIndex -= memory.length;
		}
	}

	public byte[] getMemoryArray(int fromIndex, int length) {
		return Arrays.copyOfRange(memory, fromIndex, fromIndex + length);
	}

	public void setCommands(String text) {
		commands.clear();
		addCommands(text);
	}

	public void reset() {
		Arrays.fill(memory, (byte) 0);
		commandIndex = 0;
		memoryIndex = 0;
		output.setLength(0);
	}

	public int getMemorySize() {
		return memory.length;
	}

	public byte getMemory(int index) {
		return memory[index];
	}

	public static BrainF createFromCodeAndInput(int memorySize, String code, String input) {
		return createFromCodeAndInput(memorySize, code, input.chars().mapToObj(i -> (byte) i ));
	}
	
	public static BrainF createFromCodeAndInput(int memorySize, String code, Stream<Byte> inputStream) {
		return new BrainF(DEFAULT_MEMORY_SIZE, code, inputStream);
	}
	
	public static BrainF createFromCode(String code) {
		return createFromCodeAndInput(DEFAULT_MEMORY_SIZE, code, "");
	}
	
	public static BrainF createFromCode(int memorySize, String code) {
		return createFromCodeAndInput(memorySize, code, "");
	}
	
	public static BrainF createWithDefaultSize() {
		return createUsingSystemInputWithMemorySize(DEFAULT_MEMORY_SIZE);
	}
	
	public static BrainF createUsingSystemInputWithMemorySize(int memorySize) {
		Stream<Byte> in = Stream.generate(() -> {
			try {
				return (byte) System.in.read();
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		return new BrainF(memorySize, in);
	}
	
}

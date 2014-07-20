package net.zomis.brainf;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BrainF {
	public static final int MEMORY_SIZE = 0xfff;

	public BrainF() {
		this.input = null;
	}
	
	public BrainF(InputStream in) {
		this.input = new Scanner(in);
	}

	public static void main(String[] args) {
		BrainF brain = new BrainF(System.in);

		brain.addCommands("++++++++++[>++++++++++<-]>>++++++++++>->>>>>>>>>>>>>>>>-->+++++++[->++");
		brain.addCommands("++++++++<]>[->+>+>+>+<<<<]+++>>+++>>>++++++++[-<++++<++++<++++>>>]++++");
		brain.addCommands("+[-<++++<++++>>]>>-->++++++[->+++++++++++<]>[->+>+>+>+<<<<]+++++>>+>++");
		brain.addCommands("++++>++++++>++++++++[-<++++<++++<++++>>>]++++++[-<+++<+++<+++>>>]>>-->");
		brain.addCommands("---+[-<+]-<[+[->+]-<<->>>+>[-]++[-->++]-->+++[---++[--<++]---->>-<+>[+");
		brain.addCommands("+++[----<++++]--[>]++[-->++]--<]>++[--+[-<+]->>[-]+++++[---->++++]-->[");
		brain.addCommands("->+<]>>[.>]++[-->++]]-->+++]---+[-<+]->>-[+>>>+[-<+]->>>++++++++++<<[-");
		brain.addCommands(">+>-[>+>>]>[+[-<+>]>+>>]<<<<<<]>>[-]>>>++++++++++<[->-[>+>>]>[+[-<+>]>");
		brain.addCommands("+>>]<<<<<]>[-]>>[>++++++[-<++++++++>]<.<<+>+>[-]]<[<[->-<]++++++[->+++");
		brain.addCommands("+++++<]>.[-]]<<++++++[-<++++++++>]<.[-]<<[-<+>]+[-<+]->>]+[-]<<<.>>>+[");
		brain.addCommands("-<+]-<<]");

//		brain.runToEnd();
//		System.out.println(brain.output);
		
		BrainFDebug.main(args);
		
	}

	private final List<BrainFCommand> commands = new ArrayList<>();
	private final Scanner input;
	private int commandIndex;
	private final byte[] memory = new byte[MEMORY_SIZE];
	private final StringBuilder output = new StringBuilder();

	private int memoryIndex;

	public void addCommands(String string) {
		string.chars().mapToObj(i -> BrainFCommand.getCommand((char) i)).filter(obj -> obj != null).forEachOrdered(commands::add);
	}

	private void changeMemory(int i) {
		memoryIndexCheck();
		this.memory[memoryIndex] += i;
	}

	private void findMatching(BrainFCommand decrease, BrainFCommand increase, int direction) {
//		commandIndex += direction;
		int matching = 1;
		while (true) {
			commandIndex += direction;
			BrainFCommand current = this.commands.get(commandIndex);

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
		return this.memory[this.memoryIndex];
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
		this.memory[this.memoryIndex] = value;
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
//					commandIndex++;
				}
				break;
			case NEXT:
				memoryIndex++;
				memoryIndexCheck();
				break;
			case PREVIOUS:
				memoryIndex--;
				memoryIndexCheck();
				break;
			case READ:
				byte value = input.nextByte();
				setMemory(value);
				break;
			case SUBSTRACT:
				changeMemory(-1);
				break;
			case WHILE:
				if (getMemory() == 0) {
					findMatching(BrainFCommand.END_WHILE, BrainFCommand.WHILE, 1);
//					commandIndex++;
				}
				break;
			case WRITE:
				char write = (char) getMemory();
				output.append(write);
				System.out.println("Writing " + getMemory() + ": " + write);
				break;
			case NONE:
			default:
				break;
		}
	}

	private void memoryIndexCheck() {
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
		this.commands.clear();
		this.addCommands(text);
	}

	public void reset() {
		Arrays.fill(memory, (byte) 0);
		this.commandIndex = 0;
		this.memoryIndex = 0;
		this.output.setLength(0);
	}

	public int getMemorySize() {
		return memory.length;
	}

	public byte getMemory(int i) {
		return memory[i];
	}
	
}

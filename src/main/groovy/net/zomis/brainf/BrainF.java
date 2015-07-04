package net.zomis.brainf;

import net.zomis.brainf.model.BrainfuckMemory;
import net.zomis.brainf.model.BrainfuckRunner;

import java.util.stream.Stream;

public class BrainF {
    // TODO: See http://codereview.stackexchange.com/questions/61651/brainfk-interpreter-in-java

	public static BrainfuckRunner createFromCodeAndInput(int memorySize, String code, String input) {
		return createFromCodeAndInput(memorySize, code, input.chars().mapToObj(i -> (byte) i ));
	}
	
	public static BrainfuckRunner createFromCodeAndInput(int memorySize, String code, Stream<Byte> inputStream) {
		return new BrainfuckRunner(memorySize, code, inputStream);
	}
	
	public static BrainfuckRunner createFromCode(String code) {
		return createFromCodeAndInput(BrainfuckMemory.DEFAULT_MEMORY_SIZE, code, "");
	}
	
	public static BrainfuckRunner createFromCode(int memorySize, String code) {
		return createFromCodeAndInput(memorySize, code, "");
	}
	
	public static BrainfuckRunner createWithDefaultSize() {
		return createUsingSystemInputWithMemorySize(BrainfuckMemory.DEFAULT_MEMORY_SIZE);
	}
	
	public static BrainfuckRunner createUsingSystemInputWithMemorySize(int memorySize) {
		Stream<Byte> in = Stream.generate(() -> {
			try {
				return (byte) System.in.read();
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		return new BrainfuckRunner(memorySize, in);
	}
	
}

package net.zomis.brainf;

import net.zomis.brainf.model.BrainfuckMemory;
import net.zomis.brainf.model.BrainfuckRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BrainF {
	public static BrainfuckRunner createFromCodeAndInput(int memorySize, String code, String input) {
		return createFromCodeAndInput(memorySize, code, new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
	}
	
	public static BrainfuckRunner createFromCodeAndInput(int memorySize, String code, InputStream inputStream) {
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
		return new BrainfuckRunner(memorySize, System.in);
	}
	
}

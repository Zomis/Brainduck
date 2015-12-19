package net.zomis.brainf.model;

import net.zomis.brainf.model.input.FixedInput;
import net.zomis.brainf.model.input.NoInput;
import net.zomis.brainf.model.input.QueueInput;

import java.util.concurrent.BlockingQueue;

public class BrainF {
	public static BrainfuckRunner createFromCodeAndInput(int memorySize, String code, String input) {
		return createFromCodeAndInput(memorySize, code, new FixedInput(input));
	}
	
	public static BrainfuckRunner createFromCodeAndInput(int memorySize, String code, BrainfuckInput inputStream) {
		return new BrainfuckRunner(memorySize, code, inputStream);
	}
	
	public static BrainfuckRunner createWithDefaultSize() {
		return createUsingSystemInputWithMemorySize(BrainfuckMemory.DEFAULT_MEMORY_SIZE);
	}
	
	public static BrainfuckRunner createUsingSystemInputWithMemorySize(int memorySize) {
		return new BrainfuckRunner(memorySize, new NoInput());
	}

	public static BrainfuckRunner createUsingQueueWithMemorySize(BlockingQueue<Integer> input,
             int memorySize, BrainfuckOutput output) {
		return new BrainfuckRunner(new BrainfuckMemory(memorySize), new BrainfuckCode(), new QueueInput(input), output);
	}

    public static BrainfuckCode code(String code) {
        BrainfuckCode bfCode = new BrainfuckCode();
        bfCode.setSource(ListCode.create(code));
        return bfCode;
    }
}

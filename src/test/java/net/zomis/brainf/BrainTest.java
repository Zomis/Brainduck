package net.zomis.brainf;

import static org.junit.Assert.*;

import net.zomis.brainf.model.BrainfuckMemory;
import net.zomis.brainf.model.BrainfuckRunner;
import org.junit.Test;

public class BrainTest {

	@Test
	public void gotoCorrectEndWhile() {
		BrainfuckRunner brain = BrainF.createWithDefaultSize();
		brain.getCode().addCommands(">+>[-]+   ");
        brain.getCode().addCommands("++[-->++]-->   Find next 254 and go one step beyond it");
        brain.getCode().addCommands("            Loop through all 254s");
        brain.getCode().addCommands("+++[---         Make sure that we are not at 253 (end)");
        brain.getCode().addCommands("++[--<++]--	");

		assertEquals(BrainFCommand.NEXT, brain.step());
		assertEquals(BrainFCommand.ADD, brain.step());
		assertEquals(BrainFCommand.NEXT, brain.step());
		assertEquals(BrainFCommand.WHILE, brain.step());
		
		assertEquals(6, brain.getCode().getCommandIndex());
		assertEquals(BrainFCommand.ADD, brain.step());
	}

	@Test
	public void simpleLoopMultiplication() {
		BrainfuckRunner brain = BrainF.createWithDefaultSize();
		brain.getCode().addCommands("++[>+++<-]>>>");
		brain.run();
		assertArrayEquals(new byte[] { 0, 6, 0, 0, 0, 0, 0, 0, 0, 0 },
				brain.getMemory().getMemoryArray(0, 10));
	}

	@Test
	public void printAlphabet() {
		BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.getCode().addCommands("++++++[>++++++++++>++++<<-]>+++++>++[-<.+>]");
		brain.run();
		assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", brain.getOutput());
	}

	@Test
	public void input() {
		BrainfuckRunner brain = BrainF.createFromCodeAndInput(BrainfuckMemory.DEFAULT_MEMORY_SIZE, "+++,.", "a");
		brain.run();
		assertEquals("a", brain.getOutput());
	}
	
	@Test
	public void simpleCommands() {
        BrainfuckRunner abc = BrainF.createWithDefaultSize();
        abc.getCode().addCommands("+>++>+++<");
		abc.run();
		assertEquals(9, abc.getCode().getCommandIndex());
		assertEquals(1, abc.getMemory().getMemoryIndex());
		assertEquals(2, abc.getMemory().getMemory());
		abc.perform(BrainFCommand.PREVIOUS);
		assertEquals(1, abc.getMemory().getMemory());
		abc.perform(BrainFCommand.NEXT);
		abc.perform(BrainFCommand.NEXT);
		assertEquals(3, abc.getMemory().getMemory());

	}

}

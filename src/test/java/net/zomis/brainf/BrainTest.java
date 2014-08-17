package net.zomis.brainf;

import static org.junit.Assert.*;

import org.junit.Test;

public class BrainTest {

	@Test
	public void gotoCorrectEndWhile() {
		BrainF brain = BrainF.createWithDefaultSize();
		brain.addCommands(">+>[-]+   ");
		brain.addCommands("++[-->++]-->   Find next 254 and go one step beyond it");
		brain.addCommands("            Loop through all 254s");
		brain.addCommands("+++[---         Make sure that we are not at 253 (end)");
		brain.addCommands("++[--<++]--	");

		assertEquals(BrainFCommand.NEXT, brain.step());
		assertEquals(BrainFCommand.ADD, brain.step());
		assertEquals(BrainFCommand.NEXT, brain.step());
		assertEquals(BrainFCommand.WHILE, brain.step());
		
		assertEquals(6, brain.getCommandIndex());
		assertEquals(BrainFCommand.ADD, brain.step());
	}

	@Test
	public void simpleLoopMultiplication() {
		BrainF brain = BrainF.createWithDefaultSize();
		brain.addCommands("++[>+++<-]>>>");
		brain.runToEnd();
		assertArrayEquals(new byte[] { 0, 6, 0, 0, 0, 0, 0, 0, 0, 0 },
				brain.getMemoryArray(0, 10));
	}

	@Test
	public void printAlphabet() {
		BrainF brain = BrainF.createWithDefaultSize();
		brain.addCommands("++++++[>++++++++++>++++<<-]>+++++>++[-<.+>]");
		brain.runToEnd();
		assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", brain.getOutput());
	}

	@Test
	public void input() {
		BrainF brain = BrainF.createFromCodeAndInput(BrainF.DEFAULT_MEMORY_SIZE, "+++,.", "a");
		brain.runToEnd();
		assertEquals("a", brain.getOutput());
	}
	
	@Test
	public void simpleCommands() {
		BrainF abc = BrainF.createWithDefaultSize();
		abc.addCommands("+>++>+++<");
		abc.runToEnd();
		assertEquals(9, abc.getCommandIndex());
		assertEquals(1, abc.getMemoryIndex());
		assertEquals(2, abc.getMemory());
		abc.perform(BrainFCommand.PREVIOUS);
		assertEquals(1, abc.getMemory());
		abc.perform(BrainFCommand.NEXT);
		abc.perform(BrainFCommand.NEXT);
		assertEquals(3, abc.getMemory());

	}

}

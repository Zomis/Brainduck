package net.zomis.brainf.model;

import net.zomis.brainf.model.ast.Lexer;
import net.zomis.brainf.model.ast.tree.Parser;
import net.zomis.brainf.model.groovy.GroovyBFContext;
import net.zomis.brainf.model.input.QueueInput;

import java.util.concurrent.BlockingQueue;

public class BrainF {
	public static BrainfuckRunner createUsingQueueWithMemorySize(BlockingQueue<Integer> input,
             int memorySize, BrainfuckOutput output) {
		return new BrainfuckRunner(new BrainfuckMemory(memorySize), new BrainfuckCode(), new QueueInput(input), output);
	}

    public static BrainfuckCode code(String code) {
        BrainfuckCode bfCode = new BrainfuckCode();
        bfCode.setRootTree(new Parser(new GroovyBFContext()).parse(Lexer.tokenize(code)));
        bfCode.setSource(ListCode.create(code));
        return bfCode;
    }
}

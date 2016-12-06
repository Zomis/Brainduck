package net.zomis.brainf.model

import groovy.transform.CompileStatic
import net.zomis.brainf.model.ast.tree.SteppableSyntax
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.run.RunStrategy

@CompileStatic
class BrainfuckRunner {

    final BrainfuckMemory memory
    final BrainfuckCode code
    final BrainfuckInput input;

    private final BrainfuckOutput output
    private BrainfuckListener listener = new BrainfuckListener() {
        @Override
        void beforePerform(BrainfuckRunner runner, BrainfuckCommand command) {}

        @Override
        void afterPerform(BrainfuckRunner runner, BrainfuckCommand command) {}
    }

    BrainfuckRunner(BrainfuckMemory memory, BrainfuckCode code, BrainfuckInput input, BrainfuckOutput output) {
        this.memory = memory
        this.code = code
        this.input = input
        this.output = output
    }

    @Deprecated
    String getOutput() {
        return output.toString();
    }

    BrainfuckOutput getOutputBuilder() {
        return output
    }

    void run() {
        while (code.hasMoreCommands()) {
            step();
        }
    }

    BrainfuckCommand step() {
        // change this...
        /*
        * check current node
        * run current node
        * if current node is syntaxtree, enter syntaxtree (if condition is ok)
        * if current node is last one in syntax tree, then go to parent syntaxtree again,
        * and on next run determine whether to enter it or not (to simulate end loop ']' )
        *
        *
        */
        println "Perform step with memory " + Arrays.toString(memory.values(0, 5)) + " and index $memory.memoryIndex"
        if (code.isFinished()) {
            return null;
        }

        if (code.currentSyntax instanceof SteppableSyntax) {
            println "Step: " + code.currentSyntax
            SteppableSyntax steppableSyntax = code.currentSyntax as SteppableSyntax
            steppableSyntax.performTimes(this, 1)
            code.positionInSyntax++
            if (code.positionInSyntax == steppableSyntax.getTimes()) {
                println "Syntax done: " + code.currentSyntax
                code.positionInSyntax = 0;
                gotoNextSyntax()
            }
            return ;
        } else {
            println "Perform: " + code.currentSyntax
            code.currentSyntax.perform(this)
        }

        gotoNextSyntax()

        BrainfuckCommand command = code.getNextCommand();
        int index = code.commandIndex
        perform(command);
        int commandLength = code.source.getCommandLength(index)
        code.commandIndex += commandLength
        return command;
    }

    Syntax runSyntax() {
        def syntax = code.currentSyntax
        if (code.positionInSyntax != 0) {
            // Perform the rest of the active syntax
            SteppableSyntax steppableSyntax = syntax as SteppableSyntax
            steppableSyntax.performTimes(this, steppableSyntax.getTimes() - code.positionInSyntax)
            code.positionInSyntax = 0
            gotoNextSyntax()
        } else {
            syntax.perform(this)
            gotoNextSyntax()
        }
        syntax
    }

    private void gotoNextSyntax() {
        println "Goto next after " + code.currentSyntax
        if (code.currentSyntax instanceof SyntaxTree) {
            // enter syntax tree if condition is ok, otherwise skip to next syntax
            if (memory.value != 0) {
                println "Entering syntax tree " + code.currentSyntax
                code.enteredTrees.push(new SyntaxTreePosition(code.currentSyntax as SyntaxTree))
            } else {
                println "Loop zero at pos $memory.memoryIndex, skipping entering."
                code.getCurrentTree().stepForward()
            }
        } else if (code.syntaxIndex == code.currentTree.size() - 1) {
            println "Last step in syntax. Pop."
            // go to parent syntax tree
            code.enteredTrees.pop()
        } else {
            println "${code.syntaxIndex} != ${code.currentTree.size() - 1}"
            // go to next
            code.getCurrentTree().stepForward()
            println "Step forward."
        }
    }

    void reset() {
        memory.reset();
        code.resetIndex();
    }

    @Deprecated
    void perform(BrainfuckCommand command) {
        listener.beforePerform(this, command)
        if (command) {
            command.perform(this)
        }
        listener.afterPerform(this, command)
    }

    void setListener(BrainfuckListener listener) {
        this.listener = listener
    }

    int run(RunStrategy strategy) {
        int count = 0;
        boolean repeat = strategy.start(this);
        while (repeat) {
            count++
            repeat = strategy.next(this)
        }
        count
    }

    void appendOutput(char value) {
        output.write(value);
    }

}

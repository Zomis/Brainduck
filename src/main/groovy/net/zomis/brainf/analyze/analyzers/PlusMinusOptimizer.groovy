package net.zomis.brainf.analyze.analyzers

import groovy.transform.CompileStatic
import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.InspectionResult
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.ChangePointerSyntax
import net.zomis.brainf.model.ast.tree.ChangeValueSyntax
import net.zomis.brainf.model.ast.tree.CommentSyntax
import net.zomis.brainf.model.ast.tree.GroovySyntax
import net.zomis.brainf.model.ast.tree.SteppableSyntax
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.classic.BrainFCommand

@CompileStatic
class PlusMinusOptimizer implements BrainfuckAnalyzer {

    private final List<InspectionResult> results = []

    private List<Integer> changesLeft = new ArrayList<>()
    private List<Integer> changesRight = new ArrayList<>()
    private int pointerChange
    private int commandStart
    private int commandsUsed

    @Override
    void after(Brainalyze analyze, BrainfuckRunner runner) {
        finishAndReset(runner)
        results.each {
            analyze.addInspectionResult(it)
        }
    }

    @Override
    void print() {
        results.each {println it}
    }

    @Override
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, Syntax command) {
        if (command instanceof GroovySyntax) {
            return
        }
        if (command instanceof CommentSyntax) {
            return
        }
        def commandCount = command instanceof SteppableSyntax ? (command as SteppableSyntax).getTimes() : 0;
        if (command instanceof ChangePointerSyntax) {
            pointerMove((command as ChangePointerSyntax).getValue());
            commandsUsed += commandCount;
            return
        }
        if (command instanceof ChangeValueSyntax) {
            valueChange((command as ChangeValueSyntax).getValue());
            commandsUsed += commandCount;
            return
        }
        finishAndReset(runner)
    }

    void valueChange(int i) {
        int index = pointerChange
        List list
        if (pointerChange >= 0) {
            list = changesRight
        } else {
            index = Math.abs(pointerChange) - 1
            list = changesLeft
        }
        while (list.size() <= index) {
            list.add(0)
        }
        list.set(index, list.get(index) + i)
    }

    void pointerMove(int i) {
        pointerChange += i
    }

    boolean finishAndReset(BrainfuckRunner runner) {
        trim(changesLeft)
        trim(changesRight)
        // find out if it's better to go to the `pointerChange` from left or right
        // >>>>> + <<<<< <<<<< <<<<< - > + for example is better to go right first, then left
        int leftCost = changesLeft.size() + pointerChange
        int rightCost = changesRight.size() - 1 - pointerChange
        boolean fromLeftToEnd = leftCost < rightCost

        StringBuilder str = new StringBuilder()
        if (fromLeftToEnd) {
            if (!changesRight.isEmpty()) {
                str.append(code(changesRight, '>' as char))
                str.append('<' * (changesRight.size() - 1))
            }
            if (!changesLeft.isEmpty()) {
                str.append('<')
                str.append(code(changesLeft, '<' as char))
            }
            if (leftCost > 0) {
                str.append('>' * leftCost)
            } else {
                str.append('<' * -leftCost)
            }
        } else {
            if (!changesLeft.isEmpty()) {
                str.append('<')
                str.append(code(changesLeft, '<' as char))
                str.append('>' * changesLeft.size())
            }
            if (!changesRight.isEmpty()) {
                str.append(code(changesRight, '>' as char))
            }
            if (rightCost > 0) {
                str.append('<' * rightCost)
            } else {
                str.append('>' * -rightCost)
            }
        }


        String shortestCode = str.toString()
        if (shortestCode.length() < this.commandsUsed) {
            this.results.add(new InspectionResult(InspectionResult.InspectionSeverity.HINT,
                commandStart, runner.code.commandIndex,
                "Code is unnecessarily complicated. Can be written as '$shortestCode'"))
        }

        changesLeft.clear()
        changesRight.clear()
        pointerChange = 0
        commandStart = 0
        commandsUsed = 0
    }

    static String code(List<Integer> list, char ch) {
        StringBuilder str = new StringBuilder()
        def it = list.listIterator()
        while (it.hasNext()) {
            int value = it.next()
            if (value > 0) {
                str.append('+' * value)
            }
            if (value < 0) {
                value = Math.abs(value)
                str.append('-' * value)
            }
            if (it.hasNext()) {
                str.append(ch)
            }
        }
        str.toString()
    }

    static void trim(List<Integer> integers) {
        while (!integers.isEmpty() && integers.get(integers.size() - 1) == 0) {
            integers.remove(integers.size() - 1)
        }
    }

}

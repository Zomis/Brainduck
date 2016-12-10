package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.ChangePointerSyntax
import net.zomis.brainf.model.ast.tree.ChangeValueSyntax
import net.zomis.brainf.model.ast.tree.GroovySyntax
import net.zomis.brainf.model.ast.tree.PrintSyntax
import net.zomis.brainf.model.ast.tree.ReadSyntax
import net.zomis.brainf.model.ast.tree.SteppableSyntax
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.classic.BrainFCommand

import java.util.function.BiFunction

class CommandCountAnalysis implements BrainfuckAnalyzer {

    private int[] times
    private final Map<String, Integer> actionsPerCommand = new HashMap<>()
    private final Map<String, Integer> codeCommands = new HashMap<>()

    private static final BiFunction<Integer, Integer, Integer> MERGE_FUNCTION = {a, b -> a + b}

    @Override
    void beforeStart(BrainfuckRunner runner) {
        this.times = new int[runner.code.commandCount];
    }

    @Override
    void print() {
        println 'Actions per command'
        printCommands(actionsPerCommand)
        println()
        println 'Code instructions per command'
        printCommands(codeCommands)
        println()
    }

    static String syntaxKey(Syntax syntax) {
        boolean negative = syntax instanceof SteppableSyntax && (syntax as SteppableSyntax).value < 0;
        if (syntax instanceof ChangeValueSyntax) {
            return negative ? BrainFCommand.SUBTRACT : BrainFCommand.ADD;
        }
        if (syntax instanceof ChangeValueSyntax) {
            return negative ? BrainFCommand.PREVIOUS : BrainFCommand.NEXT;
        }
        if (syntax instanceof ReadSyntax) {
            return BrainFCommand.READ;
        }
        if (syntax instanceof PrintSyntax) {
            return BrainFCommand.WRITE;
        }
        if (syntax instanceof GroovySyntax) {
            return syntax.toString()
        }
        return null
    }

    private static void add(Map<String, Integer> map, String key) {
        map.merge(key, 1, MERGE_FUNCTION)
    }

    @Override
    void beforeWhile(MemoryCell cell, BrainfuckRunner runner) {
        add(actionsPerCommand, BrainFCommand.WHILE.name())
    }

    @Override
    void beforeEndWhile(MemoryCell cell, BrainfuckRunner runner) {
        add(actionsPerCommand, BrainFCommand.END_WHILE.name())
    }

    @Override
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, Syntax command) {
        int codeIndex = runner.code.commandIndex
        this.times[codeIndex]++
        String key = syntaxKey(command);
        int times = command instanceof SteppableSyntax ? (command as SteppableSyntax).getTimes() : 1;
        if (key != null) {
            actionsPerCommand.merge(key, times, MERGE_FUNCTION)
        }
    }

    @Override
    void after(Brainalyze analyze, BrainfuckRunner runner) {
        addCodeCommands(runner.code.rootTree)
        int commandCount = runner.code.commandCount
        for (int i = 0; i < commandCount; i++) {
            String command = runner.code.getCommandAt(i)
            codeCommands.merge(command, 1, MERGE_FUNCTION)
        }
    }

    void addCodeCommands(SyntaxTree tree) {
        for (Syntax syntax : tree) {
            int value = syntax instanceof SteppableSyntax ? (syntax as SteppableSyntax).getValue() : 1;
            int times = syntax instanceof SteppableSyntax ? (syntax as SteppableSyntax).getTimes() : 1;
            if (syntax instanceof SyntaxTree) {
                codeCommands.merge(BrainFCommand.WHILE.name(), 1, MERGE_FUNCTION)
                codeCommands.merge(BrainFCommand.END_WHILE.name(), 1, MERGE_FUNCTION)
                addCodeCommands(syntax as SyntaxTree)
            } else if (syntax instanceof ChangePointerSyntax) {
                String key = value < 0 ? BrainFCommand.PREVIOUS.name() : BrainFCommand.NEXT.name();
                codeCommands.merge(key, times, MERGE_FUNCTION)
            } else if (syntax instanceof ChangeValueSyntax) {
                String key = value < 0 ? BrainFCommand.SUBTRACT.name() : BrainFCommand.ADD.name();
                codeCommands.merge(key, times, MERGE_FUNCTION)
            } else {
                codeCommands.merge(syntax.toString(), times, MERGE_FUNCTION)
            }
        }
    }

    static void printCommands(Map<String, Integer> ints) {
        int sum = 0
        ints.entrySet().stream().forEach({
            String command = it.key
            Integer count = it.value
            if (count > 0) {
                println "$command: $count"
            }
            if (command != BrainFCommand.NONE) {
                sum += count
            }
        })
        println "Total: $sum"
    }

    int getActionsForCommand(String command) {
        this.@actionsPerCommand[command]
    }

    int[] getTimes() {
        return Arrays.copyOf(this.@times, this.@times.length)
    }

    Map<String, Integer> getActionsPerCommand() {
        return new HashMap<>(this.@actionsPerCommand)
    }

    Map<String, Integer> getCodeCommands() {
        return new HashMap<>(this.@codeCommands)
    }

}

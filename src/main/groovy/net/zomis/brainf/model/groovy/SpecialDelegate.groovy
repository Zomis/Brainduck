package net.zomis.brainf.model.groovy

import net.zomis.brainf.analyze.AnalyzeFactory
import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.analyzers.BrainfuckAnalyzers
import net.zomis.brainf.model.BrainfuckCode
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckMemory
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.CodeRetriever
import net.zomis.brainf.model.ListCode
import net.zomis.brainf.model.SubCommand
import net.zomis.brainf.model.ast.Lexer
import net.zomis.brainf.model.ast.tree.Parser
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.classic.BrainfuckConverter
import net.zomis.brainf.model.input.QueueInput
import net.zomis.brainf.model.input.StringBuilderOutput

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.function.Predicate

class SpecialDelegate {

    private final BrainfuckRunner runner
    private final GroovyBFContext groovyContext
    public final WrapBehavior CRASH = WrapBehavior.CRASH
    public final WrapBehavior ALLOW = WrapBehavior.ALLOW
    public final WrapBehavior BLOCK = WrapBehavior.BLOCK

    SpecialDelegate(GroovyBFContext groovyContext, BrainfuckRunner runner) {
        this.groovyContext = groovyContext
        this.runner = runner
    }

    void wrapValue(WrapBehavior behavior) {
        groovyContext.setValueWrap(behavior)
    }

    void input(String text) {
        if (runner.input instanceof QueueInput) {
            QueueInput input = runner.input as QueueInput
            text.chars.each {input.queue.offer((int) it.charValue())}
        }
    }

    void input(int value) {
        if (runner.input instanceof QueueInput) {
            QueueInput input = runner.input as QueueInput
            input.queue.offer(value)
        }
    }

    void inputLine(String text) {
        input(text)
        input(10)
    }

    void wrapMemory(WrapBehavior behavior) {
        groovyContext.setMemoryWrap(behavior)
    }

    void minValue(int value) {
        runner.memory.minValue = value
    }

    void pause() {
        groovyContext.pause = true
    }

    void maxValue(int value) {
        runner.memory.maxValue = value
    }

    int getValue() {
        runner.memory.value
    }

    int getPosition() {
        runner.memory.memoryIndex
    }

    static final Predicate<BrainfuckCommand> IS_WHILE_START = { it == BrainFCommand.WHILE }
    static final Predicate<BrainfuckCommand> IS_WHILE_END = {it == BrainFCommand.END_WHILE}

    boolean name(String name) {
        groovyContext.addCellName(runner.memory.memoryIndex, name)
    }

    boolean hasName(String name) {
        Map<String, Integer> nameMap = groovyContext.getCellNames(runner.memory.memoryIndex)
        return nameMap == null ? false : nameMap.containsKey(name)
    }

    void nextLoop(String tagName) {
        def iterator = runner.code.currentTree.iteratorCopy();
        while (iterator.hasNext()) {
            def syntax = iterator.next();
            if (syntax instanceof SyntaxTree) {
                syntaxLoopTag(syntax, tagName)
                return
            }
        }
        throw new IllegalStateException("There is no lastLoop.");
    }

    void bf(String code) {
        SyntaxTree tree = new Parser(groovyContext).parse(Lexer.tokenize(code))
        println 'Subcommand: ' + tree

        def command = new SubCommand(tree)
        command.perform(runner)
    }

    private void syntaxLoopTag(Syntax syntax, String name) {
        int startIndex = syntax.tokens.get(0).info.position
        groovyContext.addLoopName(startIndex, name)
    }

    private void recursiveLoopTag(SyntaxTree tree, String tagName) {
        syntaxLoopTag(tree, tagName)
        for (Syntax syntax : tree) {
            if (syntax instanceof SyntaxTree) {
                recursiveLoopTag(syntax, tagName)
            }
        }
    }

    void nextLoops(String tagName) {
        def iterator = runner.code.currentTree.iteratorCopy();
        while (iterator.hasNext()) {
            def syntax = iterator.next();
            if (syntax instanceof SyntaxTree) {
                recursiveLoopTag(syntax, tagName)
                return
            }
        }
        throw new IllegalStateException("There is no lastLoop.");
    }

    void loop(String tagName) {
        if (runner.isOnRootTree()) {
            throw new IllegalStateException("Not inside a loop.");
        }
        def tree = runner.code.currentTree.tree;
        int index = tree.tokens.get(0).info.position
        groovyContext.addLoopName(index, tagName)
    }

    void lastLoop(String tagName) {
        def iterator = runner.code.currentTree.iteratorCopy();
        while (iterator.hasPrevious()) {
            def syntax = iterator.previous();
            if (syntax instanceof SyntaxTree) {
                syntaxLoopTag(syntax, tagName)
                return
            }
        }
        throw new IllegalStateException("There is no lastLoop.");
    }

    @Deprecated
    int findCode(Predicate<BrainfuckCommand> predicate, int delta) {
        int index = runner.code.commandIndex
        while (true) {
            BrainfuckCommand command = runner.code.getCommandAt(index)
            if (command == null) {
                throw new IllegalStateException('command out of range: ' + index)
            }
            if (predicate.test(command)) {
                break
            }
            index += delta
        }
        index
    }

    def memory(int count) {
        int index = runner.memory.memoryIndex
        [offset: {int forward ->
            int[] values = runner.memory.getMemoryArray(index + forward, count)
            new ArrayBuilder(values)
        }, offsetBackward: {int backward ->
            int[] values = runner.memory.getMemoryArray(index - backward, count)
            new ArrayBuilder(values)
        }]
    }

    ArrayBuilder values(int firstValue) {
        return new ArrayBuilder(firstValue)
    }

    void compareWith(String file) {
        final Runnable before = groovyContext.afterRun
        groovyContext.afterRun = {
            before.run()
            String myOutput = runner.output
            StringBuilder otherOutput = new StringBuilder()
            BrainfuckRunner other = new BrainfuckRunner(new BrainfuckMemory(runner.memory.size), new BrainfuckCode(),
                null, new StringBuilderOutput(otherOutput))
            URL url = findFile(file)
            GroovyBFContext otherContext = new GroovyBFContext()
            other.code.source = ListCode.create(new GroovySupportConverter(otherContext,
                    new BrainfuckConverter()), url.text)
            Brainalyze analyze = new AnalyzeFactory()
                .addAnalyzers(BrainfuckAnalyzers.availableAnalyzers)
                .analyze(other, otherContext)
            println "Analyze for $file"
            analyze.print()
            println()
            println()
            assert myOutput == otherOutput.toString()
        }
    }

    URL findFile(String name) {
        List<URL> urls = []
        if (!name.endsWith('.bf')) {
            name = name + '.bf'
        }
        Path relative = Paths.get(name)
        if (Files.exists(relative)) {
            urls << relative.toUri().toURL()
        }
        urls << getClass().getResource(name)
        urls << getClass().classLoader.getResource(name)

        URL url = urls.stream()
                .peek({url -> println "Checking for $name at $url"})
                .filter({url -> url != null})
                .findFirst()
                .orElseThrow({new RuntimeException("Unable to find file $name")})
        return url
    }

    void include(String name) {
        URL url = findFile(name)
        String code = url.text
        bf(code)
    }

}

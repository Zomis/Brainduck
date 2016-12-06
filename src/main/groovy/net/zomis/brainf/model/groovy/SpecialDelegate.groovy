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
        int index = findCode(IS_WHILE_START, 1)
        groovyContext.addLoopName(index, tagName)
    }

    void bf(String code) {
        def commands = ListCode.create(new BrainfuckConverter(), code)
        // TODO: Parse commands and create syntax tree and perform that.
//        def subCommand = new SubCommand(commands)
//        runner.perform(subCommand)
        throw new UnsupportedOperationException("bf code method needs to be rewritten")
    }

    void nextLoops(String tagName) {
        int firstIndex = findCode(IS_WHILE_START, 1)
        int lastIndex = runner.code.findMatching(firstIndex, BrainFCommand.END_WHILE, BrainFCommand.WHILE, 1)

        while (firstIndex < lastIndex) {
            if (IS_WHILE_START.test(runner.code.getCommandAt(firstIndex))) {
                groovyContext.addLoopName(firstIndex, tagName)
            }
            firstIndex++
        }

    }

    void loop(String tagName) {
        int index = findCode(IS_WHILE_START, -1)
        groovyContext.addLoopName(index, tagName)
    }

    void lastLoop(String tagName) {
        int endIndex = findCode(IS_WHILE_END, -1)
        int startIndex = runner.code.findMatching(endIndex, BrainFCommand.WHILE, BrainFCommand.END_WHILE, -1)
        groovyContext.addLoopName(startIndex, tagName)
    }

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

        CodeRetriever commands = ListCode.create(url.text)
        println 'Subcommand: ' + commands

        def command = new SubCommand(commands)
        command.perform(runner)
    }

}

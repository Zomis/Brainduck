package net.zomis.brainf

import net.zomis.brainf.analyze.AnalyzeFactory
import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.analyzers.BrainfuckAnalyzers
import net.zomis.brainf.analyze.analyzers.CommandCountAnalysis
import net.zomis.brainf.analyze.analyzers.GroovyCommandAnalysis
import net.zomis.brainf.analyze.analyzers.IOAnalysis
import net.zomis.brainf.analyze.analyzers.MemoryIndexAnalysis
import net.zomis.brainf.analyze.analyzers.MemoryValues
import net.zomis.brainf.analyze.analyzers.ReadWriteAnalysis
import net.zomis.brainf.analyze.analyzers.WhileLoopAnalysis
import net.zomis.brainf.model.BrainF
import net.zomis.brainf.model.BrainfuckCode
import net.zomis.brainf.model.BrainfuckMemory
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ListCode
import net.zomis.brainf.model.ast.Lexer
import net.zomis.brainf.model.ast.tree.Parser
import net.zomis.brainf.model.classic.BrainfuckConverter
import net.zomis.brainf.model.groovy.GroovyBFContext
import net.zomis.brainf.model.groovy.GroovySupportConverter
import net.zomis.brainf.model.input.NoInput
import net.zomis.brainf.model.input.StringBuilderOutput
import org.junit.Before

class BrainfuckTest {

    BrainfuckRunner brain
    ListCode source
    Brainalyze analyze
    GroovyBFContext context
    StringBuilder output

    void analyze(BrainfuckAnalyzer... analyzers) {
        analyze = new AnalyzeFactory().addAnalyzers(analyzers).analyze(brain, context)
    }

    @Before
    public void setup() {
        context = new GroovyBFContext()
        output = new StringBuilder()
        brain = new BrainfuckRunner(new BrainfuckMemory(),
                new BrainfuckCode(), new NoInput(), new StringBuilderOutput(output))
        def converter = new GroovySupportConverter(context,
                new BrainfuckConverter())
        source = ListCode.create(converter, "")
        brain.code.source = source
        brain.code.rootTree = new Parser(context).parse(Lexer.tokenize(""))
    }

    void analyzeAll() {
        analyze(BrainfuckAnalyzers.availableAnalyzers)
    }

    void useFile(String fileName) {
        useCode(getClass().getResource(fileName).text)
    }

    void useCode(String code) {
        source.addCommands(code)
        brain.code.rootTree = new Parser(context).parse(Lexer.tokenize(code))
    }

}

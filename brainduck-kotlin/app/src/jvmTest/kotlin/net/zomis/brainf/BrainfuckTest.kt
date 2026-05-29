package net.zomis.brainf

import net.zomis.brainduck.LimitedMemory
import net.zomis.brainduck.analyze.BrainfuckRuntime
import kotlin.test.BeforeTest

open class BrainfuckTest {
/*
    var brain: BrainfuckRuntime
    var source: ListCode
    var analyze: Brainalyze
    var context: GroovyBFContext
    var output: StringBuilder

    fun analyze(vararg analyzers: BrainfuckAnalyzer) {
        analyze = AnalyzeFactory().addAnalyzers(analyzers).analyze(brain, context)
    }

    @BeforeTest
    fun setup() {
        output = StringBuilder()
        brain = BrainfuckRunner(LimitedMemory(0..1000), BrainfuckCode(), NoInput(), StringBuilderOutput(output))
        val converter = GroovySupportConverter(context, BrainfuckConverter())
        source = ListCode.create(converter, "")
        brain.code.source = source
        brain.code.rootTree = Parser(context).parse(Lexer.tokenize(""))
    }

    fun analyzeAll() {
        analyze(BrainfuckAnalyzers.availableAnalyzers)
    }

    fun useFile(fileName: String) {
        useCode(getClass().getResource(fileName).text)
    }

    fun useCode(code: String) {
        source.addCommands(code)
        brain.code.rootTree = Parser(context).parse(Lexer.tokenize(code))
    }
*/
}

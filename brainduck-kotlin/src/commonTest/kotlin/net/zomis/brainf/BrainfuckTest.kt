package net.zomis.brainf

import net.zomis.brainf.analyze.AnalyzeFactory
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.analyzers.BrainfuckAnalyzers
import net.zomis.brainf.model.BrainfuckCode
import net.zomis.brainf.model.BrainfuckMemory
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ListCode
import net.zomis.brainf.model.ast.Lexer
import net.zomis.brainf.model.ast.tree.Parser
import net.zomis.brainf.model.classic.BrainfuckConverter
import net.zomis.brainf.model.groovy.GroovyBFContext
import net.zomis.brainf.model.groovy.GroovySupportConverter
import kotlin.test.BeforeTest

open class BrainfuckTest {

    var brain BrainfuckRunner
    var source: ListCode
    var analyze: Brainalyze
    var context: GroovyBFContext
    var output: StringBuilder

    fun analyze(vararg analyzers: BrainfuckAnalyzer) {
        analyze = AnalyzeFactory().addAnalyzers(analyzers).analyze(brain, context)
    }

    @BeforeTest
    fun setup() {
        context = GroovyBFContext()
        output = StringBuilder()
        brain = BrainfuckRunner(BrainfuckMemory(), BrainfuckCode(), NoInput(), StringBuilderOutput(output))
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

}

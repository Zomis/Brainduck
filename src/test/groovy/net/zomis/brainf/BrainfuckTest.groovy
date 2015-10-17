package net.zomis.brainf

import net.zomis.brainf.analyze.AnalyzeFactory
import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.model.BrainF
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ListCode
import net.zomis.brainf.model.classic.BrainfuckConverter
import net.zomis.brainf.model.groovy.GroovyBFContext
import net.zomis.brainf.model.groovy.GroovySupportConverter
import org.junit.Before

class BrainfuckTest {

    BrainfuckRunner brain
    ListCode source
    Brainalyze analyze
    GroovyBFContext context

    void analyze() {
        analyze = Brainalyze.analyze(brain, context)
    }

    void analyze(BrainfuckAnalyzer... analyzers) {
        analyze = new AnalyzeFactory().addAnalyzers(analyzers).analyze(brain, context)
    }

    @Before
    public void setup() {
        context = new GroovyBFContext()
        brain = BrainF.createWithDefaultSize()
        def converter = new GroovySupportConverter(context,
                new BrainfuckConverter())
        source = ListCode.create(converter, "")
        brain.code.source = source
    }

}

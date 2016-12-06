package net.zomis.brainf.analyze.analyzers

import groovy.transform.CompileStatic
import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.groovy.GroovyBFContext

@CompileStatic
class GroovyCommandAnalysis implements BrainfuckAnalyzer {

    private final List<Map> problematicCommands = []

    @Override
    void after(Brainalyze analyze, BrainfuckRunner runner) {
        for (int i = 0; i < runner.code.commandCount; i++) {
            def command = runner.code.getCommandAt(i)
            if (command instanceof GroovyBFContext.SpecialCommand) {
                GroovyBFContext.SpecialCommand cmd = command as GroovyBFContext.SpecialCommand
                for (int codeIndex = 0; codeIndex < cmd.code.length(); codeIndex++) {
                    char ch = cmd.code.charAt(codeIndex)
                    BrainFCommand bfCommand = BrainFCommand.getCommand(ch)
                    if (bfCommand != BrainFCommand.NONE) {
                        Map<String, Object> map = [index: i, codeIndex: codeIndex, command: bfCommand]
                        problematicCommands << map
                        break;
                    }
                }
            }
        }
    }

    @Override
    void print() {
        for (Map problem : problematicCommands) {
            println "Problematic command: $problem"
        }
    }

}

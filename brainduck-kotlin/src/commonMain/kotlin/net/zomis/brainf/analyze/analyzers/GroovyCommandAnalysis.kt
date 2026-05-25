package net.zomis.brainf.analyze.analyzers

import groovy.transform.CompileStatic
import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.GroovySyntax
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.classic.BrainFCommand

@CompileStatic
class GroovyCommandAnalysis implements BrainfuckAnalyzer {

    private final List<Map> problematicCommands = []

    @Override
    void after(Brainalyze analyze, BrainfuckRunner runner) {
        analyzeTree(runner.code.rootTree);
    }

    void analyzeTree(SyntaxTree syntaxTree) {
        for (Syntax syntax : syntaxTree) {
            if (syntax instanceof GroovySyntax) {
                inspectSyntax(syntax as GroovySyntax)
            }
            if (syntax instanceof SyntaxTree) {
                analyzeTree(syntax as SyntaxTree)
            }
        }
    }

    void inspectSyntax(GroovySyntax groovySyntax) {
        String code = groovySyntax.getCode();
        for (int codeIndex = 0; codeIndex < code.length(); codeIndex++) {
            char ch = code.charAt(codeIndex)
            BrainFCommand bfCommand = BrainFCommand.getCommand(ch)
            if (bfCommand != BrainFCommand.NONE) {
                Map<String, ? extends Object> map = [index: groovySyntax.getTokens().get(0).info.position,
                   codeIndex: codeIndex, command: bfCommand]
                problematicCommands << map
                break;
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

package net.zomis.brainf.model

import groovy.transform.CompileStatic
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.run.UntilEndStrategy

@CompileStatic
class SubCommand extends Syntax {

    private final SyntaxTree tree;

    SubCommand(SyntaxTree tree) {
        this.tree = tree
    }

    @Override
    void perform(BrainfuckRunner runner) {
        BrainfuckRunner subRunner = new BrainfuckRunner(runner.memory, new BrainfuckCode(),
            runner.input, runner.outputBuilder)
        subRunner.code.rootTree = tree
        subRunner.run(new UntilEndStrategy())
    }

}

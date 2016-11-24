package net.zomis.brainf.model

import net.zomis.brainf.model.run.UntilEndStrategy

class SubCommand implements BrainfuckCommand {

    private final CodeRetriever commands

    SubCommand(CodeRetriever commands) {
        this.commands = commands
    }

    @Override
    void perform(BrainfuckRunner runner) {
        BrainfuckRunner subRunner = new BrainfuckRunner(runner.memory, new BrainfuckCode(),
            runner.input, runner.outputBuilder)
        subRunner.code.source = commands
        subRunner.run(new UntilEndStrategy())
    }

}

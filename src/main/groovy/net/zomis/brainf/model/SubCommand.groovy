package net.zomis.brainf.model

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
        subRunner.run()
    }

}

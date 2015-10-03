package net.zomis.brainf.model

class SubCommand implements BrainfuckCommand {

    private final List<BrainfuckCommand> commands

    SubCommand(List<BrainfuckCommand> commands) {
        this.commands = new ArrayList<>(commands)
    }

    @Override
    void perform(BrainfuckRunner runner) {
        BrainfuckRunner subRunner = new BrainfuckRunner(runner.memory, new BrainfuckCode(),
            runner.input, runner.outputBuilder)
        subRunner.code.source = new ListCode(commands)
        subRunner.run()
    }

}

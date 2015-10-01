package net.zomis.brainf.model

import net.zomis.brainf.model.classic.BrainfuckCode

class SubCommand implements BrainfuckCommand {

    private final List<BrainfuckCommand> commands

    SubCommand(List<BrainfuckCommand> commands) {
        this.commands = new ArrayList<>(commands)
    }

    @Override
    void perform(BrainfuckRunner runner) {
        BrainfuckRunner subRunner = new BrainfuckRunner(runner.memory, new BrainfuckCode(),
            runner.input, runner.outputBuilder)

        for (BrainfuckCommand command : commands) {
            subRunner.code.addCommand(command)
        }
        subRunner.run()
    }

}

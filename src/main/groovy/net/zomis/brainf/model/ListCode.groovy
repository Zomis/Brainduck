package net.zomis.brainf.model

import net.zomis.brainf.model.classic.BrainfuckConverter
import net.zomis.brainf.model.groovy.GroovySupportConverter

class ListCode implements CodeRetriever {

    private final List<BrainfuckCommand> commands

    ListCode(List<BrainfuckCommand> commands) {
        this.commands = new ArrayList<>(commands)
    }

    @Override
    BrainfuckCommand getCommand(int commandIndex) {
        if (commandIndex < 0 || commandIndex >= commands.size()) {
            return null
        }
        commands.get(commandIndex)
    }

    @Override
    int capacity() {
        commands.size()
    }

    static ListCode create(String code) {
        new ListCode(new ArrayList<BrainfuckCommand>())
            .addCommands(code)
    }

    ListCode addCommands(String string) {
        BrainfuckCodeConverter converter = new GroovySupportConverter(new BrainfuckConverter())
        converter.convert(string, { commands.add it })
        this
    }

}

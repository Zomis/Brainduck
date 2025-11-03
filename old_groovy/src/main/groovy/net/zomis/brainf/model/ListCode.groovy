package net.zomis.brainf.model

import net.zomis.brainf.model.classic.BrainfuckConverter
import net.zomis.brainf.model.groovy.GroovyBFContext
import net.zomis.brainf.model.groovy.GroovySupportConverter

class ListCode implements CodeRetriever {

    private final List<BrainfuckCommand> commands
    private final BrainfuckCodeConverter converter

    ListCode(BrainfuckCodeConverter converter, List<BrainfuckCommand> commands) {
        this.converter = converter
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

    static GroovySupportConverter newGroovyConverter() {
        return new GroovySupportConverter(new GroovyBFContext(), new BrainfuckConverter())
    }

    static ListCode create(String code) {
        create(newGroovyConverter(), code)
    }

    static ListCode create(BrainfuckCodeConverter converter, String code) {
        new ListCode(converter, new ArrayList<BrainfuckCommand>())
                .addCommands(code)
    }

    ListCode addCommands(String string) {
        converter.convert(string, { commands.add it })
        this
    }

}

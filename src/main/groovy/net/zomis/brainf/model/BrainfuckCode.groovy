package net.zomis.brainf.model

import groovy.transform.PackageScope
import net.zomis.brainf.model.codeconv.BrainfuckConverter
import net.zomis.brainf.model.codeconv.GroovySupportConverter

import java.util.function.Predicate
import java.util.regex.Pattern

class BrainfuckCode {
    private final List<BrainfuckCommand> commands = new ArrayList<>();
    int commandIndex

    BrainfuckCode() {

    }

    @PackageScope void gotoMatching(BrainfuckCommand decrease, BrainfuckCommand increase, int direction) {
        commandIndex = findMatching(decrease, increase, direction);
    }

    int findMatching(BrainfuckCommand decrease, BrainfuckCommand increase, int direction) {
        int index = commandIndex
        int matching = 1;
        while (true) {
            index += direction;
            BrainfuckCommand current = getCommandAt(index);
            if (current == null) {
                return -1
            }
            if (current == decrease) {
                matching--;
                if (matching == 0) {
                    break;
                }
            }
            else if (current == increase) {
                matching++;
            }
        }
        index
    }

    void addCommands(String string) {
        BrainfuckCodeConverter converter = new GroovySupportConverter(new BrainfuckConverter())
        converter.convert(string, { addCommand it })
    }

    void addCommand(BrainfuckCommand command) {
        this.commands.add(command)
    }

    void setCommands(String text) {
        commands.clear();
        addCommands(text);
    }

    void resetIndex() {
        commandIndex = 0
    }

    boolean hasMoreCommands() {
        commandIndex < commands.size()
    }

    BrainfuckCommand getNextCommand() {
        if (!hasMoreCommands()) {
            return null;
        }
        commands.get(commandIndex)
    }

    int getCommandCount() {
        commands.size()
    }

    BrainfuckCommand getCommandAt(int index) {
        if (index < 0 || index >= commands.size()) {
            return null
        }
        commands.get(index)
    }

}

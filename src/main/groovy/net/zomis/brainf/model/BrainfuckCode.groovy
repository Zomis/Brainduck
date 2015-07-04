package net.zomis.brainf.model

import groovy.transform.PackageScope
import net.zomis.brainf.BrainFCommand

class BrainfuckCode {
    private final List<BrainFCommand> commands = new ArrayList<>();
    int commandIndex

    @PackageScope void gotoMatching(BrainFCommand decrease, BrainFCommand increase, int direction) {
        int matching = 1;
        while (true) {
            commandIndex += direction;
            BrainFCommand current = commands.get(commandIndex);

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
    }

    void addCommands(String string) {
        string.chars().mapToObj({i -> BrainFCommand.getCommand((char) i)})
                .filter({obj -> obj != null})
                .forEachOrdered({ addCommand(it) });
    }

    void addCommand(BrainFCommand command) {
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

    BrainFCommand getNextCommand() {
        commands.get(commandIndex)
    }
}

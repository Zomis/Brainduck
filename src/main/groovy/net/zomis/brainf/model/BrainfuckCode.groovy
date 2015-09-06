package net.zomis.brainf.model

import groovy.transform.PackageScope
import net.zomis.brainf.BrainFCommand

class BrainfuckCode {
    private final List<BrainFCommand> commands = new ArrayList<>();
    int commandIndex

    @PackageScope void gotoMatching(BrainFCommand decrease, BrainFCommand increase, int direction) {
        commandIndex = findMatching(decrease, increase, direction);
    }

    int findMatching(BrainFCommand decrease, BrainFCommand increase, int direction) {
        int index = commandIndex
        int matching = 1;
        while (true) {
            index += direction;
            BrainFCommand current = commands.get(index);

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

    int getCommandCount() {
        commands.size()
    }

    BrainFCommand getCommandAt(int index) {
        commands.get(index)
    }

}

package net.zomis.brainf.model

import groovy.transform.PackageScope
import net.zomis.brainf.model.BrainfuckCodeConverter
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.classic.BrainfuckConverter
import net.zomis.brainf.model.groovy.GroovySupportConverter

class BrainfuckCode {

    CodeRetriever source
    int commandIndex

    BrainfuckCode() {
    }

    void gotoMatching(BrainfuckCommand decrease, BrainfuckCommand increase, int direction) {
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

    void resetIndex() {
        commandIndex = 0
    }

    boolean hasMoreCommands() {
        getCommandAt(commandIndex) != null
    }

    BrainfuckCommand getNextCommand() {
        if (!hasMoreCommands()) {
            return null;
        }
        source.getCommand(commandIndex)
    }

    int getCommandCount() {
        source.capacity()
    }

    BrainfuckCommand getCommandAt(int index) {
        source.getCommand(index)
    }

}

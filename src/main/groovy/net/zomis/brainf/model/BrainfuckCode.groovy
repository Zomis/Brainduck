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
        return findMatching(commandIndex, decrease, increase, direction)
    }

    /**
     * Searches for a matching command, that is grouped with another command, in the specified direction
     *
     * @param startIndex Index to start search at (exclusive)
     * @param decrease The desired search target
     * @param increase The command that is the opposite of the desired search target, which increases the nesting
     * @param direction How much to change the index each step
     * @return The first found index of the `decrease` that has a nesting level of 0, or -1 if no such target is found
     */
    int findMatching(int startIndex, BrainfuckCommand decrease, BrainfuckCommand increase, int direction) {
        int index = startIndex
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
        if (source == null) {
            return null
        }
        source.getCommand(index)
    }

}

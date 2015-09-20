package net.zomis.brainf.model

import groovy.transform.PackageScope

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

    static final Predicate<String> PATTERN_CODEBLOCK = Pattern.compile('^\\s*\\$\\s*\\{$').asPredicate()
    static final Predicate<String> PATTERN_CODEBLOCK_END = Pattern.compile('^\\s*\\}$').asPredicate()
    static final Predicate<String> PATTERN_CODELINE = Pattern.compile('^\\s*\\$').asPredicate()

    void addCommands(String string) {
        String[] lines = string.split('\n')
        boolean codeEnabled = false
        StringBuilder code = new StringBuilder()
        for (String str : lines) {
            if (!codeEnabled && PATTERN_CODEBLOCK.test(str)) {
                codeEnabled = true
            } else if (codeEnabled && PATTERN_CODEBLOCK_END.test(str)) {
                codeEnabled = false
                addCommand(new SpecialCommand(code.toString()))
                code.setLength(0)
            } else if (!codeEnabled && PATTERN_CODELINE.test(str)) {
                addCommand(new SpecialCommand(str.substring(str.indexOf('$') + 1)))
            } else if (codeEnabled) {
                code.append(str)
                code.append('\n')
            } else {
                str.chars().mapToObj({i -> BrainFCommand.getCommand((char) i)})
                        .filter({obj -> obj != null})
                        .forEachOrdered({ addCommand(it) });
            }
            // out-of-sync for text vs. code index can be fixed by adding multiple NONE fields, or by changing approach
        }
        if (codeEnabled) {
            throw new IllegalArgumentException('Code block was not terminated.')
        }
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

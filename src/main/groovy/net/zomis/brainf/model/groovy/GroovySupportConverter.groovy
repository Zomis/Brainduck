package net.zomis.brainf.model.groovy

import net.zomis.brainf.model.BrainfuckCodeConverter
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.groovy.SpecialCommand

import java.util.function.Consumer
import java.util.function.Predicate
import java.util.regex.Pattern

class GroovySupportConverter implements BrainfuckCodeConverter {

    private final BrainfuckCodeConverter next

    GroovySupportConverter(BrainfuckCodeConverter next) {
        this.next = next;
    }

    static final Predicate<String> PATTERN_CODEBLOCK = Pattern.compile('^\\s*\\$\\s*\\{$').asPredicate()
    static final Predicate<String> PATTERN_CODEBLOCK_END = Pattern.compile('^\\s*\\}$').asPredicate()
    static final Predicate<String> PATTERN_CODELINE = Pattern.compile('^\\s*\\$').asPredicate()



    @Override
    void convert(String string, Consumer<BrainfuckCommand> add) {
        String[] lines = string.split('\n')
        boolean codeEnabled = false
        StringBuilder code = new StringBuilder()
        for (int i = 0; i < lines.length; i++) {
            String str = lines[i]
            if (!codeEnabled && PATTERN_CODEBLOCK.test(str)) {
                codeEnabled = true
                addEmpty(add, str.length() - 1) // adding one Special command, the rest is pure text
            } else if (codeEnabled && PATTERN_CODEBLOCK_END.test(str)) {
                codeEnabled = false
                add.accept(new SpecialCommand(code.toString()))
                code.setLength(0)
                addEmpty(add, str.length())
            } else if (!codeEnabled && PATTERN_CODELINE.test(str)) {
                add.accept(new SpecialCommand(str.substring(str.indexOf('$') + 1)))
                addEmpty(add, str.length() - 1) // adding one Special command, the rest is pure text
            } else if (codeEnabled) {
                code.append(str)
                code.append('\n')
                addEmpty(add, str.length())
            } else {
                next.convert(str, add)
            }
            if (i != lines.length -1) {
                add.accept(BrainFCommand.NONE) // line breaks
            }
            // out-of-sync for text vs. code index is fixed by adding multiple NONE fields
        }
        if (codeEnabled) {
            throw new IllegalArgumentException('Code block was not terminated.')
        }

    }

    private void addEmpty(Consumer<BrainfuckCommand> add, int count) {
        for (int i = 0; i < count; i++) {
            add.accept(BrainFCommand.NONE)
        }
    }

}

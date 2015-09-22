package net.zomis.brainf.model.codeconv

import net.zomis.brainf.model.BrainFCommand
import net.zomis.brainf.model.BrainfuckCodeConverter
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.SpecialCommand

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
        for (String str : lines) {
            if (!codeEnabled && PATTERN_CODEBLOCK.test(str)) {
                codeEnabled = true
            } else if (codeEnabled && PATTERN_CODEBLOCK_END.test(str)) {
                codeEnabled = false
                add.accept(new SpecialCommand(code.toString()))
                code.setLength(0)
            } else if (!codeEnabled && PATTERN_CODELINE.test(str)) {
                add.accept(new SpecialCommand(str.substring(str.indexOf('$') + 1)))
            } else if (codeEnabled) {
                code.append(str)
                code.append('\n')
            } else {
                next.convert(str, add)
            }
            // out-of-sync for text vs. code index can be fixed by adding multiple NONE fields, or by changing approach
        }
        if (codeEnabled) {
            throw new IllegalArgumentException('Code block was not terminated.')
        }

    }
}

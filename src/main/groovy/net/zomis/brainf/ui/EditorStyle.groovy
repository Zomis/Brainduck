package net.zomis.brainf.ui

import net.zomis.brainf.model.classic.BrainFCommand
import org.fxmisc.richtext.StyleSpans
import org.fxmisc.richtext.StyleSpansBuilder

class EditorStyle {

    static int findMatching(String commands, int commandIndex, char decrease, char increase, int direction) {
        int index = commandIndex
        int matching = 1;
        while (true) {
            index += direction;
            if (index < 0 || index >= commands.length()) {
                return -1
            }
            char current = commands.charAt(index);
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

    static StyleSpans<? extends Collection<String>> computeHighlighting(int pos, String text) {
        int startLoop = findMatching(text, pos, '[' as char, ']' as char, -1)
        int endLoop = findMatching(text, pos, ']' as char, '[' as char, 1)
        println "startLoop: $startLoop and end $endLoop pos $pos"
        StyleSpansBuilder<Collection<String>> builder = new StyleSpansBuilder<>()
        int current = 0
        String currentClass = null;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String last = currentClass
            currentClass = cssFor(text, i, ch)
            if (last != null && currentClass != last) {
                println "adding $last from $current to $i total ${i - current}"
                builder.add(Collections.singleton(last), i - current)
                current = i
            }
        }
        if (currentClass == null) {
            currentClass = 'code'
        }
        println "final $currentClass from $current to ${text.length()} total ${text.length() - current}"
        builder.add(Collections.singleton(currentClass), text.length() - current)
        builder.create()
    }

    static String cssFor(String text, int pos, char ch) {
        BrainFCommand command = BrainFCommand.getCommand(ch)
        if (command.isLoop()) {
            return 'loop'
        } else if (command == BrainFCommand.NONE) {
            return 'comment'
        } else {
            return 'code'
        }
    }

}

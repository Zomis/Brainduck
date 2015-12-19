package net.zomis.brainf.ui

import net.zomis.brainf.model.classic.BrainFCommand
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.StyleSpans
import org.fxmisc.richtext.StyleSpansBuilder

class EditorStyle {

    private final CodeArea codeArea

    EditorStyle(CodeArea codeArea) {
        this.codeArea = codeArea
    }

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

    StyleSpans<? extends Collection<String>> computeHighlighting() {
        int pos = codeArea.caretPosition
        String text = codeArea.text
        int startLoop = findMatching(text, pos, '[' as char, ']' as char, -1)
        int endLoop = findMatching(text, pos, ']' as char, '[' as char, 1)
        println "startLoop: $startLoop and end $endLoop pos $pos"
        StyleSpansBuilder<Collection<String>> builder = new StyleSpansBuilder<>()
        int current = 0
        Set<Integer> highlighted = highlights(codeArea)
        String currentClass = null;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String last = currentClass
            currentClass = cssFor(highlighted, i, ch)
            if (last != null && currentClass != last) {
                builder.add(Collections.singleton(last), i - current)
                current = i
            }
        }
        if (currentClass == null) {
            currentClass = 'code'
        }
        builder.add(Collections.singleton(currentClass), text.length() - current)
        builder.create()
    }

    private String cssFor(Set<Integer> highlighted, int pos, char ch) {
        if (highlighted.contains(pos)) {
            return 'highlighted'
        }
        BrainFCommand command = BrainFCommand.getCommand(ch)
        if (command == BrainFCommand.NONE) {
            return 'comment'
        } else if (command.isLoop()) {
            return 'loop'
        } else if (command == BrainFCommand.READ || command == BrainFCommand.WRITE) {
            return 'io'
        } else {
            return 'code'
        }
    }

    void applyHighlights() {
        int[] highlights = highlights(codeArea)
        for (int i : highlights) {
            loopStyle(i)
        }
    }

    static Set<Integer> highlights(CodeArea codeArea) {
        int pos = codeArea.caretPosition
        String before = codeArea.getText(pos - 1, pos)
        String after = codeArea.getText(pos, pos + 1)
        String text = codeArea.text
        Set<Integer> result = new HashSet<>()
        boolean highlighted = false
        if (!before.isEmpty()) {
            BrainFCommand command = BrainFCommand.getCommand(before.charAt(0))
            if (command == BrainFCommand.END_WHILE) {
                println "caret move before"
                highlighted = true
                int startLoop = findMatching(text, pos - 1, '[' as char, ']' as char, -1)
                result << pos - 1
                result << startLoop
                // find match before and highlight
            }
        }
        if (!after.isEmpty()) {
            BrainFCommand command = BrainFCommand.getCommand(after.charAt(0))
            if (command == BrainFCommand.WHILE) {
                println "caret move after"
                highlighted = true
                int endLoop = findMatching(text, pos, ']' as char, '[' as char, 1)
                result << pos
                result << endLoop
                // find match after and highlight
            }
        }
        if (!highlighted) {
            int startLoop = findMatching(text, pos, '[' as char, ']' as char, -1)
            int endLoop = findMatching(text, pos - 1, ']' as char, '[' as char, 1)
            println "caret move loops $startLoop $endLoop"
            result << startLoop
            result << endLoop
        }
        result
    }

    void loopStyle(int position) {
        if (position == -1) {
            return
        }
        Collection<String> style = Arrays.asList('loop', 'highlighted')
        codeArea.setStyle(position, position + 1, style)
    }

    static EditorStyle setup(CodeArea codeArea, Runnable onModified, Runnable onCaretChange) {
        EditorStyle styleApplier = new EditorStyle(codeArea)
        codeArea.richChanges().subscribe({change ->
//            println "rich change $change properties ${change.properties} pos ${change.position} inserted " +
//                    "${change.inserted} removed ${change.removed}"
            if (change.removed.text != change.inserted.text) {
                onModified.run()
            }
            def highlighting = styleApplier.computeHighlighting()
            codeArea.setStyleSpans(0, highlighting)
        })
        // codeArea.caretPositionProperty().addListener()
        codeArea.setOnKeyReleased({
            onCaretChange.run()
            styleApplier.applyHighlights()
        })
        styleApplier
    }
}

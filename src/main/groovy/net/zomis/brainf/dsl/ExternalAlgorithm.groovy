package net.zomis.brainf.dsl

import net.zomis.brainf.model.BrainFCommand

class ExternalAlgorithm {
    private Set<String> varNames = new HashSet<>()
    private String code
    private final String name

    ExternalAlgorithm(String name) {
        this.name = name
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i)
            assert BrainFCommand.getCommand((char) ch) == BrainFCommand.NONE :
                'Algorithm name ' + name + ' may not contain the Brainfuck code character "' + ch + '"'
        }
    }

    void variables(String... names) {
        for (String str : names) {
            assert varNames.add(str)
        }
    }

    void temp(String... temp) {
        for (String str : temp) {
            assert varNames.add(str)
        }
    }

    void code(String text) {
        StringBuilder varname = new StringBuilder()
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i)
            if (Character.isAlphabetic((int) ch) || Character.isDigit((int) ch)) {
                varname.append(ch)
            }
            if (isBFChar(ch)) {
                checkVarname(varname)
            }
        }
        this.code = text
    }

    private String checkVarname(StringBuilder stringBuilder) {
        String str = stringBuilder.toString()
        if (!str.isEmpty()) {
            assert varNames.contains(str)
            stringBuilder.setLength(0)
        }
        return str
    }

    static boolean isBFChar(char ch) {
        return BrainFCommand.getCommand(ch) != BrainFCommand.NONE
    }

    String apply(Map<String, Integer> variables) {
        int position = 0
        StringBuilder strBuilder = new StringBuilder()
        StringBuilder codeOutput = new StringBuilder()
        codeOutput.append(' Algorithm ' + name + ' BEGIN ')
        Stack<Integer> whileEnterPositions = new Stack<>()
        for (int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i)
            if (Character.isAlphabetic((int) ch) || Character.isDigit((int) ch)) {
                strBuilder.append(ch)
            }
            if (isBFChar(ch)) {
                String varName = checkVarname(strBuilder)
                position = gotoVariable(position, variables, varName, codeOutput)
                codeOutput.append(ch)
            }
            if (ch == '[') {
                whileEnterPositions.add(position)
            } else if (ch == ']') {
                int whilePos = whileEnterPositions.pop()
                assert position == whilePos : "While position mismatch. Started at $whilePos but ended at $position"
            } else if (ch == '>') {
                position++
            } else if (ch == '<') {
                position--
            }
        }
        String varName = checkVarname(strBuilder)
        gotoVariable(position, variables, varName, codeOutput)
        codeOutput.append(' Algorithm ' + name + ' END ')
        return codeOutput.toString()
    }

    private static int gotoVariable(int current, Map<String, Integer> variables, String variable, StringBuilder codeOutput) {
        if (variable.isEmpty()) {
            return current
        }
        int newPosition = variables.get(variable)
        int oldPosition = current
        int diff = newPosition - oldPosition
        if (diff > 0) {
            codeOutput.append('>' * diff)
        } else {
            codeOutput.append('<' * -diff)
        }
        newPosition
    }
}

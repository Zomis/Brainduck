package net.zomis.brainf.tools.textgen

import java.util.stream.Collectors

class TextGenerator {

    static List<TextCellGroup> groupIntoCells(int[] values, int cells) {
        List<TextCellGroup> result = []

        def textCellGroup = new TextCellGroup(values[0])
        Arrays.stream(values).each {textCellGroup.add(it)}
        result << textCellGroup

        for (int i = 0; i < cells; i++) {
            TextCellGroup splittingGroup = result.stream().max(Comparator.<TextCellGroup>comparingInt({it.maxDiff})).get()
            if (splittingGroup.maxDiff <= 0) {
                break
            }
            int splittedValue = splittingGroup.valueToSplit
            def newCellGroup = new TextCellGroup(splittedValue)
            List<TextCellGroup> newResults = result.stream().mapToInt({it.startValue})
                    .mapToObj({new TextCellGroup(it)}).collect(Collectors.toList())
            newResults << newCellGroup

            Arrays.stream(values).forEachOrdered({value ->
                newResults.stream().min(Comparator.<TextCellGroup>comparingInt({it.diffIfAdd(value)})).get().add(value)
            })

            result = newResults
        }
        result
/*
    approach A:
    [32, 33, 44, 72, 87, 100, 101, 108, 108, 108, 111, 111, 114] with 3 cells
    calculate MAX - MIN + 1 (114 - 32 + 1) to get the number of values
    calculate STEP = VALUE_SPAN / CELLS to a double
    calculate MIN + STEP and MIN + STEP*2... and set those to the values

    approach B:
    [72, 101, 108, 108, 111, 44, 32, 87, 111, 114, 108, 100, 33] with 3 cells
    calculate diffs
    [0, 29, 7, 0, 3, 67, 12, 55, 24, 3, 6, 8, 67]
    take the highest diff and separate those two into two groups, giving:

    [44] and [72]

    now calculate the diffs and group by putting into the closest group
    [A 0, A 29, A 7, A 0, A 3, B 0, B 12, A 24, A 24, A 3, A 6, A 8, B 1]

    giving the two groups
    [72, 101, 108, 108, 111, 87, 111, 114, 108, 100]
    [44, 32, 33]

    take the highest diff again and create another group, re-checking the group for all values
    [72, 101, 108, 108, 111, 44, 32, 87, 111, 114, 108, 100, 33]
    [A 0, C 0, C 7, C 7, C 3, B 0, B 12, A 15, C 0, C 3, C 6, C 8, B 1]
*/
    }

    void writeOnce(String text, int cells) {
        println "Write once with $cells cells: $text"
        // Get ASCII values for text
        int[] values = text.chars().toArray()

        // Group into maximum `cells` groups
        List<TextCellGroup> grouped = groupIntoCells(values, cells)

        // Use multiplication to create the first number in each group as it appear in the string
        int[] startNumbers = grouped.stream().mapToInt({it.startValue}).toArray()
        String multiplication = createMultiplicationLoopForNumbers(startNumbers)

        // Go to the different cells and print and modify
        String printRest = printValuesWithGroups(values, grouped)

        // TODO: Figure out the best order for the cells

        println values
        println grouped
        println multiplication
        println printRest
        println()
    }

    String printValuesWithGroups(int[] values, List<TextCellGroup> textCellGroups) {
        int[] groupIndexes = new int[textCellGroups.size()]
        Arrays.fill(groupIndexes, -1)
        int oldGroup = 0
        StringBuilder str = new StringBuilder()
        for (int value : values) {
            int groupNumber = findGroupForValue(textCellGroups, value, groupIndexes)
            if (oldGroup != groupNumber) {
                str.append('\n')
                str.append(change(groupNumber - oldGroup, '>', '<'))
            }
            TextCellGroup group = textCellGroups[groupNumber]
            int oldIndex = groupIndexes[groupNumber]
            int oldValue = value
            if (oldIndex != -1) {
                oldValue = group.get(oldIndex)
            }
            groupIndexes[groupNumber] = groupIndexes[groupNumber] + 1
            int diff = value - oldValue
            str.append(change(diff, '+', '-'))
            str.append('.')
            oldGroup = groupNumber
        }

        /*
        * for each value:
        * 1. go to the group for that value
        * 2. adjust the current value for that group to the value and print
        */
        str.toString()
    }

    static int findGroupForValue(List<TextCellGroup> groups, int value, int[] groupIndexes) {
        for (int i = 0; i < groups.size(); i++) {
            int pos = groupIndexes[i] + 1
            TextCellGroup group = groups[i]
            if (group.size() > pos && group.get(pos) == value) {
                return i
            }
        }
        throw new RuntimeException("Unable to find group for value $value indexes $groupIndexes groups $groups")
    }

    static String change(int diff, String positive, String negative) {
        String ch = diff > 0 ? positive : negative
        return ch * Math.abs(diff)
    }

    static String createMultiplicationLoopForNumbers(int[] values) {
        int divisor = TextGenFactorization.factorize(values)
        StringBuilder str = new StringBuilder()
        str.append('+' * divisor)
        str.append('[-\n')

        int[] bs = TextGenFactorization.calcBs(divisor, values)
        for (int b : bs) {
            int count = b
            str.append('    >')
            str.append('+' * count)
            str.append('\n')
        }
        str.append('<' * values.length)
        str.append(']\n')

        int[] cs = TextGenFactorization.calcC(divisor, bs, values)
        for (int c : cs) {
            str.append('>')
            str.append(change(c, '+', '-'))
            str.append('\n')
        }
        str.append('<' * (values.length - 1))
        str.toString()
    }

    void writeMultiple(String text, int cells) {
        // same as write once but reset values either before or after each write
        // separate result into "setup" and "repeat"
    }


    void saveToTape(String text) {
        println "Save to tape: $text"
        int[] startNumbers = text.chars().toArray()
        String multiplication = createMultiplicationLoopForNumbers(startNumbers)
        println multiplication
    }

    public static void main(String[] args) {
        TextGenerator gen = new TextGenerator()
        String str = '"Wikipedia is the best thing ever. Anyone in the world can write anything they want about any subject, so you know you are getting the best possible information." - Michael Scott'
        String helloCommaWorld = 'Hello, World!'
        String helloWorld = 'Hello World!'
        String fizz = 'Fizz'
        String buzz = 'Buzz'
        gen.saveToTape(fizz)
        gen.saveToTape(buzz)
        for (int i = 0; i < 4; i++) {
            gen.writeOnce(helloCommaWorld, i)
        }
        for (int i = 0; i < 4; i++) {
            gen.writeOnce(helloWorld, i)
        }

        gen.writeOnce(str, 1)
        gen.writeOnce(str, str.length())
    }


}

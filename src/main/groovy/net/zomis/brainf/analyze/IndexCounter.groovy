package net.zomis.brainf.analyze

import java.util.concurrent.atomic.AtomicInteger

/**
 * For counting the number of times something occurs at a specific index.
 *
 * Can be used with for example while loops analysis, <code>[20 * 3, 1 * 4, 20 * 2] --> {1: 4, 20: 5}</code>
 */
class IndexCounter {

    List<Integer> results = []
    Stack<AtomicInteger> started = new Stack<>()

    void begin() {
        started.push(new AtomicInteger(0))
    }

    String toString() {
        printCompactList(results)
    }

    private static String countString(int count, Object value) {
        count >= 2 ? "$value * $count" : "$value"
    }

    private static String printCompactList(List<?> values) {
        if (values.isEmpty()) {
            return '[]'
        }
        StringBuilder str = new StringBuilder()
        int count = 0
        Object value = null
        str.append '['
        boolean shouldPrintComma = false
        for (Object i : values) {
            if (Objects.equals(i, value)) {
                count++
            } else {
                if (shouldPrintComma) {
                    str.append ', '
                }
                if (count > 0) {
                    shouldPrintComma = true
                    str.append countString(count, value)
                }
                count = 1
                value = i
            }
        }
        if (shouldPrintComma) {
            str.append ', '
        }
        str.append countString(count, value)
        str.append ']'
        str.toString()
    }

    void increase() {
        this.started.peek().incrementAndGet()
    }

    void add(int i) {
        this.results.add(i)
    }

    void finishLast() {
        add(this.started.pop().get())
    }

}

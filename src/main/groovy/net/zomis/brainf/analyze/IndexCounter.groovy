package net.zomis.brainf.analyze

import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Function
import java.util.stream.Stream

/**
 * For counting the number of times something occurs at a specific index.
 *
 * Can be used with for example while loops analysis, <code>[20 * 3, 1 * 4, 20 * 2] --> {1: 4, 20: 5}</code>
 */
class IndexCounter {

    int forIndex
    List<Integer> results = []
    Stack<AtomicInteger> started = new Stack<>()

    void begin() {
        started.push(new AtomicInteger(0))
    }

    String toString() {
        printCompactList(results, {String.valueOf(it)})
    }

    private static <T> String countString(int count, T value, Function<T, String> toStringFunction) {
        String val = toStringFunction.apply(value)
        count >= 2 ? "$val * $count" : "$val"
    }

    private static <T> String printCompactList(List<T> values, Function<T, String> toStringFunction) {
        if (values.isEmpty()) {
            return '[]'
        }
        StringBuilder str = new StringBuilder()
        int count = 0
        T value = null
        str.append '['
        boolean shouldPrintComma = false
        for (T i : values) {
            if (Objects.equals(i, value)) {
                count++
            } else {
                if (shouldPrintComma) {
                    str.append ', '
                }
                if (count > 0) {
                    shouldPrintComma = true
                    str.append countString(count, value, toStringFunction)
                }
                count = 1
                value = i
            }
        }
        if (shouldPrintComma) {
            str.append ', '
        }
        str.append countString(count, value, toStringFunction)
        str.append ']'
        str.toString()
    }

    private List<String> tagNames(Function<Integer, String> toStringFunction) {
        if (results.isEmpty()) {
            return []
        }
        List<String> result = []
        int count = 0
        Integer value = null
        for (Integer i : results) {
            if (Objects.equals(i, value)) {
                count++
            } else {
                if (count > 0) {
                    result << countString(count, value, toStringFunction)
                }
                count = 1
                value = i
            }
        }
        result << countString(count, value, toStringFunction)
        result
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

    Stream<String> tags(String prefix, Function<Integer, String> indexToStringFunction) {
        tagNames(indexToStringFunction).stream().map({prefix + ' ' + it})
    }

}

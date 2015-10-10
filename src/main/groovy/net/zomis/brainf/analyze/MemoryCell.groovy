package net.zomis.brainf.analyze

import net.zomis.brainf.model.groovy.GroovyBFContext

import java.util.function.BiConsumer
import java.util.function.BiFunction
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector
import java.util.stream.Stream

class MemoryCell {

    final int index
    int value
    long readCount
    long writeCount
    IndexCounter prints = new IndexCounter()
    IndexCounter userInputs = new IndexCounter()
    IndexCounter whileLoopStart = new IndexCounter()
    IndexCounter whileLoopContinue = new IndexCounter()
    IndexCounter whileLoopEnd = new IndexCounter()

    MemoryCell(int index) {
        this.index = index
    }

    String toString(GroovyBFContext groovy) {
        int value = this.value
        String hexAddress = String.format("%04X", index)
        String decAddress = String.format("%06d", index)

        boolean specialChar = value >= 0 && value <= 13
        char chrValue = specialChar ? 32 : value;
        String decValue = String.format("%6d", value);

        String reads = String.format("%6d", this.readCount);
        String writes = String.format("%6d", this.writeCount);
        Map<String, Integer> tagsCount = resolveTags(groovy)
        String tags = tagsCount.isEmpty() ? '' : tagsCount.toString()
        "Hex $hexAddress\tDec $decAddress\tValue $decValue '$chrValue' \t" +
            "Reads: $reads\tWrites: $writes $tags".toString()
    }

    Map<String, Integer> resolveTags(GroovyBFContext groovy) {
        Function<Integer, String> loopNames = {i ->
            String name = groovy.getLoopName(i)
            return name ? name : ''
        }
        Stream.of(prints.tags('print', loopNames),
                userInputs.tags('userInput', loopNames),
                whileLoopStart.tags('loop-begin', loopNames),
                whileLoopContinue.tags('loop-continue', loopNames),
                whileLoopEnd.tags('loop-end', loopNames))
            .flatMap({it})
            .sorted()
            .collect(countingCollector())
    }

    public static <T> Collector<T, ?, Map<T, Integer>> countingCollector() {
        Supplier<Map<T, Integer>> supplier = {new TreeMap<>()}
        BiFunction<Integer, Integer, Integer> plus = {a, b -> a + b}
        BiConsumer<Map<T, Integer>, T> accumulator = {map, obj -> map.merge(obj, 1, plus)}
        BinaryOperator<Map<T, Integer>> combiner = {Map<T, Integer> mapA, Map<T, Integer> mapB ->
            mapA.entrySet().stream().forEach({Map.Entry<T, Integer> ee ->
                mapB.merge(ee.key, ee.value, plus)
            })
        }
        Collector.Characteristics[] characteristics = [Collector.Characteristics.CONCURRENT,
               Collector.Characteristics.UNORDERED] as Collector.Characteristics[]
        Collector.of(supplier, accumulator, combiner, characteristics)
    }

}
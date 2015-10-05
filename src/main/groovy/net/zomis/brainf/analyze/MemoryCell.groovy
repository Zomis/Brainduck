package net.zomis.brainf.analyze

import java.util.function.BiConsumer
import java.util.function.BiFunction
import java.util.function.BinaryOperator
import java.util.function.Supplier
import java.util.stream.Collector
import java.util.stream.Stream

class MemoryCell {

    final int index
    int value
    long readCount
    long writeCount
    IndexCounter prints = new IndexCounter()
    IndexCounter whileLoopStart = new IndexCounter()
    IndexCounter whileLoopContinue = new IndexCounter()
    IndexCounter whileLoopEnd = new IndexCounter()

    MemoryCell(int index) {
        this.index = index
    }

    @Override
    String toString() {
        int value = this.value
        String hexAddress = String.format("%04X", index)
        String decAddress = String.format("%06d", index)

        boolean specialChar = value >= 0 && value <= 13
        char chrValue = specialChar ? 32 : value;
        String decValue = String.format("%6d", value);

        String reads = String.format("%6d", this.readCount);
        String writes = String.format("%6d", this.writeCount);
        Map<String, Integer> tagsCount = resolveTags()
        String tags = tagsCount.isEmpty() ? '' : tagsCount.toString()
        "Hex $hexAddress\tDec $decAddress\tValue $decValue '$chrValue' \t" +
            "Reads: $reads\tWrites: $writes $tags".toString()
    }

    Map<String, Integer> resolveTags() {
        Stream.of(prints.tags('print'), whileLoopStart.tags('loop-start'),
                whileLoopContinue.tags('loop-continue'), whileLoopEnd.tags('loop-end'))
            .flatMap({it})
            .collect(countingCollector())
    }

    public static <T> Collector<T, ?, Map<T, Integer>> countingCollector() {
        Supplier<Map<T, Integer>> supplier = {new HashMap<>()}
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
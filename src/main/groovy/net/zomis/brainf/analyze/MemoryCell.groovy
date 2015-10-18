package net.zomis.brainf.analyze

import net.zomis.brainf.model.groovy.GroovyBFContext

import java.util.function.BiConsumer
import java.util.function.BiFunction
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector
import java.util.stream.Collectors
import java.util.stream.Stream

class MemoryCell {

    final int index
    boolean used
    IndexCounter prints = new IndexCounter('print')
    IndexCounter userInputs = new IndexCounter('userInput')

    private final Map<Class<?>, Object> analysis = [:]

    MemoryCell(int index) {
        this.index = index
    }

    public <T> T data(BrainfuckAnalyzer analyzer, Class<T> clazz) {
        Object obj = analysis.get(clazz)
        if (!obj) {
            obj = analyzer.createMemoryData()
            analysis.put(clazz, obj)
        }
        if (!obj) {
            throw new IllegalStateException("Analyzer $analyzer does not create a memory data object of $clazz")
        }
        return (T) obj;
    }

    public <T> T data(Class<T> clazz) {
        Object obj = analysis.get(clazz)
        return (T) obj;
    }

    String toString(GroovyBFContext groovy) {
        String hexAddress = String.format("%04X", index)
        String decAddress = String.format("%06d", index)

        String analysis = analysis.values().stream().map({obj -> String.valueOf(obj)}).collect(Collectors.joining('\t'))
        Map<String, Integer> tagsCount = resolveTags(groovy)
        String tags = tagsCount.isEmpty() ? '' : tagsCount.toString()
        "Hex $hexAddress\tDec $decAddress\t" +
            analysis +
            "$tags".toString()
    }

    Map<String, Integer> resolveTags(GroovyBFContext groovy) {
        Function<Integer, String> loopNames = {i ->
            groovy.getLoopName(i)
        }
        Stream<CellTagger> taggers = this.analysis.values().stream()
            .filter({it instanceof CellTagger})
            .map({it as CellTagger})
        Stream<CellTagger> oldTaggers = Stream.of(prints, userInputs)
        Stream.concat(taggers, oldTaggers)
            .flatMap({it.tags(loopNames)})
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
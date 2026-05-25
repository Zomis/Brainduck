package net.zomis.brainf.analyze

import kotlin.reflect.KClass

class MemoryCell(val index: Int) {

    private val analysis = mutableMapOf<KClass<*>, Any>()

    fun <T : Any> data(analyzer: BrainfuckAnalyzer, clazz: KClass<T>): T {
        var obj = analysis[clazz]
        if (obj == null) {
            obj = analyzer.createMemoryData()
            analysis[clazz] = obj
        }
        return obj as T
    }

    fun <T : Any> data(clazz: KClass<T>): T? {
        return analysis[clazz] as T
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
        Map<String, Integer> result = taggers
            .flatMap({it.tags(loopNames)})
            .sorted()
            .collect(countingCollector())
        Map<String, Integer> cellNames = groovy.getCellNames(this.index)
        if (cellNames != null) {
            cellNames.entrySet().forEach({result.merge(it.key, it.value, {a, b -> a + b})})
        }
        result
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
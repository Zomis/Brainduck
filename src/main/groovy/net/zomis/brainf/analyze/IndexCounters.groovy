package net.zomis.brainf.analyze

import java.util.stream.Stream

class IndexCounters {
    private final Map<Integer, IndexCounter> counters = [:]
    private final Stack<IndexCounter> recentCounters = new Stack<>()

    Stream<Map.Entry<Integer, IndexCounter>> sorted() {
        counters.entrySet().stream().sorted(Comparator.comparingInt({it.key}))
    }

    List<Integer> getAt(int index) {
        new ArrayList<>(counters[index].results)
    }

    void begin(IndexCounter counter) {
        assert counter : 'Cannot begin on null counter'
        recentCounters.push(counter)
        counter.begin()
    }

    int size() {
        counters.size()
    }

    void finishLast() {
        recentCounters.pop().finishLast()
    }

    IndexCounter recent() {
        recentCounters.peek()
    }

    IndexCounter getOrCreate(int i) {
        counters.putIfAbsent(i, new IndexCounter())
        counters.get(i)
    }

}

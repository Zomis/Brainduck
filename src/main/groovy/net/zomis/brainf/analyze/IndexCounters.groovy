package net.zomis.brainf.analyze

import java.util.stream.Stream

class IndexCounters {
    private final Map<Integer, IndexCounter> counters = [:]
    private final Deque<IndexCounter> recentCounters = new LinkedList<>()

    Stream<Map.Entry<Integer, IndexCounter>> sorted() {
        counters.entrySet().stream().sorted(Comparator.comparingInt({it.key}))
    }

    List<Integer> getAt(int index) {
        new ArrayList<>(counters[index].results)
    }

    void begin(IndexCounter counter) {
        assert counter : 'Cannot begin on null counter'
        recentCounters.addLast(counter)
        counter.begin()
    }

    int size() {
        counters.size()
    }

    void finishLast() {
        recentCounters.removeLast().finishLast()
    }

    IndexCounter recent() {
        recentCounters.peekLast()
    }

    IndexCounter getOrCreate(int i) {
        def idxCounter = new IndexCounter('')
        idxCounter.forIndex = i
        counters.putIfAbsent(i, idxCounter)
        counters.get(i)
    }

}

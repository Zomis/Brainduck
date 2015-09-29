package net.zomis.brainf.model

class SpecialDelegate {

    private BrainfuckRunner runner

    SpecialDelegate(BrainfuckRunner runner) {
        this.runner = runner
    }

    int memBack(int count) {
        runner.memory.getMemory(runner.memory.memoryIndex - count)
    }

    int getValue() {
        runner.memory.getMemory()
    }

    int getPosition() {
        runner.memory.memoryIndex
    }

    def memory(int count) {
        int index = runner.memory.memoryIndex
        [offset: {int forward ->
            int[] values = runner.memory.getMemoryArray(index + forward, count)
            new ArrayBuilder(values)
        }, offsetBackward: {int backward ->
            int[] values = runner.memory.getMemoryArray(index - backward, count)
            new ArrayBuilder(values)
        }]
    }

    ArrayBuilder values(int firstValue) {
        return new ArrayBuilder(firstValue)
    }

}

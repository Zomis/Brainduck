package net.zomis.brainf.model

class SpecialDelegate {

    private BrainfuckRunner runner

    SpecialDelegate(BrainfuckRunner runner) {
        this.runner = runner
    }

    int memBack(int count) {
        runner.memory.getMemory(runner.memory.memoryIndex - count)
    }

}

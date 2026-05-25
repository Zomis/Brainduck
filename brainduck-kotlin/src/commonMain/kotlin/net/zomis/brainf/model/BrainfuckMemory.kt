package net.zomis.brainf.model

const val DEFAULT_MEMORY_SIZE = 0x1000

class BrainfuckMemory(
    val size: Int = DEFAULT_MEMORY_SIZE
) {

    var memoryIndex: Int = 0
        private set
    private val memory: Array<Int> = Array(size) { 0 }
    var minValue = 0
    var maxValue = 255

    val value: Int get() = memory[memoryIndex]

    fun changeMemory(i: Int) {
        memoryIndexWraparound()
        memory[memoryIndex] += i
        memoryBoundsCheck()
    }

    internal fun memoryIndexWraparound() {
        if (memoryIndex < 0) {
            memoryIndex += memory.size
        }
        if (memoryIndex >= memory.size) {
            memoryIndex -= memory.size
        }
    }

    fun setMemoryIndex(newIndex: Int) {
        this.memoryIndex = newIndex
        memoryIndexWraparound()
    }

    fun getMemoryArray(fromIndex: Int, length: Int): Array<Int> {
        return memory.copyOfRange(fromIndex, fromIndex + length)
    }

    fun getMemorySize(): Int {
        return memory.size
    }

    fun getMemory(index: Int): Int {
        return memory[index]
    }

    fun setMemory(value: Int) {
        memory[memoryIndex] = value;
        memoryBoundsCheck()
    }

    private fun memoryBoundsCheck() {
        var diff = memory[memoryIndex] - minValue
        if (diff < 0) {
            memory[memoryIndex] = maxValue + diff + 1
        }

        diff = maxValue - memory[memoryIndex]
        if (diff < 0) {
            memory[memoryIndex] = minValue - diff - 1
        }
    }

    fun reset() {
        memory.fill(0)
        memoryIndex = 0
    }

    fun values(from: Int, toExclusive: Int): Array<Int> {
        return memory.copyOfRange(from, toExclusive)
    }

}

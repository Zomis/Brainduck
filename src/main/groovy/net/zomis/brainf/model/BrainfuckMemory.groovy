package net.zomis.brainf.model

import groovy.transform.PackageScope

class BrainfuckMemory {

    public static final int DEFAULT_MEMORY_SIZE = 0x1000;

    private int memoryIndex
    private int[] memory
    int size = DEFAULT_MEMORY_SIZE
    int minValue = 0
    int maxValue = 255

    BrainfuckMemory() {
        this(DEFAULT_MEMORY_SIZE)
    }

    BrainfuckMemory(int size) {
        this.size = size
        this.memory = new int[size]
    }

    void changeMemory(int i) {
        memoryIndexWraparound();
        memory[memoryIndex] += i;
        memoryBoundsCheck()
    }

    @PackageScope void memoryIndexWraparound() {
        if (memoryIndex < 0) {
            memoryIndex += memory.length;
        }
        if (memoryIndex >= memory.length) {
            memoryIndex -= memory.length;
        }
    }

    public void setMemoryIndex(int newIndex) {
        this.memoryIndex = newIndex
        memoryIndexWraparound()
    }

    public int[] getMemoryArray(int fromIndex, int length) {
        return Arrays.copyOfRange(memory, fromIndex, fromIndex + length);
    }

    public int getMemorySize() {
        return memory.length;
    }

    public int getMemory(int index) {
        return memory[index];
    }

    public int getValue() {
        return memory[memoryIndex];
    }

    public void setMemory(int value) {
        memory[memoryIndex] = value;
        memoryBoundsCheck()
    }

    void memoryBoundsCheck() {
        int diff = memory[memoryIndex] - minValue
        if (diff < 0) {
            memory[memoryIndex] = maxValue + diff
        }

        diff = maxValue - memory[memoryIndex]
        if (diff > 0) {
            memory[memoryIndex] = maxValue - diff
        }
    }

    public int getMemoryIndex() {
        return memoryIndex;
    }

    void reset() {
        Arrays.fill(memory, (byte) 0);
        memoryIndex = 0;
    }
}

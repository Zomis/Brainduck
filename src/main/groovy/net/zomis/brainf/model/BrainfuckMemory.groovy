package net.zomis.brainf.model

import groovy.transform.PackageScope

class BrainfuckMemory {

    public static final int DEFAULT_MEMORY_SIZE = 0x1000;

    int memoryIndex
    private int[] memory
    int size = DEFAULT_MEMORY_SIZE
    int minValue = 0
    int maxValue = 255

    BrainfuckMemory(int size) {
        this(size: size)
    }

    BrainfuckMemory(Map map) {
        map?.each { k, v -> this[k] = v }
        this.memory = new int[this.size]
    }

    void changeMemory(int i) {
        checkMemoryIndex();
        memory[memoryIndex] += i;
        memoryBoundsCheck()
    }

    @PackageScope void checkMemoryIndex() {
        if (memoryIndex < 0) {
            memoryIndex += memory.length;
        }
        if (memoryIndex >= memory.length) {
            memoryIndex -= memory.length;
        }
    }

    public byte[] getMemoryArray(int fromIndex, int length) {
        return Arrays.copyOfRange(memory, fromIndex, fromIndex + length);
    }

    public int getMemorySize() {
        return memory.length;
    }

    public int getMemory(int index) {
        return memory[index];
    }

    public int getMemory() {
        return memory[memoryIndex];
    }

    public void setMemory(int value) {
        memory[memoryIndex] = value;
        memoryBoundsCheck()
    }

    void memoryBoundsCheck() {
        // TODO: compare current memory with minValue and maxValue
    }

    public int getMemoryIndex() {
        return memoryIndex;
    }

    void reset() {
        Arrays.fill(memory, (byte) 0);
        memoryIndex = 0;
    }
}

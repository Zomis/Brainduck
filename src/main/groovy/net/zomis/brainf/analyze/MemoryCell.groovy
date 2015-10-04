package net.zomis.brainf.analyze

class MemoryCell {

    final int index
    int value
    long readCount
    long writeCount

    MemoryCell(int index) {
        this.index = index
    }

}

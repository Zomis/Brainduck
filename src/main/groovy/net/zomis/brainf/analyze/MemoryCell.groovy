package net.zomis.brainf.analyze

class MemoryCell {

    final int index
    int value
    long readCount
    long writeCount
    IndexCounter prints = new IndexCounter()

    MemoryCell(int index) {
        this.index = index
    }

    @Override
    String toString() {
        int value = this.value
        String hexAddress = String.format("%04X", index)
        String decAddress = String.format("%06d", index)

        boolean specialChar = value >= 0 && value <= 13
        char chrValue = specialChar ? 32 : value;
        String decValue = String.format("%6d", value);

        String reads = String.format("%6d", this.readCount);
        String writes = String.format("%6d", this.writeCount);
        "Hex $hexAddress\tDec $decAddress\tValue $decValue '$chrValue' \tReads: $reads\tWrites: $writes".toString()
    }

}

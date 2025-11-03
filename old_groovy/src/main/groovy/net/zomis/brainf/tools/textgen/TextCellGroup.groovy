package net.zomis.brainf.tools.textgen

class TextCellGroup {
    private final List<Integer> values = []
    private final int startValue
    private int maxDiff
    private int valueToSplit

    TextCellGroup(int startValue) {
        this.startValue = startValue
    }

    TextCellGroup add(int value) {
        if (!values.isEmpty()) {
            int lastValue = values[values.size() - 1]
            int diff = Math.abs(lastValue - value)
            if (diff > maxDiff) {
                maxDiff = diff
                valueToSplit = value
            }
        }
        values << value
        this
    }

    int diffIfAdd(int value) {
        if (values.isEmpty()) {
            return Math.abs(startValue - value)
        }
        int lastValue = values[values.size() - 1]
        int diff = Math.abs(lastValue - value)
        diff
    }

    int getMaxDiff() {
        this.@maxDiff
    }

    int getValueToSplit() {
        this.@valueToSplit
    }

    int getStartValue() {
        this.@startValue
    }

    @Override
    String toString() {
        this.values.toString()
    }

    int get(int index) {
        if (this.values.size() <= index) {
            throw new RuntimeException("Error accessing index $index in $this")
        }
        this.values[index]
    }

    int size() {
        this.values.size()
    }
}

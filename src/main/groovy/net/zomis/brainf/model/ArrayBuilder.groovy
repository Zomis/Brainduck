package net.zomis.brainf.model

class ArrayBuilder {

    private final List<Integer> values

    ArrayBuilder(int firstValue) {
        this.values = [firstValue]
    }

    ArrayBuilder(int[] values) {
        this.values = new ArrayList<>(values.length)
        for (int i : values) {
            this.values.add i
        }
    }

    def methodMissing(String name, args) {
        values.add Integer.parseInt(name)
        if (args.length == 1) {
            values.add args[0] as int
        }
        this
    }

    def propertyMissing(String name) {
        values.add Integer.parseInt(name)
        this
    }

    @Override
    public int hashCode() {
        values.hashCode()
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ArrayBuilder) {
            ArrayBuilder o = other as ArrayBuilder
            return this.values.equals(o.values)
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        values.toString()
    }

}

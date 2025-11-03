package net.zomis.brainf.tools

class WeightedPerformanceScorer {

    private final double codeWeight
    private final double runtimeWeight

    WeightedPerformanceScorer(double codeWeight, double runtimeWeight) {
        this.codeWeight = codeWeight
        this.runtimeWeight = runtimeWeight
    }

    int score(int codeLength, int runtimeLength) {
        return (int) (codeWeight * codeLength + runtimeWeight * runtimeLength)
    }

}
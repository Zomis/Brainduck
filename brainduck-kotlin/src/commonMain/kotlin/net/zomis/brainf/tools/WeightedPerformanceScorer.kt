package net.zomis.brainf.tools

class WeightedPerformanceScorer(
    val codeWeight: Double,
    val runtimeWeight: Double,
) {

    fun score(codeLength: Int, runtimeLength: Int): Double {
        return codeWeight * codeLength + runtimeWeight * runtimeLength
    }

}
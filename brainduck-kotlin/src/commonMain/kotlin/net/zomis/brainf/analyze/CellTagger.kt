package net.zomis.brainf.analyze

fun interface CellTagger {
    fun tags(indexToStringFunction: (Int) -> String): Sequence<String>
}

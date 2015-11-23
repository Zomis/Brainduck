package net.zomis.brainf.analyze

import java.util.function.Function
import java.util.stream.Stream

interface CellTagger {

    Stream<String> tags(Function<Integer, String> indexToStringFunction)

}

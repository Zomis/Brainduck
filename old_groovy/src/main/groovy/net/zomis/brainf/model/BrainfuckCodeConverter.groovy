package net.zomis.brainf.model

import java.util.function.Consumer

interface BrainfuckCodeConverter {

    void convert(String code, Consumer<BrainfuckCommand> add)

}

package net.zomis.brainf.model.codeconv

import net.zomis.brainf.model.BrainFCommand
import net.zomis.brainf.model.BrainfuckCodeConverter
import net.zomis.brainf.model.BrainfuckCommand

import java.util.function.Consumer

class BrainfuckConverter implements BrainfuckCodeConverter {

    @Override
    void convert(String str, Consumer<BrainfuckCommand> add) {
        str.chars().mapToObj({i -> BrainFCommand.getCommand((char) i)})
                .filter({obj -> obj != null})
                .forEachOrdered(add);
    }

}

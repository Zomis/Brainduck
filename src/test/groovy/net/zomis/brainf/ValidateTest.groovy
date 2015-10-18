package net.zomis.brainf

import net.zomis.brainf.analyze.analyzers.MemoryValues
import org.junit.Test

class ValidateTest extends BrainfuckTest {

    @Test
    void moveNegativeIndex() {
        source.addCommands('+<<++')
        analyze()
        assert analyze.isMemoryIndexBelowZero()
    }

    @Test
    void stayPositiveIndex() {
        source.addCommands('+>+<++')
        analyze()
        assert !analyze.isMemoryIndexBelowZero()
    }

    @Test
    void valueRange() {
        source.addCommands('-[+]++')
        analyze(new MemoryValues())
        assert analyze.get(MemoryValues).minValue == -1
        assert analyze.get(MemoryValues).maxValue == 2
    }

}

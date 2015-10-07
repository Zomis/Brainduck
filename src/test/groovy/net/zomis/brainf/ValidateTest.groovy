package net.zomis.brainf

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
        analyze()
        assert analyze.minValue == -1
        assert analyze.maxValue == 2
    }

}

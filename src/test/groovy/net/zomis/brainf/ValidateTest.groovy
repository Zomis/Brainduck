package net.zomis.brainf

import net.zomis.brainf.analyze.analyzers.MemoryIndexAnalysis
import net.zomis.brainf.analyze.analyzers.MemoryValues
import org.junit.Test

class ValidateTest extends BrainfuckTest {

    @Test
    void moveNegativeIndex() {
        useCode('+<<++')
        analyze(new MemoryIndexAnalysis())
        assert analyze.get(MemoryIndexAnalysis).isMemoryIndexBelowZero()
        assert !analyze.get(MemoryIndexAnalysis).isMemoryIndexAboveMax()
    }

    @Test
    void stayPositiveIndex() {
        useCode('+>+<++')
        analyze(new MemoryIndexAnalysis())
        assert !analyze.get(MemoryIndexAnalysis).isMemoryIndexBelowZero()
        assert !analyze.get(MemoryIndexAnalysis).isMemoryIndexAboveMax()
    }

    @Test
    void valueRange() {
        brain.memory.minValue = -10
        useCode('-[+]++')
        analyze(new MemoryValues())
        assert analyze.get(MemoryValues).minValue == -1
        assert analyze.get(MemoryValues).maxValue == 2
    }

}

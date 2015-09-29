package net.zomis.brainf

import net.zomis.brainf.model.BrainF
import net.zomis.brainf.model.BrainfuckRunner
import org.junit.Before
import org.junit.Test

class BrainUnitTest {

    BrainfuckRunner runner

    @Before
    void setup() {
        runner = BrainF.createWithDefaultSize()
    }

    @Test
    void assertFail() {
        addCommands('''
            ++++
            $ assert value == 4
            $ assert value == 5
        ''')
        expectFailure('value == 5')
    }

    void addCommands(String code) {
        runner.code.addCommands(code)
    }

    void expectFailure(String expectedContains) {
        try {
            runner.run()
            assert false : 'No assertion error was thrown'
        } catch (AssertionError error) {
            println error
            assert error.getMessage().contains(expectedContains)
        }
    }

}

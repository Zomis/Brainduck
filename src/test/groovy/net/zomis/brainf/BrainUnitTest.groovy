package net.zomis.brainf

import net.zomis.brainf.model.BrainF
import net.zomis.brainf.model.BrainfuckRunner
import org.junit.Test

class BrainUnitTest {

    @Test
    void assertFail() {
        def runner = BrainF.createWithDefaultSize();
        runner.code.addCommands('''
            ++++
            $ assert value == 4
            $ assert value == 5
        ''')
        expectFailure(runner, 'value == 5')
    }

    static void expectFailure(BrainfuckRunner runner, String expectedContains) {
        try {
            runner.run()
            assert false : 'No assertion error was thrown'
        } catch (AssertionError error) {
            println error
            assert error.getMessage().contains(expectedContains)
        }
    }

}

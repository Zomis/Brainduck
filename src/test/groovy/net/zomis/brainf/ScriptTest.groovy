package net.zomis.brainf

import net.zomis.brainf.dsl.BrainDSL
import net.zomis.brainf.model.BrainF
import net.zomis.brainf.model.BrainfuckMemory
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.input.NoInput
import net.zomis.brainf.model.input.StringBuilderOutput
import org.junit.Test

class ScriptTest {

    static BrainfuckMemory runBrainfuck(String dslCode) {
        String code = run(dslCode)
        println code
        def runner = new BrainfuckRunner(new BrainfuckMemory(), BrainF.code(code),
                new NoInput(), new StringBuilderOutput(new StringBuilder()))
        runner.run()
        runner.getMemory()
    }

    private static String run(String code) {
        new BrainDSL().runScript(code).code
    }

    @Test
    void runScript() {
        assert run('right 2') == '>>'
    }

    @Test
    void runAlgorithm() {
        assert runBrainfuck('set 42; right 1; algo "x_equals_y" values(x: 0, y: -1, temp0: -2)')
            .getMemoryArray(0, 3) == [ 42, 42, 0 ]
    }

    @Test
    void set() {
        assert run('set(3, -4, "0")') == '[-]+++>[-]---->[-]++++++++++++++++++++++++++++++++++++++++++++++++'
    }

}

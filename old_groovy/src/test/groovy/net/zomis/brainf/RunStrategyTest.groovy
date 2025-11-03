package net.zomis.brainf

import groovy.transform.CompileStatic
import net.zomis.brainf.model.ast.tree.ChangePointerSyntax
import net.zomis.brainf.model.ast.tree.ChangeValueSyntax
import net.zomis.brainf.model.ast.tree.LoopInstructionSyntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.run.LimitedStepsStrategy
import net.zomis.brainf.model.run.RunUntilLoopStartStrategy
import net.zomis.brainf.model.run.UntilEndStrategy
import org.junit.Before
import org.junit.Test

@CompileStatic
class RunStrategyTest extends BrainfuckTest {

    @Before
    @Override
    public void setup() {
        super.setup()
        useCode('+++++[->+<]>+++')
    }

    @Test
    public void testTree() {
        def tree = brain.code.rootTree;
        assert tree.syntax.size() == 4
        assert (tree.syntax[0] as ChangeValueSyntax).value == 5
        assert (tree.syntax[1] as SyntaxTree).syntax.size() == 4
        assert (tree.syntax[2] as ChangePointerSyntax).value == 1
        assert (tree.syntax[3] as ChangeValueSyntax).value == 3
    }

    @Test
    public void singleStep() {
        brain.run(new LimitedStepsStrategy())
        assert brain.memory.getMemoryArray(0, 2) == [1, 0] as int[]
    }

    @Test
    public void twoSteps() {
        brain.run(new LimitedStepsStrategy(2))
        assert brain.memory.getMemoryArray(0, 2) == [2, 0] as int[]
    }

    @Test(timeout = 2000L)
    public void loopStart() {
        brain.run(new RunUntilLoopStartStrategy())
        assert brain.memory.getMemoryArray(0, 2) == [5, 0] as int[]
        assert brain.code.currentSyntax instanceof LoopInstructionSyntax
    }

    @Test
    public void untilEndStrategy() {
        brain.run(new UntilEndStrategy())
        assert brain.memory.getMemoryArray(0, 2) == [0, 8] as int[]
    }

    /*
    * TODO: Add test cases:
    * - Dynamically change currentSyntax and run from there (run loop x times)
    * - Dynamically modify tree and run
    * - Step Continue
    * - Step Out
    **/

}

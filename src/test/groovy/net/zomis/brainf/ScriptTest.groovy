package net.zomis.brainf

import net.zomis.brainf.dsl.BrainDSL
import org.junit.Test

class ScriptTest {

    private String run(String code) {
        new BrainDSL().runScript(code).code
    }

    @Test
    void runScript() {
        assert run('right 2') == '>>'
    }

}

package net.zomis.brainf.model

import org.codehaus.groovy.control.CompilerConfiguration

class SpecialCommand implements BrainfuckCommand {

    final String code

    SpecialCommand(String code) {
        this.code = code
    }

    @Override
    void perform(BrainfuckRunner runner) {
        CompilerConfiguration cc = new CompilerConfiguration()
        cc.setScriptBaseClass(DelegatingScript.class.getName())
        GroovyShell sh = new GroovyShell(cc)
        DelegatingScript script = (DelegatingScript) sh.parse(code)
        script.setDelegate(new SpecialDelegate(runner))
        script.run()
    }

}

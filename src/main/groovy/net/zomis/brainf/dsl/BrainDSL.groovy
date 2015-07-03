package net.zomis.brainf.dsl

import org.codehaus.groovy.control.CompilerConfiguration;

class BrainDSL {

    private final StringBuilder code = new StringBuilder()

    BrainDSL runScript(File file) {
        CompilerConfiguration cc = new CompilerConfiguration()
        cc.setScriptBaseClass(DelegatingScript.class.getName())
        GroovyShell sh = new GroovyShell(cc)
        DelegatingScript script = (DelegatingScript) sh.parse(file)
        script.setDelegate(this)
        script.run()
        this
    }

    BrainDSL runScript(String text) {
        CompilerConfiguration cc = new CompilerConfiguration()
        cc.setScriptBaseClass(DelegatingScript.class.getName())
        GroovyShell sh = new GroovyShell(cc)
        DelegatingScript script = (DelegatingScript) sh.parse(text)
        script.setDelegate(this)
        script.run()
        this
    }

    BrainDSL right(int steps) {
        addCode('>' * steps)
    }

    BrainDSL addCode(String s) {
        code.append(s)
        this
    }

    String getCode() {
        code.toString()
    }

}
package net.zomis.brainf.model.groovy

import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import org.codehaus.groovy.control.CompilerConfiguration

class GroovyBFContext {

    GroovyShell shell
    private Map<Integer, Set<String>> loopNames = new HashMap<>()

    GroovyBFContext() {
        CompilerConfiguration cc = new CompilerConfiguration()
        cc.setScriptBaseClass(DelegatingScript.class.getName())
        this.shell = new GroovyShell(cc)
    }

    BrainfuckCommand createCommand(String code) {
        new SpecialCommand(code)
    }

    void addLoopName(int index, String name) {
        this.loopNames.putIfAbsent(index, new HashSet<String>())
        this.loopNames.get(index).add(name)
    }

    String getLoopName(int index) {
        String names = String.valueOf(this.loopNames.get(index))
        "#$index $names"
    }

    Map<Integer, String> getLoopNames() {
        new HashMap<Integer, String>(this.loopNames)
    }

    private class SpecialCommand implements BrainfuckCommand {
        final String code

        SpecialCommand(String code) {
            this.code = code
        }

        @Override
        void perform(BrainfuckRunner runner) {
            DelegatingScript script = (DelegatingScript) shell.parse(code)
            script.setDelegate(new SpecialDelegate(GroovyBFContext.this, runner))
            script.run()
        }
    }

}

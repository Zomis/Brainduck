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
        def loopNameSet = this.loopNames.get(index)
        String prefix = "#$index"
        return loopNameSet ? "$prefix $loopNameSet" : prefix
    }

    Map<Integer, String> getLoopNames() {
        new HashMap<Integer, String>(this.loopNames)
    }

    private class SpecialCommand implements BrainfuckCommand {
        private final DelegatingScript script

        SpecialCommand(String code) {
            script = (DelegatingScript) shell.parse(code)
        }

        @Override
        void perform(BrainfuckRunner runner) {
            script.setDelegate(new SpecialDelegate(GroovyBFContext.this, runner))
            script.run()
        }
    }

}

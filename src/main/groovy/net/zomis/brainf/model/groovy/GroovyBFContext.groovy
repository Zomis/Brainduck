package net.zomis.brainf.model.groovy

import groovy.transform.PackageScope
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import org.codehaus.groovy.control.CompilerConfiguration

class GroovyBFContext {

    GroovyShell shell
    private Map<Integer, Set<String>> loopNames = new HashMap<>()
    @PackageScope Runnable afterRun = {}

    GroovyBFContext() {
        CompilerConfiguration cc = new CompilerConfiguration()
        cc.setScriptBaseClass(DelegatingScript.class.getName())
        this.shell = new GroovyShell(cc)
    }

    BrainfuckCommand createCommand(String code) {
        new SpecialCommand(this, code)
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

    void postExecute() {
        afterRun.run()
    }

    public static class SpecialCommand implements BrainfuckCommand {
        private final DelegatingScript script
        private final String code
        private final GroovyBFContext context

        SpecialCommand(GroovyBFContext context, String code) {
            this.code = code
            this.context = context
            script = (DelegatingScript) context.shell.parse(code)
        }

        @Override
        void perform(BrainfuckRunner runner) {
            script.setDelegate(new SpecialDelegate(context, runner))
            script.run()
        }

        String getCode() {
            return this.@code
        }

        @Override
        public String toString() {
            return 'GroovyCommand'
        }

    }

}

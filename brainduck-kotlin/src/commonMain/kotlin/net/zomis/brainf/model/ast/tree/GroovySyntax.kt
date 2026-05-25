package net.zomis.brainf.model.ast.tree;

import groovy.util.DelegatingScript;
import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.ast.GroovyToken;
import net.zomis.brainf.model.groovy.GroovyBFContext;
import net.zomis.brainf.model.groovy.SpecialDelegate;

public class GroovySyntax extends Syntax {

    private final GroovyToken token;
    private final GroovyBFContext groovyContext;
    private final DelegatingScript script;

    public GroovySyntax(GroovyBFContext groovyContext, GroovyToken token) {
        this.groovyContext = groovyContext;
        this.token = token;
        script = (DelegatingScript) groovyContext.getShell().parse(token.code);
    }

    @Override
    public void perform(BrainfuckRunner runner) {
        script.setDelegate(new SpecialDelegate(groovyContext, runner));
        script.run();
    }

    public String getCode() {
        return this.token.code;
    }

    @Override
    public String toString() {
        return "GroovySyntax{" + token.code + '}';
    }
}

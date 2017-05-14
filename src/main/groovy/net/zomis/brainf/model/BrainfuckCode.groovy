package net.zomis.brainf.model

import groovy.transform.CompileStatic
import net.zomis.brainf.model.ast.Token
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree

@CompileStatic
class BrainfuckCode {

    @Deprecated
    CodeRetriever source

    SyntaxTree rootTree;
    private final Stack<SyntaxTreePosition> enteredTrees = new Stack<>();
    int positionInSyntax;

    BrainfuckCode() {
    }

    public Stack<SyntaxTreePosition> getEnteredTrees() {
        return enteredTrees;
    }

    public void setRootTree(SyntaxTree tree) {
        this.rootTree = tree;
        resetIndex()
    }

    public Syntax getCurrentSyntax() {
        def tree = getCurrentTree();
        return tree.current
    }

    public SyntaxTreePosition getCurrentTree() {
        return enteredTrees.peek();
    }

    public int getSyntaxIndex() {
        return getCurrentTree().currentIndex
    }

    public boolean isFinished() {
        boolean lastInLastTree = enteredTrees.size() == 1 &&
            currentTree.currentIndex == currentTree.size()
        return lastInLastTree || enteredTrees.isEmpty()
    }

    void resetIndex() {
        enteredTrees.clear()
        if (rootTree != null) {
            enteredTrees.push(new SyntaxTreePosition(rootTree))
        }
    }

    boolean hasMoreCommands() {
        return !isFinished()
    }

    int getCommandCount() {
        if (rootTree == null) {
            throw new IllegalStateException("Root tree not set")
        }
        def lastToken = rootTree.tokens.last();
        return lastToken.info.position + lastToken.info.length
    }

    public int getCommandIndex() {
        if (enteredTrees.isEmpty()) {
            // No more commands to run so we are finished
            return commandCount;
        }
        Syntax syntax = getCurrentSyntax();
        if (syntax == null) {
            return commandCount;
        }
        Token token = syntax.tokens.get(positionInSyntax);
        return token.info.position;
    }

}

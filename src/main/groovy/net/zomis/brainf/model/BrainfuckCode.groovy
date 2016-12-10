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


    @Deprecated
    void gotoMatching(BrainfuckCommand decrease, BrainfuckCommand increase, int direction) {
        throw new RuntimeException();
    }

    @Deprecated
    int findMatching(BrainfuckCommand decrease, BrainfuckCommand increase, int direction) {
        return findMatching(commandIndex, decrease, increase, direction)
    }

    /**
     * Searches for a matching command, that is grouped with another command, in the specified direction
     *
     * @param startIndex Index to start search at (exclusive)
     * @param decrease The desired search target
     * @param increase The command that is the opposite of the desired search target, which increases the nesting
     * @param direction How much to change the index each step
     * @return The first found index of the `decrease` that has a nesting level of 0, or -1 if no such target is found
     */
    @Deprecated
    int findMatching(int startIndex, BrainfuckCommand decrease, BrainfuckCommand increase, int direction) {
        int index = startIndex
        int matching = 1;
        while (true) {
            index += direction;
            BrainfuckCommand current = getCommandAt(index);
            if (current == null) {
                return -1
            }
            if (current == decrease) {
                matching--;
                if (matching == 0) {
                    break;
                }
            }
            else if (current == increase) {
                matching++;
            }
        }
        index
    }

    @Deprecated
    void resetIndex() {
        enteredTrees.clear()
        enteredTrees.push(new SyntaxTreePosition(rootTree))
    }

    boolean hasMoreCommands() {
        return !isFinished()
    }

    @Deprecated
    BrainfuckCommand getNextCommand() {
        if (!hasMoreCommands()) {
            return null;
        }
        return null
        // source.getCommand(commandIndex)
    }

    int getCommandCount() {
        def lastToken = rootTree.tokens.last();
        return lastToken.info.position + lastToken.info.length
    }

    @Deprecated
    BrainfuckCommand getCommandAt(int index) {
        source?.getCommand(index)
    }

    public int getCommandIndex() {
        if (enteredTrees.isEmpty()) {
            // No more commands to run so we are finished
            return commandCount;
        }
        Syntax syntax = getCurrentSyntax();
        Token token = syntax.tokens.get(positionInSyntax);
        return token.info.position;
    }

}

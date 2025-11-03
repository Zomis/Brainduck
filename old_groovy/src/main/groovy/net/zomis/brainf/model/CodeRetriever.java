package net.zomis.brainf.model;

@Deprecated
public interface CodeRetriever {

    BrainfuckCommand getCommand(int commandIndex);

    default int getCommandLength(int commandIndex) {
        return 1;
    }

    int capacity();
}

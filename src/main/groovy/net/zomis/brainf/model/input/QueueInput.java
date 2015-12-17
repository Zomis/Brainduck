package net.zomis.brainf.model.input;

import net.zomis.brainf.model.BrainfuckException;
import net.zomis.brainf.model.BrainfuckInput;

import java.util.concurrent.BlockingQueue;

public class QueueInput implements BrainfuckInput {
    private final BlockingQueue<Integer> queue;

    public QueueInput(BlockingQueue<Integer> input) {
        this.queue = input;
    }

    @Override
    public int read() throws BrainfuckException {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new BrainfuckException("Interrupted while retrieving input", e);
        }
    }
}

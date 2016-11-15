package net.zomis.brainf.model.input;

import net.zomis.brainf.model.BrainfuckException;
import net.zomis.brainf.model.BrainfuckInput;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class QueueInput implements BrainfuckInput {
    private final BlockingQueue<Integer> queue;

    public QueueInput(BlockingQueue<Integer> input) {
        Objects.requireNonNull(input, "Queue input may not be null");
        this.queue = input;
    }

    public BlockingQueue<Integer> getQueue() {
        return queue;
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

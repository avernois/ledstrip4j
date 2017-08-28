package fr.craftinglabs.pi.apa102.httpapi;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class PrintingQueue {

    BlockingDeque<String> queue = new LinkedBlockingDeque<>();

    public Optional<String> getOldest() {
        try {
            return Optional.of(queue.removeFirst());
        } catch (NoSuchElementException nsee) {
            return Optional.empty();
        }
    }

    public void insert(String s) {
        queue.add(s);
    }
}

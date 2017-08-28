package fr.craftinglabs.pi.apa102.httpapi;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class PrintingQueueTest {

    @Test public void 
    should_return_and_empty_optional_when_queue_is_empty() {
        PrintingQueue queue = new PrintingQueue();

        Optional<String> empty = queue.getOldest();
        assertFalse(empty.isPresent());
    }

    @Test public void
    should_return_the_oldest_value_inserted() {
        PrintingQueue queue = new PrintingQueue();
        queue.insert("First value");
        queue.insert("Second value");

        Optional<String> value = queue.getOldest();

        assertEquals("First value", value.get());
    }

    @Test public void
    should_return_empty_optional_when_one_value_has_been_inserted_then_removed() {
        PrintingQueue queue = new PrintingQueue();
        queue.insert("A value");
        queue.getOldest();

        Optional<String> value = queue.getOldest();

        assertFalse(value.isPresent());
    }


}
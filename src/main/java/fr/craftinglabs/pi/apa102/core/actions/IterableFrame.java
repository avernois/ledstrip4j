package fr.craftinglabs.pi.apa102.core.actions;

import fr.craftinglabs.pi.apa102.matrix.Frame;

import java.util.Iterator;

public class IterableFrame implements Iterable<Frame> {

    private Frame frame;

    public IterableFrame(Frame frame) {
        this.frame = frame;
    }

    @Override
    public Iterator<Frame> iterator() {
        return new Iterator<Frame>() {
            boolean hasNext = true;
            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public Frame next() {
                hasNext = false;
                return frame;
            }
        };
    }
}

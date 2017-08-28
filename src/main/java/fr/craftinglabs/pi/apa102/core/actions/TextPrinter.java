package fr.craftinglabs.pi.apa102.core.actions;

import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.httpapi.PrintingQueue;
import fr.craftinglabs.pi.apa102.matrix.ApaMatrix;
import fr.craftinglabs.pi.apa102.matrix.Frame;
import fr.craftinglabs.pi.apa102.time.TimeFrameBuilder;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Supplier;

import static fr.craftinglabs.pi.apa102.text.SlidingTextFramesBuilder.slidingTextFrames;

public class TextPrinter implements Runnable {

    private PrintingQueue printingQueue;
    private Supplier<LocalTime> timeSupplier;
    private ApaMatrix matrix;

    public TextPrinter(ApaMatrix matrix, PrintingQueue printingQueue, Supplier<LocalTime> timeSupplier) {
        this.matrix = matrix;
        this.printingQueue = printingQueue;
        this.timeSupplier = timeSupplier;
    }

    @Override
    public void run() {
        while(true) {
            Optional<String> toBePrinted = printingQueue.getOldest();

            Iterable<Frame> frames = toBePrinted.map(text -> buildSllidingTextFrames(text))
                                                .orElse(buildTimeFrameIterable(timeSupplier));

            for (Frame frame : frames) {
                matrix.print(frame);
                sleep(75);
            }
        }
    }

    private Iterable<Frame> buildSllidingTextFrames(String toBePrinted) {
        return slidingTextFrames()
                            .sized(matrix.size())
                            .withBackgroundColor(new ARGBColor(0, 0, 0, 0))
                            .withFontColor(new ARGBColor(10, 100, 0, 155))
                            .forText(toBePrinted).build();
    }

    private Iterable<Frame> buildTimeFrameIterable(Supplier<LocalTime> timeSupplier) {
        return buildTimeFrameIterable(timeSupplier.get());
    }

    private Iterable<Frame> buildTimeFrameIterable(LocalTime time) {
        Frame frame = TimeFrameBuilder.aTimeFrame().sized(matrix.size())
                .withBackgroundColor(ARGBColor.BLACK)
                .withFontColor(new ARGBColor(15, 255, 255, 255))
                .buildForTime(time);

        Iterable<Frame> frames = () -> new Iterator<Frame>() {
            int call = 0;
            @Override
            public boolean hasNext() {
                return call == 0;
            }

            @Override
            public Frame next() {
                call++;
                return frame;
            }
        };
        
        return frames;
    }

    private void sleep(int timemillis) {
        try {
            Thread.sleep(timemillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

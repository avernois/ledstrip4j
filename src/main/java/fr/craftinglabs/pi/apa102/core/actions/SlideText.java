package fr.craftinglabs.pi.apa102.core.actions;

import fr.craftinglabs.pi.apa102.httpapi.PrintingQueue;

import java.util.concurrent.BlockingDeque;

public class SlideText {

    private final PrintingQueue printingQueue;

    public SlideText(PrintingQueue printingQueue) {
        this.printingQueue = printingQueue;
    }

    public String slideText(String text) throws InterruptedException {
        printingQueue.insert(text);

        return "OK";
    }
}


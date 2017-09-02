package fr.craftinglabs.pi.apa102.httpapi;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.GpioUtil;
import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.Apa;
import fr.craftinglabs.pi.apa102.core.actions.SlideText;
import fr.craftinglabs.pi.apa102.core.actions.TextPrinter;
import fr.craftinglabs.pi.apa102.matrix.Apa102LedStrip;
import fr.craftinglabs.pi.apa102.matrix.ApaMatrix;
import fr.craftinglabs.pi.apa102.matrix.Size;
import fr.craftinglabs.pi.apa102.matrix.io.ConsoleTwoColorMatrixLedStrip;
import fr.craftinglabs.pi.apa102.matrix.io.LedStrip;
import fr.craftinglabs.pi.apa102.matrix.io.LedStripGPIO;
import fr.craftinglabs.pi.apa102.matrix.io.LedStripIO;
import sun.swing.PrintingStatus;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.stop;

public class ApaServer {

    private static final Integer DEFAULT_PORT = 5000;


    public static void main(String[] args) throws InterruptedException, IOException {
        port(DEFAULT_PORT);

//        ApaMatrix matrix = initApa102Matrix();
        ApaMatrix matrix = initConsoleMatrix();

        PrintingQueue printingQueue = new PrintingQueue();
        SlideText slideText = new SlideText(printingQueue);

        new Thread(new TextPrinter(matrix, printingQueue, () -> LocalTime.now())).start();

        printStartingText(slideText);

        get("/text/:text", ((request, response) -> slideText.slideText(request.params(":text"))));

        addShutdownHook();
        Thread.currentThread().join();
    }

    private static void printStartingText(SlideText slideText) throws InterruptedException {
        String text = "Port " + DEFAULT_PORT + ".";
        slideText.slideText(text);
    }

    private static ApaMatrix initApa102Matrix() {
        GpioUtil.enableNonPrivilegedAccess();

        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput clock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "CLOCK", PinState.LOW);
        GpioPinDigitalOutput data = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "DATA", PinState.LOW);

        LedStripIO io = new LedStripGPIO(data, clock);
        Size size = new Size(24, 5);

        return new ApaMatrix(size, new Apa102LedStrip(io));
    }

    private static ApaMatrix initConsoleMatrix() {
        Size size = new Size(24, 5);
        return new ApaMatrix(size, new ConsoleTwoColorMatrixLedStrip(size, ARGBColor.BLACK));
    }

    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Exiting.");
            stop();
        }, "shutdownHook"));
    }
}
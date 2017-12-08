package com.mycompany.paymenttrackercli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static HashMap<String, Integer> payments = new HashMap<>();
    public static final int OUTPUT_IN_SECONDS = 60;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // TODO code application logic here
        if (args.length != 1) {
            System.out.println("You need to supply an input file with currencies and amounts. Exiting now...");
            System.exit(1);
        }

        // read initial input/data from a file
        try {
            File f = new File(args[0]);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            System.out.println("Reading input file first...");

            while ((readLine = b.readLine()) != null) {
                System.out.println(readLine);
                String keyvalue[] = readLine.split("\\s+");
                String key = keyvalue[0].toUpperCase();
                if (key.length() != 3) {
                    // bad currency - skipping
                    continue;
                }
                Integer value;

                try {
                    value = new Integer(keyvalue[1]);
                } catch (NumberFormatException e) {
                    // wrong value - skipping
                    continue;
                }

                if (!payments.containsKey(key)) {
                    payments.put(keyvalue[0], new Integer(keyvalue[1]));
                } else {
                    payments.put(key, payments.get(key) + value);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // schedule and run two tasks periodically
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);

        execService.scheduleAtFixedRate(new Thread(new InputTask()), 0, 1, TimeUnit.SECONDS);
        execService.scheduleAtFixedRate(new Thread(new OutputTask()), 0, OUTPUT_IN_SECONDS, TimeUnit.SECONDS);
    }
}

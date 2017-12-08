package com.mycompany.paymenttrackercli;

import static com.mycompany.paymenttrackercli.Main.payments;
import java.time.LocalTime;
import java.util.Iterator;

public class OutputTask implements Runnable {

    @Override
    public void run() {

        System.out.println("Running my OUTPUT task @ " + LocalTime.now());

        Iterator iter = payments.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            Object value = payments.get(key);
            if (payments.get(key) == 0) {
                continue;
            }
            System.out.println(key + " --> " + value);
        }
    }
}
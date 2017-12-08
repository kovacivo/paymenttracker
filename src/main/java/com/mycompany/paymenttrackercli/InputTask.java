package com.mycompany.paymenttrackercli;

import static com.mycompany.paymenttrackercli.Main.payments;
import java.util.Scanner;

/**
 *
 * @author ik9281
 */
public class InputTask implements Runnable {

    @Override
    public void run() {

        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        // user typed "quit"
        if (line.equals("quit")) {
            System.out.println("User wants to quit. Exiting now...");
            System.exit(0);
        }
        String[] keyvalue = line.split("\\s+");
        String key = keyvalue[0].toUpperCase();

        // if the currency is not three letters
        if (key.length() != 3) {
            System.out.println("Not correct currency given!!!");
            return;
        }

        Integer value;

        try {
            value = Integer.parseInt(keyvalue[1]);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect value given!!!");
            return;
        }

        if (!payments.containsKey(key)) {
            payments.put(keyvalue[0], new Integer(keyvalue[1]));
        } else {
            payments.put(key, payments.get(key) + value);
        }
    }
}

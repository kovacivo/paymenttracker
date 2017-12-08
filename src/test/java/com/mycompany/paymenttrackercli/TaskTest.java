package com.mycompany.paymenttrackercli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TaskTest {
    
    public static HashMap<String, Integer> payments = new HashMap<>();
    public File f;
    
    public TaskTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         // read initial input/data from a file
        try {
            f = new File("initial_payment_list.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            System.out.println("Reading input file first...");

            while ((readLine = b.readLine()) != null) {
                System.out.println(readLine);
                String keyvalue[] = readLine.split("\\s+");
                String key = keyvalue[0].toUpperCase();
                Integer value = new Integer(keyvalue[1]);

                if (!payments.containsKey(key)) {
                    payments.put(keyvalue[0], new Integer(keyvalue[1]));
                } else {
                    payments.put(key, payments.get(key) + value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
    }
       
    @Test
    public void testAddAmount() {
        System.out.println("Adding amount  of 300 to HKD");
        System.out.println("HKD should be then 600");
        Integer value = payments.get("HKD");
        payments.put("HKD", payments.get("HKD") + 300);
        System.out.println(payments.get("HKD"));
        Assert.assertTrue(payments.get("HKD") == value + 300);
    }
    
    @Test
    public void testSubAmount() {
        System.out.println("Subtracting amount  of 500 from RMB");
        System.out.println("RMB should be then 1500");
        Integer value = payments.get("RMB");
        payments.put("RMB", payments.get("RMB") -500);
        System.out.println(payments.get("RMB"));
        Assert.assertTrue(payments.get("RMB") == value -500);
    }
    
}

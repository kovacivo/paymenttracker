# Payment tracker

Write a program that keeps a record of payments. Each payment includes a currency and an amount. 
Data should be kept in memory (please don’t introduce any database engines).

The program should output a list of all the currency and amounts to the console once per minute. 
The input can be typed into the command line with possibility to be automated in the future, 
and optionally also be loaded from a file when starting up.

## Detailed requirements
When your Java program is run, a filename can be optionally specified. 
The format of the file will be one or more lines with Currency Code Amount like in the Sample Input above, 
where the currency may be any uppercase 3 letter code, such as USD, HKD, RMB, NZD, GBP etc. 
The user can then enter more lines into the console by typing a currency and amount and pressing enter. 
Once per minute, the output showing the net amounts of each currency should be displayed. 
If the net amount is 0, that currency should not be displayed. 
When the user types "quit", the program should exit.

## Sample input
```
USD 1000
HKD 100
USD -100
RMB 2000
HKD 200
```

## Sample output
```
USD 900
RMB 2000
HKD 300
```

## Assumptions taken
- input file is called 'initial_payment_list.txt'
- there are no checks if the inputted currency exists at all; just if it is comprised of three letters/characters

## How to compile, test and run program
This is a Maven project.

### Compilation
```
mvn package
```

### Testing
```
mvn test
```

### Launching
```
java -cp target/PaymentTrackerCli-1.0-SNAPSHOT.jar  com.mycompany.paymenttrackercli.Main initial_payment_list.txt
```

## Notes
- tests are far from being perfect - I have little experience on how to test threaded programs
- I know of design patterns; in this case I did not use any
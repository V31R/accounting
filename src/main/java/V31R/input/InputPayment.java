package V31R.input;

import V31R.payment.Payment;

import java.util.Scanner;

public class InputPayment {

    public static Payment input(){

        Scanner inputStream = new Scanner(System.in);
        String currency = inputStream.next();
        Double sum = inputStream.nextDouble();
        return new Payment(currency,sum);

    }

}

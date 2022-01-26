package V31R.input;

import V31R.payment.Payment;

import java.util.Locale;
import java.util.Scanner;

public class InputPayment {

    public static Payment input() throws Exception {

        Scanner inputStream = new Scanner(System.in);

        String currency = inputStream.next().toUpperCase(Locale.ROOT).trim();
        if(currency.equals("QUIT")){
            throw new Exception();
        }
        Double sum = 0.d;
        if (inputStream.hasNext()){

           sum = inputStream.nextDouble();

        }else{

            if(currency.equals("QUIT")){
                throw new Exception();
            }

        }
        return new Payment(currency,sum);

    }

}

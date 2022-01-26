package V31R.input;

import V31R.exception.QuitException;
import V31R.output.Output;
import V31R.payment.Payment;
import V31R.payment.PaymentDAOFactory;

import java.util.Locale;
import java.util.Scanner;

public class InputPayment {

    public static Payment input() throws QuitException, Exception {

        Scanner inputStream = new Scanner(System.in);

        String currency = inputStream.next().toUpperCase(Locale.ROOT).trim();
        if(currency.equals("QUIT")){

            throw new QuitException();

        }

        if(!PaymentDAOFactory.getInstance().isCurrencyAvailable(currency)){

            throw new Exception(
                    (new StringBuilder()).append("Currency \"")
                            .append(currency)
                            .append("\" isn't available.")
                            .toString()
            );

        }

        Double sum = 0.d;
        if (inputStream.hasNext()){

            try {

                sum = inputStream.nextDouble();

            }catch(Exception exception){

                Output.println("Value must be a number");

            }

        }

        return new Payment(currency,sum);

    }

}

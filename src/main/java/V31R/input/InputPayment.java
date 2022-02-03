package V31R.input;

import V31R.exception.PaymentFormatException;

import V31R.output.Output;
import V31R.payment.Payment;
import V31R.payment.PaymentDAOFactory;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class InputPayment {

    public static Payment input() throws  PaymentFormatException {

        Scanner scanner = new Scanner(System.in);

        String currency = scanner.next().toUpperCase(Locale.ROOT);
        if(currency.equals("QUIT")){

            return null;

        }

       return inputPayment(scanner,currency);

    }

    public static Payment input(Scanner scanner) throws PaymentFormatException {

        String currency = scanner.next().toUpperCase(Locale.ROOT);

        return inputPayment(scanner,currency);

    }

    protected static Payment inputPayment(Scanner scanner, String currency) throws PaymentFormatException{

        if(!PaymentDAOFactory.getInstance().isCurrencyAvailable(currency)){

            throw new PaymentFormatException(
                    (new StringBuilder()).append("Currency \"")
                            .append(currency)
                            .append("\" isn't available.")
                            .toString()
            );

        }

        BigDecimal sum = BigDecimal.valueOf(0);
        if (scanner.hasNext()){

            try {

                sum = scanner.nextBigDecimal();

            }catch(Exception exception){

                Output.println("Value must be a number");

            }

        }

        return new Payment(currency,sum);

    }

}

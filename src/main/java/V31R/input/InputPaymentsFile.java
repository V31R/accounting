package V31R.input;

import V31R.output.Output;
import V31R.payment.PaymentDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class InputPaymentsFile {

    public static void loadFile(String filename, PaymentDAO paymentDAO) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream(filename));

        while (scanner.hasNext()) {

            try {

                paymentDAO.addPayment(InputPayment.input(scanner));

            }catch(Exception exception){

                Output.println((new StringBuilder())
                        .append("Was occurred an error during reading \"")
                        .append(filename)
                        .append("\".")
                        .toString());

            }


        }

    }


}

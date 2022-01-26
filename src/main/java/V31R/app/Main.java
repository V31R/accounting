package V31R.app;

import V31R.exception.QuitException;
import V31R.input.InputPayment;
import V31R.output.Output;
import V31R.output.OutputPayment;
import V31R.payment.CurrenciesBalances;
import V31R.payment.Payment;
import V31R.payment.PaymentDAOFactory;

import java.io.IOException;

public class Main {

    static public void main(String[] argv){

        CurrenciesBalances currenciesBalances = new CurrenciesBalances();
        try {

            PaymentDAOFactory.getInstance().loadCurrencies("currencies.txt");
            PaymentDAOFactory.getInstance().setDAO(currenciesBalances);

        }
        catch (Exception exception){

            Output.println(exception.getMessage());
            return;

        }
        while(true) {

            Output.println("Enter payment");
            Payment payment=null;
            try {

                payment = InputPayment.input();

            }
            catch(QuitException quitException){

                break;

            }
            catch (IOException ioException){

                Output.println("Something go wrong :(");
                break;

            }
            catch(Exception exception){

                Output.println(exception.getMessage());

            }
            currenciesBalances.addPayment(payment);
            currenciesBalances.outputCurrent();

        }
    }


}

package V31R.app;

import V31R.exception.QuitException;
import V31R.input.InputPayment;
import V31R.input.MainInputLoop;
import V31R.output.Output;
import V31R.output.OutputPayment;
import V31R.payment.CurrenciesBalances;
import V31R.payment.Payment;
import V31R.payment.PaymentDAOFactory;

import java.io.IOException;

public class Main {

    static public void main(String[] argv){

        MainInputLoop mainInputLoop = new MainInputLoop();
        mainInputLoop.run();

    }


}

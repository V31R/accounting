package V31R.app;

import V31R.input.InputPayment;
import V31R.output.OutputPayment;
import V31R.payment.CurrenciesBalances;
import V31R.payment.Payment;

public class Main {

    static public void main(String[] argv){
        CurrenciesBalances currenciesBalances = new CurrenciesBalances();
        while(true) {
            System.out.println("Enter payment");
            Payment payment = InputPayment.input();

            currenciesBalances.addPayment(payment);
            currenciesBalances.outputCurrent();

        }
    }


}

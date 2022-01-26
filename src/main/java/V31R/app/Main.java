package V31R.app;

import V31R.input.InputPayment;
import V31R.output.OutputPayment;
import V31R.payment.CurrenciesBalances;
import V31R.payment.Payment;

public class Main {

    static public void main(String[] argv){
        CurrenciesBalances currenciesBalances = new CurrenciesBalances();
        try {
            currenciesBalances.loadCurrencies("currencies.txt");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return;
        }
        while(true) {
            System.out.println("Enter payment");
            Payment payment=null;
            try {
                payment = InputPayment.input();
            }
            catch(Exception exception){
                break;
            }
            currenciesBalances.addPayment(payment);
            currenciesBalances.outputCurrent();

        }
    }


}

package V31R.app;

import V31R.input.InputPayment;
import V31R.output.OutputPayment;
import V31R.payment.Payment;

public class Main {

    static public void main(String[] argv){

        System.out.println("Enter payment");
        Payment payment= InputPayment.input();
        OutputPayment.output(payment);

    }


}

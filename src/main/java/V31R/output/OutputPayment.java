package V31R.output;

import V31R.payment.Payment;

public class OutputPayment {

    public static void output(Payment payment){

        System.out.printf("%s %s\n",payment.getCurrency(), payment.getSum());

    }

}

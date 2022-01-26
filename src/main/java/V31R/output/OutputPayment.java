package V31R.output;

import V31R.payment.Payment;

import java.util.Map;

public class OutputPayment {

    public static void output(Map.Entry<String, Double> payment){

        output(new Payment(payment.getKey(), payment.getValue()));

    }

    public static void output(Payment payment){

        if(Double.compare(payment.getSum(),0)!=0) {

            System.out.printf("%s %s\n", payment.getCurrency(), payment.getSum());

        }

    }

}

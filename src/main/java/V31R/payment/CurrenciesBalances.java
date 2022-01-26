package V31R.payment;

import V31R.output.OutputPayment;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class CurrenciesBalances implements  PaymentDAO{

    private Map<String, Double> balancies = new HashMap<String, Double>();

    @Override
    public void addPayment(Payment payment) {

        if(payment==null){

            return;

        }

        if(balancies.containsKey(payment.getCurrency())){

            balancies.replace(payment.getCurrency(),Double.sum( balancies.get(payment.getCurrency()), payment.getSum()));

        }else{

            balancies.put(payment.getCurrency(),payment.getSum());

        }

    }

    @Override
    public void outputCurrent() {

        balancies.entrySet()
                .forEach((p)->OutputPayment.output(p));

    }

}

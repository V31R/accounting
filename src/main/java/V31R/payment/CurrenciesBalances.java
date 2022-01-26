package V31R.payment;

import V31R.output.OutputPayment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;


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
    public Set<Map.Entry<String,Double>> getPayments(){

        return balancies.entrySet();

    }


}

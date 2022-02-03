package V31R.payment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class CurrenciesBalances implements  PaymentDAO{

    private Object lock = new Object();

    private Map<String, BigDecimal> balancies = new HashMap<>();

    @Override
    public void addPayment(Payment payment) {

        if(payment==null){

            return;

        }

        synchronized (lock) {

            if (balancies.containsKey(payment.getCurrency())) {

                balancies.replace(payment.getCurrency(), balancies.get(payment.getCurrency()).add(payment.getSum()));

            } else {

                balancies.put(payment.getCurrency(), payment.getSum());

            }

        }

    }

    @Override
    public Set<Map.Entry<String, BigDecimal>> getPayments(){

        synchronized (lock) {

            return balancies.entrySet();

        }

    }


}

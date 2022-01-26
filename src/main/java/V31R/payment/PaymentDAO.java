package V31R.payment;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public interface PaymentDAO {

    public void addPayment(Payment payment);
    public Set<Map.Entry<String,Double>> getPayments();

}

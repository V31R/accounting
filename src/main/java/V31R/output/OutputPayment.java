package V31R.output;

import V31R.payment.Payment;
import V31R.payment.PaymentDAO;
import V31R.payment.PaymentDAOFactory;

import java.math.BigDecimal;
import java.util.Map;

public class OutputPayment {

    public static void output(Map.Entry<String, BigDecimal> payment){

        output(new Payment(payment.getKey(), payment.getValue()));

    }

    public static void output(Payment payment){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(payment.getCurrency())
                    .append(" ")
                    .append(payment.getSum());

        BigDecimal rate = PaymentDAOFactory.getInstance().getCurrencyRate(payment.getCurrency());
        if(rate!=null && !payment.getCurrency().equals("USD")){

            stringBuilder.append(" (USD ")
                    .append(String.format("%.2f",payment.getSum().multiply(rate)))
                    .append(")");

        }
        Output.println(stringBuilder.toString());

    }

    public static void output(PaymentDAO paymentDAO){

        paymentDAO.getPayments()
                .stream()
                .filter((p)->p.getValue().compareTo(BigDecimal.valueOf(0))!=0)
                .forEach((p)->OutputPayment.output(p));

    }

}

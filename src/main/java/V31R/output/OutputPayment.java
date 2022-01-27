package V31R.output;

import V31R.payment.Payment;
import V31R.payment.PaymentDAO;
import V31R.payment.PaymentDAOFactory;

import java.util.Map;

public class OutputPayment {

    public static void output(Map.Entry<String, Double> payment){

        output(new Payment(payment.getKey(), payment.getValue()));

    }

    public static void output(Payment payment){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(payment.getCurrency())
                    .append(" ")
                    .append(payment.getSum());

        Double rate = PaymentDAOFactory.getInstance().getCurrencyRate(payment.getCurrency());
        if(rate!=null && !payment.getCurrency().equals("USD")){

            stringBuilder.append(" (USD ")
                    .append(String.format("%.2f",payment.getSum()*rate))
                    .append(")");

        }
        Output.println(stringBuilder.toString());

    }

    public static void output(PaymentDAO paymentDAO){

        paymentDAO.getPayments()
                .stream()
                .filter((p)->Double.compare(p.getValue(), 0.d)!=0)
                .forEach((p)->OutputPayment.output(p));

    }

}

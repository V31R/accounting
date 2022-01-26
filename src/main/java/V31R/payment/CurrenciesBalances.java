package V31R.payment;

import V31R.output.OutputPayment;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class CurrenciesBalances implements  PaymentDAO{

    private Map<String, Double> balancies = new HashMap<String, Double>();

    public void loadCurrencies(String currenciesFilename) throws FileNotFoundException, IOException {

        InputStream fileInputStream = new FileInputStream(currenciesFilename);
        balancies.clear();
        Scanner inputStream = new Scanner(fileInputStream);
        while(inputStream.hasNext()){
            String currency=inputStream.next().trim().toUpperCase(Locale.ROOT);
            balancies.put(currency, 0.d);
        }
        fileInputStream.close();

    }

    @Override
    public void addPayment(Payment payment) {

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

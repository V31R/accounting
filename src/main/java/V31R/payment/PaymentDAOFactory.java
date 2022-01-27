package V31R.payment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class PaymentDAOFactory {

    private Set<String> currencies;
    private static PaymentDAOFactory instance;

    private boolean loaded;

    private PaymentDAOFactory(){

        loaded = false;
        currencies = new HashSet<>();

    }

    public static  PaymentDAOFactory getInstance(){

        if(instance == null){

            instance = new PaymentDAOFactory();

        }

        return instance;

    }

    public void loadCurrencies(String filename) throws IOException{

        Scanner inputStream = new Scanner(new FileInputStream(filename));

        while (inputStream.hasNext()) {

            currencies.add(inputStream.next().trim().toUpperCase(Locale.ROOT));

        }

        loaded = true;

    }

    public void setDAO(PaymentDAO paymentDAO) throws Exception{

        if(!loaded){

            throw new Exception("Currencies not loaded.");

        }

        Payment defaultPayment = new Payment("",0.d);
        for (String currency:currencies) {

            defaultPayment.setCurrency(currency);
            paymentDAO.addPayment(defaultPayment);

        }

    }

    public boolean isCurrencyAvailable(String currency){

        return currencies.contains(currency);

    }

}

package V31R.payment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class PaymentDAOFactory {

    private Map<String, Double> currencies;
    private static PaymentDAOFactory instance;

    private boolean loaded;

    private PaymentDAOFactory(){

        loaded = false;
        currencies = new HashMap<>();

    }

    public static  PaymentDAOFactory getInstance(){

        if(instance == null){

            instance = new PaymentDAOFactory();

        }

        return instance;

    }

    public void loadCurrencies(String filename) throws IOException{

        Scanner inputStream = new Scanner(new FileInputStream(filename));

        String last=inputStream.next().trim().toUpperCase(Locale.ROOT);
        while (inputStream.hasNext()) {

            String in =inputStream.next().trim();

            try{

                Double rate=Double.valueOf(in);
                currencies.put(last.toUpperCase(Locale.ROOT), rate);


            }
            catch(Exception exception){

                if(!currencies.containsKey(last)) {

                    currencies.put(last, null);

                }
                last=in.toUpperCase(Locale.ROOT);

            }

        }
        if(!currencies.containsKey(last)) {

            currencies.put(last, null);

        }

        loaded = true;

    }

    public void setDAO(PaymentDAO paymentDAO) throws Exception{

        if(!loaded){

            throw new Exception("Currencies not loaded.");

        }

        currencies.entrySet()
                .forEach(
                        (p)-> paymentDAO.addPayment(new Payment(p.getKey(), 0.d))
                );


    }

    public boolean isCurrencyAvailable(String currency){

        return currencies.containsKey(currency);

    }

    public Double getCurrencyRate(String currency){

        return currencies.get(currency);

    }

}

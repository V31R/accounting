package V31R.payment;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class PaymentDAOFactory {

    private Map<String, BigDecimal> currencies;
    private static PaymentDAOFactory instance;

    private boolean loaded;

    private PaymentDAOFactory(){

        loaded = false;
        currencies = new HashMap<String, BigDecimal>();

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

                BigDecimal rate=new BigDecimal(in);
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
                        (p)-> paymentDAO.addPayment(new Payment(p.getKey(), BigDecimal.valueOf(0)))
                );


    }

    public boolean isCurrencyAvailable(String currency){

        return currencies.containsKey(currency);

    }

    public BigDecimal getCurrencyRate(String currency){

        return currencies.get(currency);

    }

}

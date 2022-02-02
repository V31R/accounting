package V31R.input;

import V31R.exception.PaymentFormatException;
import V31R.exception.QuitException;
import V31R.output.Output;
import V31R.output.OutputPayment;
import V31R.payment.CurrenciesBalances;
import V31R.payment.Payment;
import V31R.payment.PaymentDAO;
import V31R.payment.PaymentDAOFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class MainInputLoop {

    private PaymentDAO paymentDAO;

    public MainInputLoop(){

        paymentDAO = new CurrenciesBalances();

    }

    protected void start(PaymentDAO paymentDAO){

        Scanner scanner = new Scanner(System.in);
        String first = scanner.next().toUpperCase(Locale.ROOT);
        if(first.equals("FILE")){
            try {

                InputPaymentsFile.loadFile(scanner.next(), paymentDAO);

            }catch (FileNotFoundException fileNotFoundException){

                Output.println("Impossible to open file.");

            }

        }
        else if(first.equals("RUN")){

            return;

        }
        else{

            try {

                paymentDAO.addPayment(InputPayment.inputPayment(scanner, first));

            }
            catch(Exception exception){

                Output.println(exception.getMessage());

            }

        }

    }

    protected void load(String currenciesFilename) throws IOException, Exception {

        PaymentDAOFactory.getInstance().loadCurrencies(currenciesFilename);
        PaymentDAOFactory.getInstance().setDAO(paymentDAO);

    }

    protected  void outputLoop(){

        while(true){

            OutputPayment.output(paymentDAO);

            try {
                Thread.sleep(60000);
            }
            catch(Exception exception){

                continue;

            }

        }

    }

    protected void loop(){

        boolean isWork = true;
        while(isWork) {

            Payment payment=null;
            try {

                payment = InputPayment.input();

            }
            catch(QuitException quitException){

                isWork=false;

            }
            catch(PaymentFormatException paymentFormatException){

                Output.println(paymentFormatException.getMessage());

            }

            paymentDAO.addPayment(payment);

        }

    }

    public void run(){

        try {

            load("currencies.txt");

        }
        catch (Exception exception){

            Output.println(exception.getMessage());
            return;

        }

        start(paymentDAO);

        Thread outputThread = new Thread(()->outputLoop());
        outputThread.setDaemon(true);
        outputThread.setName("Output-balance-thread");
        outputThread.start();

        loop();

    }

}

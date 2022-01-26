package V31R.payment;

public class Payment {

    private String currency;

    private Double sum;

    public Payment(String currency, Double sum) {

        this.currency = currency;
        this.sum = sum;

    }

    public String getCurrency() {

        return currency;

    }

    public void setCurrency(String currency) {

        this.currency = currency;

    }

    public Double getSum() {

        return sum;

    }

    public void setSum(Double sum) {

        this.sum = sum;

    }

}

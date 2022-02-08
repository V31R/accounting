package V31R.payment;

import java.math.BigDecimal;

public class Payment {

    private String currency;

    private BigDecimal sum;

    public Payment(String currency, BigDecimal sum) {

        this.currency = currency;
        this.sum = sum;

    }

    public String getCurrency() {

        return currency;

    }

    public void setCurrency(String currency) {

        this.currency = currency;

    }

    public BigDecimal getSum() {

        return sum;

    }

    public void setSum(BigDecimal sum) {

        this.sum = sum;

    }

}

package Panhandlr.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class StockQuote {

    final String companyName;
    final String quoteCode;
    final Double latestPrice;
    final Double openPrice;
    final Double closePrice;
    private static final int STOCK_ELEMENT_NUMBER = 5;


    public StockQuote(String companyName, String quoteCode, String latestPrice, String openPrice, String closePrice) {
        this.companyName = companyName;
        this.quoteCode = quoteCode;
        this.latestPrice = Double.parseDouble(latestPrice);
        this.openPrice = Double.parseDouble(openPrice);
        this.closePrice = Double.parseDouble(closePrice);
    }


    public static StockQuote parseRawDetails(String inputLine) {

        String[] fragments = inputLine.split(",");

        if (fragments.length != STOCK_ELEMENT_NUMBER) {
            throw new IllegalArgumentException("Malformed raw stock line encountered ! : " + inputLine);
        } else {
            String companyName = fragments[0].replace("\"", "");
            String companyCode = fragments[1].replace("\"", "");

            return new StockQuote(companyName, companyCode, fragments[2], fragments[3], fragments[4]);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!o.getClass().equals(this.getClass())) {
            return false;
        } else if (o == this) {
            return true;
        } else {
            StockQuote that = (StockQuote) o;
            return new EqualsBuilder()
                    .append(this.companyName, that.companyName)
                    .append(this.quoteCode, that.quoteCode)
                    .append(this.latestPrice, that.latestPrice)
                    .append(this.openPrice, that.openPrice)
                    .append(this.closePrice, that.closePrice)
                    .isEquals();
        }

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.companyName)
                .append(this.quoteCode)
                .append(this.latestPrice)
                .append(this.openPrice)
                .append(this.closePrice)
                .hashCode();
    }
}

package Panhandlr.domain;

import au.com.bytecode.opencsv.CSVParser;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.IOException;

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


    public static StockQuote parseRawDetails(String inputLine) throws IOException {

        CSVParser csvParser = new CSVParser();
        String fragments[] = csvParser.parseLine(inputLine);

        if (fragments.length != STOCK_ELEMENT_NUMBER) {
            throw new IllegalArgumentException("Malformed raw stock line encountered ! : " + inputLine);
        } else {
            String companyName = fragments[0];
            String companyCode = fragments[1];
            String latestPrice = "N/A".equals(fragments[2]) ? "0.00" : fragments[2];
            String openPrice = "N/A".equals(fragments[3]) ? "0.00" : fragments[3];
            String closePrice = "N/A".equals(fragments[4]) ? "0.00" : fragments[4];

            return new StockQuote(companyName, companyCode, latestPrice, openPrice, closePrice);
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getQuoteCode() {
        return quoteCode;
    }

    public Double getLatestPrice() {
        return latestPrice;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public Double getClosePrice() {
        return closePrice;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

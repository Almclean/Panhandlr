package Panhandlr.services;

import Panhandlr.domain.StockQuote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class YahooConnectionHandler implements QuoteSource {

    // http://download.finance.yahoo.com/d/quotes.csv?s=GOOG,AAPL&f=nsl1op&e.csv

    @Override
    public List<StockQuote> getLatestPriceDetails(String[] stockSymbols) throws IOException, MalformedURLException {

        List<StockQuote> stockQuoteList = new ArrayList<StockQuote>();

        String baseUrl = "http://download.finance.yahoo.com/d/quotes.csv?";
        String termUrl = "&f=nsl1op";
        String staticUrl = "&e.csv";

        StringBuilder stocks = new StringBuilder();
        stocks.append("s=");

        for (String stock : stockSymbols) {
            stocks.append(stock + ",");
        }


        URL searchUrl = new URL(baseUrl + stocks.toString() + termUrl + staticUrl);
        BufferedReader in = new BufferedReader(new InputStreamReader(searchUrl.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            stockQuoteList.add(StockQuote.parseRawDetails(inputLine));
        }

        in.close();

        return stockQuoteList;
    }
}


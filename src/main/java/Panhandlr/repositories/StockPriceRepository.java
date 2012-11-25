package Panhandlr.repositories;

import Panhandlr.domain.StockQuote;
import Panhandlr.services.QuoteSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockPriceRepository {


    private final QuoteSource quoteSource;

    public StockPriceRepository(QuoteSource quoteSource) {
        this.quoteSource = quoteSource;
    }

    public List<StockQuote> getLatestPricesFor(String[] stocks) {
        List<StockQuote> stockQuoteList = new ArrayList<StockQuote>();

        try {
            stockQuoteList.addAll(quoteSource.getLatestPriceDetails(stocks));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockQuoteList;
    }
}

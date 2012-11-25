package Panhandlr.services;

import Panhandlr.domain.StockQuote;

import java.io.IOException;
import java.util.List;

public interface QuoteSource {
    List<StockQuote> getLatestPriceDetails(String[] stockSymbols) throws IOException;
}

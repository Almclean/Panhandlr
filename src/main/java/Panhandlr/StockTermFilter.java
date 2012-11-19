package Panhandlr;

public class StockTermFilter implements TermFilter {
    private final String[] stocks;

    public StockTermFilter(String[] stocks) {
        this.stocks = stocks;
    }

    @Override
    public String[] getTerms() {
        return stocks;
    }
}

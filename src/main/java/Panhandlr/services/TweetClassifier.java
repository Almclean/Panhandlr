package Panhandlr.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetClassifier {
    private final List<String> stockList;
    private final Pattern pat;


    public TweetClassifier(List<String> stockList) {
        this.stockList = stockList;

        StringBuilder stringBuilder = new StringBuilder();

        for (String entry : stockList) {
            stringBuilder.append("(" + entry + ")");
            stringBuilder.append("|");
        }
        this.pat = Pattern.compile(stringBuilder.toString(), Pattern.CASE_INSENSITIVE);
    }

    public Set<String> classify(String tweet) {
        Matcher matcher = pat.matcher(tweet);
        Set<String> resultSet = new HashSet<>();

        while (matcher.find()) {
            String stock = matcher.group().toUpperCase();
            if (!"".equals(stock)) {
                resultSet.add(stock);
            }
        }
        return resultSet;
    }

    public List<String> getStockList() {
        return stockList;
    }

}
package Panhandlr.main;

import Panhandlr.domain.StockStatusListener;
import Panhandlr.domain.SyphoningStatusListener;
import Panhandlr.domain.Tweet;
import Panhandlr.repositories.TweetRepository;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.util.List;

/**
 * Main entry point for Panhandlr app.
 */

public class Main {

    public static void main(String[] args) {
        //String[] stocks = {"#GOOG", "#AAPL", "#MSFT", "#ARMH", "#HBC", "#AMZN", "#TLH", "#TSLA", "#ADBE",
        //        "$GOOG", "$AAPL", "$MSFT", "$ARMH", "$HBC", "$AMZN", "$TLH", "$TSLA", "$ADBE"};

        String[] stocks = {"manflu", "#manflu"};

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        SyphoningStatusListener statusListener = new StockStatusListener(stocks);

        TweetRepository repository = new TweetRepository(twitterStream, statusListener);

        try {
            List<Tweet> tweets = repository.getFixedNumberOfTweets(5);

            for (Tweet t : tweets) {
                System.out.println(t.getUserName() + " : " + t.getMessageText().replace('\n', ' '));
            }

            repository.closeRepository();

        } catch (InterruptedException e) {
            System.err.println("Ooops, something has gone awry :");
            e.printStackTrace();
        }

    }

}

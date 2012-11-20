package Panhandlr.repositories;

import Panhandlr.domain.TermFilter;
import Panhandlr.domain.TwitterStreamConnection;
import Panhandlr.services.StreamFactory;
import twitter4j.Tweet;

import java.util.List;

/**
 * Manages getting tweets, either continuosly or in a batch.
 */
public class TweetRepository {

    private final TwitterStreamConnection twitterStreamConnection;

    public TweetRepository(StreamFactory streamFactory, TermFilter termFilter) {
        this.twitterStreamConnection = streamFactory.getNewStreamConnection(termFilter);
    }

    public List<Tweet> getFixedNumberOfTweets(int numberOfTweets) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}

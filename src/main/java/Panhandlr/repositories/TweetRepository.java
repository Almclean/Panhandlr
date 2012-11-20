package Panhandlr.repositories;

import Panhandlr.domain.TermFilter;
import twitter4j.Tweet;
import twitter4j.TwitterStream;

import java.util.List;

/**
 * Manages getting tweets, either continuosly or in a batch.
 */
public class TweetRepository {

    private final TwitterStream twitterStream;

    public TweetRepository(TwitterStream twitterStream, TermFilter termFilter) {
        this.twitterStream = twitterStream;
    }

    public List<Tweet> getFixedNumberOfTweets(int numberOfTweets) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}

package Panhandlr.repositories;

import Panhandlr.domain.SyphoningStatusListener;
import Panhandlr.domain.Tweet;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Manages getting tweets, either continuosly or in a batch.
 */
public class TweetRepository {

    private final TwitterStream twitterStream;
    private final SyphoningStatusListener statusListener;

    public TweetRepository(TwitterStream twitterStream, SyphoningStatusListener statusListener) {
        this.twitterStream = twitterStream;
        this.statusListener = statusListener;

        twitterStream.addListener(statusListener);
    }

    public List<Tweet> getFixedNumberOfTweets(int numberOfTweets) throws InterruptedException {

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(statusListener.getFilterTerms());
        twitterStream.filter(filterQuery);

        int counter = 0;
        List<Tweet> retList = new ArrayList<Tweet>();
        BlockingQueue<Tweet> tweets = statusListener.syphon();

        while (counter < numberOfTweets) {
            retList.add(tweets.take());
            counter++;
        }

        return retList;
    }

}

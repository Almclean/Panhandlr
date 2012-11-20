package Panhandlr;

import Panhandlr.domain.StockStatusListener;
import Panhandlr.domain.Tweet;
import Panhandlr.repositories.TweetRepository;
import org.junit.Test;
import twitter4j.TwitterStream;

import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TwitterTests {


    @Test
    public void testThatRepoReturnsTheCorrectNumberOfSamples() {
        String[] stocks = {"GOOG", "MSFT", "AAPL"};

        TwitterStream twitterStream = createMock(TwitterStream.class);
        TweetRepository tweetRepository = new TweetRepository(twitterStream, new StockStatusListener(stocks));

        List<Tweet> tweetList = null;

        try {
            tweetList = tweetRepository.getFixedNumberOfTweets(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(tweetList.size(), is(5));
    }

}

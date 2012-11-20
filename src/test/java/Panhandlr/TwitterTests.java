package Panhandlr;

import Panhandlr.domain.StockTermFilter;
import Panhandlr.repositories.TweetRepository;
import Panhandlr.services.StreamFactory;
import org.junit.Test;
import twitter4j.Tweet;

import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TwitterTests {


    @Test
    public void testThatFiltersFilterTheCorrectTerms() {

        StreamFactory mockStreamFactory = createMock(StreamFactory.class);

        String[] stocks = {"GOOG", "MSFT", "AAPL"};

        TweetRepository tweetRepository = new TweetRepository(mockStreamFactory, new StockTermFilter(stocks));

        List<Tweet> tweetList = tweetRepository.getFixedNumberOfTweets(5);
        assertThat(tweetList.size(), is(5));
    }

}

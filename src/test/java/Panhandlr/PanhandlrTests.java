package Panhandlr;

import Panhandlr.domain.StockQuote;
import Panhandlr.domain.SyphoningStatusListener;
import Panhandlr.domain.Tweet;
import Panhandlr.repositories.StockPriceRepository;
import Panhandlr.repositories.TweetRepository;
import Panhandlr.services.QuoteSource;
import org.junit.Test;
import twitter4j.TwitterStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PanhandlrTests {


    @Test
    public void testThatRepoReturnsTheCorrectNumberOfSamples() {
        TwitterStream twitterStream = mock(TwitterStream.class);

        SyphoningStatusListener statusListener = mock(SyphoningStatusListener.class);
        when(statusListener.syphon()).thenReturn(createTestBlockingQueue());

        TweetRepository tweetRepository = new TweetRepository(twitterStream, statusListener);

        List<Tweet> tweetList = null;

        try {
            tweetList = tweetRepository.getFixedNumberOfTweets(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(tweetList.size(), is(5));
    }

    @Test
    public void testThatStockPricesAreParsedCorrectly() throws IOException {
        String[] stocks = {"GOOG", "AAPL"};
        String[] prices = {"\"Google Inc.\",\"GOOG\",667.97,669.97,665.87\n",
                "\"Apple Inc.\",\"AAPL\",571.50,567.39,561.70\n"};

        List<StockQuote> stockQuoteListRaw = new ArrayList<StockQuote>();
        stockQuoteListRaw.add(StockQuote.parseRawDetails(prices[0]));
        stockQuoteListRaw.add(StockQuote.parseRawDetails(prices[1]));

        QuoteSource mockConnectionHandler = mock(QuoteSource.class);
        when(mockConnectionHandler.getLatestPriceDetails(stocks)).thenReturn(stockQuoteListRaw);

        StockPriceRepository stockRepository = new StockPriceRepository(mockConnectionHandler);

        StockQuote google = new StockQuote("Google Inc.", "GOOG", "667.97", "669.97", "665.87");
        StockQuote apple = new StockQuote("Apple Inc.", "AAPL", "571.50", "567.39", "561.70");

        List<StockQuote> stockQuotes = new ArrayList<StockQuote>();
        stockQuotes.add(google);
        stockQuotes.add(apple);

        List<StockQuote> actualQuotes = stockRepository.getLatestPricesFor(stocks);

        assertThat(actualQuotes, is(stockQuotes));
    }

    private BlockingQueue<Tweet> createTestBlockingQueue() {
        BlockingQueue<Tweet> blockingQueue = new LinkedBlockingDeque<Tweet>();
        blockingQueue.add(new Tweet("This is a tweet", "almclean"));
        blockingQueue.add(new Tweet("This is another tweet", "blah_peer"));
        blockingQueue.add(new Tweet("This is another tweet", "blah_xoom"));
        blockingQueue.add(new Tweet("This is another tweet", "blah_gash"));
        blockingQueue.add(new Tweet("This is another tweet", "blah_halb"));

        return blockingQueue;
    }


}

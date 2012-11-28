package Panhandlr;

import Panhandlr.domain.StockQuote;
import Panhandlr.domain.SyphoningStatusListener;
import Panhandlr.domain.Tweet;
import Panhandlr.repositories.StockPriceRepository;
import Panhandlr.repositories.TweetRepository;
import Panhandlr.services.QuoteSource;
import Panhandlr.services.TweetClassifier;
import org.junit.Test;
import twitter4j.TwitterStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Test
    public void testThatTweetsAreCategorisedProperlyAndLoadedIntoAResultMap() {

        List<String> stockList = new ArrayList<>();
        stockList.add("aapl");
        stockList.add("amzn");
        stockList.add("spy");

        Set<String> actualMap = new HashSet<>();

        actualMap.add("AAPL");
        actualMap.add("AMZN");
        actualMap.add("SPY");

        String tweetOne = "EOD ETF balancing responsible for most algo programs during the last 15 minutes. $spy $vxx $aapl http://t.co/8hPrni02";
        String tweetTwo = "$amzn at $247";
        String tweetThree = "$AAPL called in a plumber to stop the faucet leak lower. Peeling off short now.";

        TweetClassifier tweetClassifier = new TweetClassifier(stockList);

        assertThat(tweetClassifier.classify(tweetOne).size(), is(2));
        assertThat(tweetClassifier.classify(tweetTwo).size(), is(1));
        assertThat(tweetClassifier.classify(tweetThree).size(), is(1));
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

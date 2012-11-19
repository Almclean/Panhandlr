package Panhandlr;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;

public class TwitterTests {

    @Test
    public void testThatFiltersFilterTheCorrectTerms() {

        String[] stocks = {"GOOG", "MSFT", "AAPL"};

        TermFilter testTermFilter = new StockTermFilter(stocks);

        TwitterStreamConnection testTwitterStreamConnection = StreamFactory.getNewStreamConnection(testTermFilter);

        List<String> messages = testTwitterStreamConnection.getSampleStreamMessages(10);

        assertThat(messages.size(), is(10));
    }
}

package Panhandlr;

import org.junit.Test;

import java.util.List;

public class TwitterTests {

    @Test
    public void testThatFiltersFilterTheCorrectTerms() {

        String[] stocks = {"GOOG", "MSFT", "AAPL"};

        TermFilter testTermFilter = new StockTermFilter(stocks);

        TwitterStreamConnection testTwitterStreamConnection = TestStreamFactory.getNewStreamConnection(testTermFilter);

        List<String> messages = testTwitterStreamConnection.getSampleStreamMessages(10);

        assertThat(messages.size(), is(10));
    }
}

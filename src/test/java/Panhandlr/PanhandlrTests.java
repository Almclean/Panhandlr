package Panhandlr;

import Panhandlr.domain.SyphoningStatusListener;
import Panhandlr.domain.Tweet;
import Panhandlr.repositories.TweetRepository;
import org.junit.Test;
import twitter4j.TwitterStream;

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
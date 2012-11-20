package Panhandlr.domain;

import twitter4j.Status;
import twitter4j.StatusDeletionNotice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class StockStatusListener implements SyphoningStatusListener {

    private BlockingQueue<Tweet> tweetQueue;
    private final String[] stocks;

    public StockStatusListener(String[] stocks) {
        this.stocks = stocks;
        this.tweetQueue = new LinkedBlockingDeque<Tweet>();
    }

    @Override
    public void onStatus(Status status) {
        tweetQueue.add(new Tweet(status.getText(), status.getUser().getScreenName()));
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
    }

    @Override
    public void onException(Exception ex) {
    }

    @Override
    public BlockingQueue syphon() {
        return tweetQueue;
    }

    @Override
    public String[] getFilterTerms() {
        return stocks;
    }
}

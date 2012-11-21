package Panhandlr.domain;

import twitter4j.StatusListener;

import java.util.concurrent.BlockingQueue;

public interface SyphoningStatusListener extends StatusListener {
    public BlockingQueue<Tweet> syphon();

    public String[] getFilterTerms();
}

package Panhandlr.domain;

import twitter4j.StatusListener;

import java.util.concurrent.BlockingQueue;

public interface SyphoningStatusListener extends StatusListener {
    public <T> BlockingQueue<T> syphon();

    public String[] getFilterTerms();
}

package Panhandlr.services;

import Panhandlr.domain.TermFilter;
import Panhandlr.domain.TwitterStreamConnection;

public class StreamFactory implements StreamSource {
    public TwitterStreamConnection getNewStreamConnection(TermFilter testTermFilter) {
        return new TwitterStreamConnection();
    }
}

package Panhandlr.services;

import Panhandlr.domain.TermFilter;
import Panhandlr.domain.TwitterStreamConnection;

/**
 * Represents a source of tweets
 */

public interface StreamSource {
    public TwitterStreamConnection getNewStreamConnection(TermFilter testTermFilter);
}

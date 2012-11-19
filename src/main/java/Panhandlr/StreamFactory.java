package Panhandlr;

public class StreamFactory {
    public static TwitterStreamConnection getNewStreamConnection(TermFilter testTermFilter) {
        return new TwitterStreamConnection();
    }
}

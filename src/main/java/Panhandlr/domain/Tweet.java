package Panhandlr.domain;

/**
 * Represents the data contained in a Tweet
 */

public class Tweet {
    private final String messageText;
    private final String userName;

    public Tweet(String messageText, String userName) {
        this.messageText = messageText;
        this.userName = userName;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getUserName() {
        return userName;
    }
}

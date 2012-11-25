package Panhandlr.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        return hashCodeBuilder
                .append(messageText)
                .append(userName)
                .hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!o.getClass().equals(this.getClass())) {
            return false;
        } else if (o == this) {
            return true;
        } else {
            Tweet rhs = (Tweet) o;
            EqualsBuilder equalsBuilder = new EqualsBuilder();
            return equalsBuilder
                    .append(this.messageText, rhs.messageText)
                    .append(this.userName, rhs.userName)
                    .isEquals();
        }
    }
}

package PanhandlrSpike;

import twitter4j.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        StatusListener statusListener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println(status.getUser() + " : " + status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            @Override
            public void onTrackLimitationNotice(int i) {
            }

            @Override
            public void onScrubGeo(long l, long l1) {
            }

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
        };

        String[] myFilter = new String[]{"$GOOG", "$AAPL", "$MSFT", "$ARMH", "$HBC", "$AMZN", "$TLH", "$TSLA", "$ADBE"};

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(statusListener);

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(myFilter);

        twitterStream.filter(filterQuery);

        System.out.println("Twitter library configured");
    }
}

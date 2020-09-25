package external;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;;

public class TwitterClient {
	private static final String ACCESS_TOKEN = "1306318150175088640-g8xzQRnOViBlel6PTA50DyHR9nlZvS";
	private static final String ACCESS_TOKEN_SECRET = "M6ZqtrvFfuEzv13ZZUyKy8bIIaXQ4SCzGYO4hq8ggGB2g";
	private static final String API_KEY = "pv1WMKZLhKVF5c2ZBBYRAqhFl";
	private static final String API_SECRET_KEY = "HKbTdofIc9jWTLxYwa5Y7XqWvXaeyau8bBuPOc1TYGufcV3bJW";
	private static final int NUM_TWEETS = 3200;
	
	public static List<Status> getPosts(String screenName) {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(API_KEY, API_SECRET_KEY);
		twitter.setOAuthAccessToken(new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET));
		ResponseList<Status> tweets = null;
		List<Status> listTweets = new ArrayList<>();
		
		try {
			tweets = twitter.getUserTimeline(screenName, new Paging(1, NUM_TWEETS));
			
			for(Status tweet:tweets) {
				//make sure to filter out link and tags to other users
				if(!tweet.isRetweet() && tweet.getContributors() == null) {
					listTweets.add(tweet);
				}
			}
			
		} catch (TwitterException e) {
			e.printStackTrace();
			System.out.println("Failed to get timeline: " + e.getErrorMessage());
			System.exit(-1);
		}
		
		return listTweets;
	}
	
	public static String randText(String screenName) {
		List<Status> list = getPosts(screenName);
		Random rand = new Random();
		int upperbound = list.size() - 1;
		int randomIndex = rand.nextInt(upperbound);
		return list.get(randomIndex).getText();
	}

}

package rpc;

import java.util.Random;
import java.util.Scanner;

import external.TwitterClient;

public class Game {
	private static final String ELON_MUSK = "elonmusk";
	private static final String KANYE = "kanyewest";
	
	public static void main(String args[]) {
		TwitterClient twitterClient = new TwitterClient();
		int total = 0, correct = 0;
		Scanner scanner = new Scanner(System.in);
		String ans = scanner.nextLine();
		
		do {
			int random = (int)Math.random();
			String randTweet;
			boolean elon = random % 2 == 0;
		
			if(elon) {
				randTweet = TwitterClient.randText(ELON_MUSK);
			} else {
				randTweet = twitterClient.randText(KANYE);
			}
			
			System.out.println("Who posted this tweet? /n a. Kanye /n b. Elon Musk /n");
			ans = scanner.nextLine();
			
			if((elon && ans.equalsIgnoreCase("b")) || (!elon && ans.equalsIgnoreCase("a"))) {
				++correct;
				System.out.println("Correct!");
			} else {
				System.out.println("Incorrect.");
			}
			
			System.out.println("Again? (Y/N)");
			ans = scanner.nextLine();
			++total;	
			
		} while (ans.equalsIgnoreCase("Y"));
		
		System.out.println("Your got " + correct + " questions correct out of " + total + " ! Good job!");
		
	}
}

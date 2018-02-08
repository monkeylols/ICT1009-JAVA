
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class SearchHashtag {

	final String CONSUMER_KEY = "xnDE5CcSmD7V5ZjtiHCUjiep9";
	final String CONSUMER_KEY_SECRET = "0OAEjLRpPAkSilFf5ZzbpGlYDrCDMIeUm58Fv4ysTgYALvdA39";
	final String ACCESS_TOKEN = "243144124-KJgrT0NahOcE8TSohnnrrOd5mcpwJiKYnkro0wFa";
	final String ACCESS_TOKEN_SECRET = "Cw5g89e9v8xq1iF5orEgG1GrctpU1Jxies4QHmage9MxJ";
	Status status;

	void run(ArrayList<String> hashTags, int count) throws FileNotFoundException, IOException, TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		;
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
		twitter.setOAuthAccessToken(accessToken);
		int innerCount = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = new DatabaseConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String hashTag : hashTags) {
			System.out.println("==============================================");
			System.out.println("Start of " + hashTag);
			System.out.println("==============================================");
			Query query = new Query(hashTag);
			int eachCount = count;
			if (eachCount <= 100) {
				query.setCount(eachCount);
				QueryResult result = twitter.search(query);

				for (Status status : result.getTweets()) {
					innerCount++;
					System.out.println(innerCount + " @ " + status.getUser().getScreenName() + " : " + status.getText()
							+ " : " + status.getCreatedAt());
					HashtagEntity[] hea = status.getHashtagEntities();
					String hashtag = "";
					for (int i = 0; i < hea.length; i++) {
						if (i == hea.length - 1) {
							hashtag += hea[i].getText();
						} else {
							hashtag += hea[i].getText() + ",";
						}
					}
					try {
						stmt = con.prepareStatement(
								"INSERT INTO twitterdata (userid, name, tweets, tweetsdt, hashtag) VALUES (?,?,?,?,?)");
						stmt.setLong(1, status.getUser().getId());
						stmt.setString(2, status.getUser().getScreenName());
						stmt.setString(3, status.getText());
						stmt.setTimestamp(4, new java.sql.Timestamp(status.getCreatedAt().getTime()));
						stmt.setString(5, hashtag);
						stmt.executeUpdate();
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			} else {
				query.setCount(100);
				QueryResult result = twitter.search(query);
				for (Status status : result.getTweets()) {
					innerCount++;
					System.out.println(innerCount + " @ " + status.getUser().getScreenName() + " : " + status.getText()
							+ " : " + status.getCreatedAt());
					HashtagEntity[] hea = status.getHashtagEntities();
					String hashtag = "";
					for (int i = 0; i < hea.length; i++) {
						if (i == hea.length - 1) {
							hashtag += hea[i].getText();
						} else {
							hashtag += hea[i].getText() + ",";
						}
					}
					try {
						stmt = con.prepareStatement(
								"INSERT INTO twitterdata (userid, name, tweets, tweetsdt, hashtag) VALUES (?,?,?,?,?)");
						stmt.setLong(1, status.getUser().getId());
						stmt.setString(2, status.getUser().getScreenName());
						stmt.setString(3, status.getText());
						stmt.setTimestamp(4, new java.sql.Timestamp(status.getCreatedAt().getTime()));
						stmt.setString(5, hashtag);
						stmt.executeUpdate();
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
				eachCount -= 100;
				if (result.hasNext()) {

					while (eachCount > 0) {
						if (eachCount <= 100) {
							query.setCount(eachCount);
							query = result.nextQuery();
							result = twitter.search(query);
							for (Status status : result.getTweets()) {
								innerCount++;
								System.out.println(innerCount + " @ " + status.getUser().getScreenName() + " : "
										+ status.getText() + " : " + status.getCreatedAt());
								HashtagEntity[] hea = status.getHashtagEntities();
								String hashtag = "";
								for (int i = 0; i < hea.length; i++) {
									if (i == hea.length - 1) {
										hashtag += hea[i].getText();
									} else {
										hashtag += hea[i].getText() + ",";
									}
								}
								try {
									stmt = con.prepareStatement(
											"INSERT INTO twitterdata (userid, name, tweets, tweetsdt, hashtag) VALUES (?,?,?,?,?)");
									stmt.setLong(1, status.getUser().getId());
									stmt.setString(2, status.getUser().getScreenName());
									stmt.setString(3, status.getText());
									stmt.setTimestamp(4, new java.sql.Timestamp(status.getCreatedAt().getTime()));
									stmt.setString(5, hashtag);
									stmt.executeUpdate();
								} catch (SQLException e) {

									e.printStackTrace();
								}
							}
						} else {
							query.setCount(100);
							query = result.nextQuery();
							result = twitter.search(query);
							for (Status status : result.getTweets()) {
								innerCount++;
								System.out.println(innerCount + " @ " + status.getUser().getScreenName() + " : "
										+ status.getText() + " : " + status.getCreatedAt());
								HashtagEntity[] hea = status.getHashtagEntities();
								String hashtag = "";
								for (int i = 0; i < hea.length; i++) {
									if (i == hea.length - 1) {
										hashtag += hea[i].getText();
									} else {
										hashtag += hea[i].getText() + ",";
									}
								}
								try {
									stmt = con.prepareStatement(
											"INSERT INTO twitterdata (userid, name, tweets, tweetsdt, hashtag) VALUES (?,?,?,?,?)");
									stmt.setLong(1, status.getUser().getId());
									stmt.setString(2, status.getUser().getScreenName());
									stmt.setString(3, status.getText());
									stmt.setTimestamp(4, new java.sql.Timestamp(status.getCreatedAt().getTime()));
									stmt.setString(5, hashtag);
									stmt.executeUpdate();
								} catch (SQLException e) {

									e.printStackTrace();
								}
							}

						}
						eachCount -= 100;
					}
				}
			}
			System.out.println("==============================================");
			System.out.println("End of " + hashTag);
			System.out.println("==============================================");
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

import java.sql.Date;

public class TwitterPost {

	private long userid;
	private String username;
	private String tweets;
	private Date tweetdt;
	private String hashtag;
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTweets() {
		return tweets;
	}
	public void setTweets(String tweets) {
		this.tweets = tweets;
	}
	public Date getTweetdt() {
		return tweetdt;
	}
	public void setTweetdt(Date tweetdt) {
		this.tweetdt = tweetdt;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
}

import java.util.Date;

public class Articles {

	private String url;
	private String content;
	private String title;
	private Date crawledDate;
	
	
	public Articles(){
		
	}
	public Articles(String url, String title, String content){
		this.url = url;
		this.content = content;
		this.title = title;
		this.crawledDate = new Date();
		System.out.println(url);
		System.out.println(content);
		System.out.println(title);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCrawledDate() {
		return crawledDate;
	}
	public void setCrawledDate(Date crawledDate) {
		this.crawledDate = crawledDate;
	}
}

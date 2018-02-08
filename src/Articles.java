
public class Articles {

	private String url;
	private String content;
	private String title;
	public Articles(){
		
	}
	public Articles(String url, String content, String title){
		this.url = url;
		this.content = content;
		this.title = title;
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
}

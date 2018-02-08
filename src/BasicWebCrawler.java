import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BasicWebCrawler {

	private HashSet<String> links;
	private String baseURL;
	private static ArrayList<Articles> articleList;
	
    public BasicWebCrawler() {
        links = new HashSet<String>();
        articleList = new ArrayList<Articles>();
    }
    
    public void getPageLinks(String URL, int depth) {
        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
    	if(links.size() == 0) {
    		baseURL = URL;
    	}
    	if(URL.contains(baseURL)) {
    		if (!links.contains(URL) && depth > 0) {
            	depth--;
                try {
                    //4. (i) If not add it to the index
                	/*
                    if (links.add(URL)) {
                        System.out.println(URL);
                    }
					*/
                	links.add(URL);
                    //2. Fetch the HTML code
                    Document document = Jsoup.connect(URL).get();
                    //3. Parse the HTML to extract links to other URLs
                    Elements linksOnPage = document.select("a[href]");
                    Elements articleOnPage = document.getElementsByTag("article");
                    for (Element article : articleOnPage) {
                    	Articles art = new Articles(URL, article.text(),document.selectFirst("h1").text());
                    	articleList.add(art);
                    }
                    //5. For each extracted URL... go back to Step 4.
                    for (Element page : linksOnPage) {
                        getPageLinks(page.attr("abs:href"), depth);
                    }
                    
                } catch (IOException e) {
                    System.err.println("For '" + URL + "': " + e.getMessage());
                }
            }
    	}
        
    }

    public static void main(String[] args) throws IOException, SQLException {
        //1. Pick a URL from the frontier
    	
        BasicWebCrawler crawler = new BasicWebCrawler();
        crawler.getPageLinks("https://www.channelnewsasia.com/", 10);
        Connection con = null;
        PreparedStatement stmt = null;
        try {
			con = new DatabaseConnection().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	stmt = con.prepareStatement("INSERT INTO webdata (url, title, content) VALUES (?,?,?)");

        
        BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
        for(Articles article : articleList) {
        	stmt.setString(1, article.getUrl());
        	stmt.setString(2, article.getTitle());
        	stmt.setString(3, article.getContent());
        	writer.write(article.getUrl() + "\n");
        	writer.write(article.getTitle() + "\n");
        	writer.write(article.getContent() + "\n");
        	stmt.executeUpdate();
        	
        }
        writer.close();
        con.close();
    }
}

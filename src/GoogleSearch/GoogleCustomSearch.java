package GoogleSearch;
import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Element;
public class GoogleCustomSearch {

	public void search(String term) {
		String query = term;
		String encoding = "UTF-8";
		try {
			
			Document google = Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(term, encoding)).userAgent("Mozilla/5.0").get();  
			
			   Elements webSitesLinks = google.getElementsByTag("cite");
				
				//Check if any results found
				if (webSitesLinks.isEmpty()) {
					System.out.println("No results found");
					return;
				}
				
				webSitesLinks.forEach(link->System.out.println(link.text()));
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
package GoogleSearch;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import TextCorpusAquisition.HTMLExtraction;

public class GoogleCustomSearch {

	public void search(String term) {
		String query = term;
		String encoding = "UTF-8";
		Document google = null;
		try {
			google = Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(term + " filetype:html", encoding)).userAgent("Mozilla/5.0").get();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		   Elements webSitesLinks = google.getElementsByTag("cite");
			
			//Check if any results found
			if (webSitesLinks.isEmpty()) {
				System.out.println("No results found");
				return;
			}
			
			webSitesLinks.forEach(link->System.out.println(link.text()));
			/*HTMLExtraction extractor = new HTMLExtraction();
			for (Element element : webSitesLinks)
			{
				extractor.extract(element.text());
			}*/
		
	}

	
}
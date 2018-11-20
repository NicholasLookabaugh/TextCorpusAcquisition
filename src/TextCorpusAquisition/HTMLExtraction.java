package TextCorpusAquisition;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
public class HTMLExtraction {
	
	private String url;
	private String title;
	private Document htmlDoc;
	private Element body;

	
		public HTMLExtraction(){

		}
		
				//Connect to web page and extract HTML
		public Document establishConnection(String url){
			
			try {
				htmlDoc = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return htmlDoc;
		}
		
		public void getMetaData(String url1) {
			String url = url1;  
			try {
				Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
				String title = doc.title();
	            System.out.println(title);
				/*for(Element meta : doc.select("meta")) {
					System.out.println("Name: " + meta.attr("name") + " - Content: " + meta.attr("content"));
				}*/
			}
			catch(Exception e) {
				System.out.println("Error");
			}
		}
		public void extractMetaList(ArrayList<String> list) {
			for (int counter = 0; counter < list.size(); counter++) { 		      
		          getMetaData(list.get(counter)); 		
		      } 
		}
		
		
		public void extract(String url1) {
			try {
			//URL to test
		      String url = url1;   
		      setUrl(url);
		      
		      		//Document with HTML
		      Document htmlDoc = Jsoup.connect(url)
		              .userAgent("Mozilla")
		              .timeout(0).get();

		      		//web page title
		      title =  htmlDoc.title();
		      setTitle(title);
	
		      		//get html body and cleaned body
		      Element body = htmlDoc.body();
		      Elements paragraphs = setBody(body);
		      
		      		//print cleaned body text
		      for (Element paragraph : paragraphs) {
		         System.out.println(paragraph.text());
		      }
			}
			catch(Exception e) {
				System.out.println("Error");
			}

		}
		public void extractList(ArrayList<String> list) {
			for (int counter = 0; counter < list.size(); counter++) { 		      
		          extract(list.get(counter)); 		
		      } 
		}
		public String getTitle(){
			
			return title;
		}
		
		public void setTitle(String title){
			
			this.title = title;
		}
		
		public Elements setBody(Element body){
			
			Elements paragraphs = body.getElementsByTag("p");
			
			return paragraphs;
		}
		
		public String getUrl(){
			
			return url;
		}
		
		public void setUrl(String url){
			
			this.url = url;
		}

		
		
		
	}
	



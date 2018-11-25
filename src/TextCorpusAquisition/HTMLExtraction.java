package TextCorpusAquisition;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class HTMLExtraction {
	
	private String url;
	private String title;
	private Document htmlDoc;
	private Element body;
	private Elements paragraph;
	private String searchPhrase;
	private int wordCount;
	private String dateTime;
	
	private static AtomicInteger pKey = new AtomicInteger(0);

	
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
		      
		      		// Date and time of connection
		      String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		      setDateTime(dateTime);
		      		//Document with HTML
		      Document htmlDoc = Jsoup.connect(url)
		              .userAgent("Mozilla")
		              .timeout(0).get();

		      setHTMLDoc(htmlDoc);
		      		//web page title
		      title =  htmlDoc.title();
		      setTitle(title);
	
		      		//get html body and cleaned body
		      Element body = htmlDoc.body();
		      Elements paragraphs = setBody(body);
		      setParagraph(paragraphs);
		      		//print cleaned body text
		      for (Element paragraph : paragraphs) {
		         System.out.println(paragraph.text());
		      }
		      
		      Pattern patt = Pattern.compile("[a-zA-Z]+");
		      Matcher match = patt.matcher(paragraphs.text().toString());
		      wordCount = 0;
		      while(match.find())
		      {
		    	  wordCount++;
		      }
		      setWordCount(wordCount);
		      
		      SqlTest();
			}
			catch(Exception e) {
				System.out.println("Error");
			}

		}
		public void extractList(ArrayList<String> list) {
			for (int counter = 0; counter < list.size(); counter++) { 		      
		          extract(list.get(counter)); 		
		      } 

		      //Downloading the html file
			  /*final File f = new File("test.html");
			      
			  try 
			  {
				  FileUtils.writeStringToFile(f, htmlDoc.outerHtml());
			  } 
			  catch (IOException e) 
			  {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }*/

		}
		public void setHTMLDoc(Document htmlDoc)
		{
			this.htmlDoc = htmlDoc;
		}
		
		public Document getHTMLDoc()
		{
			return this.htmlDoc;
		}
		
		public String getTitle(){
			
			return this.title;
		}
		
		public void setTitle(String title){
			
			this.title = title;
		}
		
		public Elements setBody(Element body){
			
			Elements paragraphs = body.getElementsByTag("p");
			
			return paragraphs;
		}
		
		public void setParagraph(Elements paragraph)
		{
			this.paragraph = paragraph;
		}
		
		public Elements getParagraph()
		{
			return this.paragraph;
		}
		
		public void setDateTime(String dateTime)
		{
			this.dateTime = dateTime;
		}
		
		public String getDateTime()
		{
			return this.dateTime;
		}
		
		public void setSearchPhrase(String searchPhrase)
		{
			this.searchPhrase = searchPhrase;
		}
		
		public String getSearchPhrase()
		{
			searchPhrase = "Web crawler";
			return this.searchPhrase;
		}
		
		public String getUrl(){
			
			return this.url;
		}
		
		public void setUrl(String url){
			
			this.url = url;
		}
		
		public void setWordCount(int wordCount)
		{
			this.wordCount = wordCount;
		}
		
		public int getWordCount()
		{
			return this.wordCount;
		}

		public int getNextpKey()
		{
			return pKey.incrementAndGet();
		}
		
		public void SqlTest()
		{
		    String connectionUrl = ("jdbc:sqlserver://98.170.196.192:49170;databaseName=TextCorpusData;user=TextCorpusProgram;password=Pa$$word!");

		    try (Connection conn = DriverManager.getConnection(connectionUrl); Statement st = conn.createStatement();) 
		    {
		    	getNextpKey();
		    	String SQL_WebPageInfo = ("INSERT INTO [TextCorpusData].[WebPageInfo] VALUES(" + pKey + ", '" + getSearchPhrase() + "', '" + getUrl() + "', '" + getTitle() + "', '" + getDateTime() + "', " + getWordCount() + ");");
		        st.executeUpdate(SQL_WebPageInfo);

    			//Fix the closing ' for SQL insert
		    	String htmlFix = getHTMLDoc().toString().replace("\'", "\'\'");

		        String SQL_PageMetaData = ("INSERT INTO [TextCorpusData].[PageMetadata] (WebPage_ID, Page_HTML) VALUES (" + pKey + ", N'" + htmlFix + "');"); 
		        st.executeUpdate(SQL_PageMetaData);

				Pattern patt = Pattern.compile("[a-zA-Z]+");
				Matcher match = patt.matcher(getParagraph().text().toString());

				while (match.find()) 
				{ 
		            String SQL_WordsExtracted = ("INSERT INTO [TextCorpusData].[WordsExtracted] VALUES (" + pKey + ", '" + match.group() + "');"); 
			        st.executeUpdate(SQL_WordsExtracted);
				}
		    }
		    // Handle any errors that may have occurred.
		    catch (SQLException e) 
		    {
		        e.printStackTrace();
		    }

		}
	}
package XmlMaker;

public class XmlMaker {
	private String title;
	private String timeAccessed;
	private String link;
	private String searchTerm;
	
	public XmlMaker(String title, String timeAccessed, String link, String searchTerm)
	{
		this.setTitle(title);
		this.setTimeAccessed(timeAccessed);
		this.setLink(link);
		this.setSearchTerm(searchTerm);
		this.makeXml();
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTimeAccessed(String timeAccessed)
	{
		this.timeAccessed = timeAccessed;
	}
	
	public String getTimeAccessed()
	{
		return this.timeAccessed;
	}
	
	public void setLink(String link)
	{
		this.link = link;
	}
	
	public String getLink()
	{
		return this.link;
	}
	
	public void setSearchTerm(String searchTerm)
	{
		this.searchTerm = searchTerm;
	}
	
	public String getSearchTerm()
	{
		return this.searchTerm;
	}
	
	public void makeXml()
	{
		
	}
}

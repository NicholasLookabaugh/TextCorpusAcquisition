package testCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import GoogleSearch.GoogleCustomSearch;
public class GoogleCustomSearchTests 
{
	@Test
	public void test() 
	{
		GoogleCustomSearch y = new GoogleCustomSearch();
		ArrayList<String> links = new ArrayList<String>();
		links = y.search("test" , 2);
		assertEquals("http://www.speedtest.net/", links.get(0));
	}

}

package WindowsApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import TextCorpusAquisition.HTMLExtraction;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

import GoogleSearch.GoogleCustomSearch;
public class CrawlerApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//HTMLExtraction x = new HTMLExtraction();
		//GoogleCustomSearch y = new GoogleCustomSearch();
		//y.search("freedom");
		//x.extract("https://money.cnn.com/2015/07/27/technology/android-text-hack/index.html");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrawlerApp window = new CrawlerApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	/**
	 * Create the application.
	 */
	public CrawlerApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Text Corpus Acquisition");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel =  new JPanel();
		JButton crawlButton = new JButton("Crawl");
		crawlButton.setPreferredSize(new Dimension(75,75));
		JTextField inputText = new JTextField();
		inputText.setPreferredSize(new Dimension(350,50));
		mainPanel.add(inputText);
		mainPanel.add(crawlButton);
		
		frame.getContentPane().add(mainPanel);
		
		crawlButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  //GoogleCustomSearch crawl = new GoogleCustomSearch();
			  //crawl.search(inputText.getText());
		  }
		});
	}

}

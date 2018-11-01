package WindowsApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import TextCorpusAquisition.HTMLExtraction;
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
		frame.setSize(500, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		JButton crawlButton = new JButton("Crawl");
		crawlButton.setBounds(25, 50, 100, 50);
		
		JTextField inputText = new JTextField();
		inputText.setBounds(150, 50, 300, 50);

		frame.add(crawlButton);
		frame.add(inputText);
		
		crawlButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  GoogleCustomSearch crawl = new GoogleCustomSearch();
			  crawl.search(inputText.getText());
		  }
		});
	}

}

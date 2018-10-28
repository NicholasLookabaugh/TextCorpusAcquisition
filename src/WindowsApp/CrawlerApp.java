package WindowsApp;

import java.awt.EventQueue;


import javax.swing.JFrame;
import TextCorpusAquisition.HTMLExtraction;
import javax.swing.JButton;
import java.awt.BorderLayout;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New button");
		frame.getContentPane().add(btnNewButton, BorderLayout.EAST);
	}

}

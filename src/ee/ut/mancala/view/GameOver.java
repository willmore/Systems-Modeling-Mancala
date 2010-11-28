package ee.ut.mancala.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameOver extends JPanel {

	private JButton close;
	private JTextArea display;
	
	/**
	 * Default constructor
	 */
	public GameOver()
	{
		super();
		initialize();
	}
	
	/**
	 * Initialize the controls and this
	 */
	private void initialize()
	{
		close = new JButton("Close");
		display = new JTextArea();
		this.add(close);
		this.add(display);
	}
	
	/**
	 * Set a text in the display (TextArea)
	 * @param text
	 */
	public void addText(String text)
	{
		display.setText(display.getText()+"\n"+text);		
	}
}

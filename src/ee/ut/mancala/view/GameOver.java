package ee.ut.mancala.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameOver extends JPanel {

	private JButton close;
	private JButton playAgain;
	private JLabel display;
	
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
		playAgain = new JButton("Play Again");
		display = new JLabel();
		display.setForeground(Color.GREEN);
		this.add(close);
		this.add(display);
		this.add(playAgain);
	}
	
	/**
	 * Set a text in the display (TextArea)
	 * @param text
	 */
	public void addText(String text)
	{
		display.setText(display.getText()+"\n"+text);		
	}
	
	public JButton getClose()
	{
		return close;
	}
	
	public JButton getPlayAgain()
	{
		return playAgain;
	}
}

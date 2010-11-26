package ee.ut.mancala.view;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ee.ut.mancala.History;

public class ShowHistory extends JPanel {

	private static int CLOSE_BUTTON_HEIGHT = 0;
	private static int CLOSE_BUTTON_WIDTH = 0;
	private static int TEXTBOX_HEIGHT = 0;
	private static int TEXTBOX_WIDTH = 0;
	private static String EMPTY = "";
	private static String ENTER = "\n";
	private static String GAME = "Game";
	private static String EMPTY_GAME_RECORDS = "No games played so far";

	private JButton close;
	private JTextArea scoresArea;

	public ShowHistory() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(400, 400);
		this.setLayout(new GridBagLayout());
		close = new JButton("Close");
		scoresArea = new JTextArea();
		this.add(scoresArea);
		this.add(close);
	}

	public JButton getCloseButton() {
		return close;
	}

	public void addRecord() {
		if (scoresArea.getText().compareTo(EMPTY) != 0)
			scoresArea.setText(ENTER);
		scoresArea.setText(ENTER + GAME + ENTER);
	}

	public void addScore(String player, int points) {
		scoresArea.setText(player + "got " + String.valueOf(points)+"     ");
	}

	public void clearRecords() {
		scoresArea.setText(EMPTY);
	}

	public void addEmptyRecords() {
		scoresArea.setText(EMPTY_GAME_RECORDS);
	}
}

package ee.ut.mancala.view;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.GridBagConstraints;
import java.awt.Dimension;
import javax.swing.JTextField;

public class PlayerEntry extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton submit = null;
	private JTextField player1Name = null;
	private JTextField player2Name = null;

	public String getPlayerOneName() {
		if (player1Name != null)
			return player1Name.getText();
		return "";
	}

	public String getPlayerTwoName() {
		if (player2Name != null)
			return player2Name.getText();
		return "";
	}

	public JButton getSubmitButton() {
		return submit;
	}

	/**
	 * This is the default constructor
	 */
	public PlayerEntry() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		this.setPreferredSize(new Dimension(100, 100));
		this.setLayout(new GridBagLayout());
		this.add(getSubmit(), gridBagConstraints);

		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 0;

		JLabel textPane = new JLabel();

		textPane.setText("Player 1");
		gridBagConstraints1.gridy = 0;
		this.add(textPane, gridBagConstraints1);

		this.player1Name = new JTextField();
		this.player1Name.setPreferredSize(new Dimension(90, 20));
		gridBagConstraints1.gridy = 1;
		this.add(this.player1Name, gridBagConstraints1);

		this.player2Name = new JTextField();
		this.player2Name.setPreferredSize(new Dimension(90, 20));

		gridBagConstraints1.gridx = 2;
		gridBagConstraints1.gridy = 0;

		textPane = new JLabel();
		textPane.setText("Player 2");
		this.add(textPane, gridBagConstraints1);
		gridBagConstraints1.gridy = 1;
		this.add(this.player2Name, gridBagConstraints1);

	}

	/**
	 * This method initializes submit
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSubmit() {
		if (submit == null) {
			submit = new JButton();
			submit.setText("Submit");
			submit.setPreferredSize(new Dimension(90, 30));
		}
		return submit;
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Demo");
		frame.setSize(new Dimension(500, 500));

		frame.add(new PlayerEntry());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}

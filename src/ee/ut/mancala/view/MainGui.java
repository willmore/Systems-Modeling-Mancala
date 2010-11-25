package ee.ut.mancala.view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.awt.Dimension;

public class MainGui {

	private JFrame jFrame = null;
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu fileMenu = null;
	private JMenu helpMenu = null;
	private JMenu showHistoryMenu = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem aboutMenuItem = null;
	private JMenuItem showHistoryMenuItem = null;
	private JDialog aboutDialog = null;
	private JPanel aboutContentPane = null;
	private JLabel aboutVersionLabel = null;
	private PlayerEntry playerEntry = null;
	private ShowHistory showHistory = null;
	private GameBoard gameBoard;
	
	public MainGui() {
		this.gameBoard = new GameBoard();
		this.gameBoard.setName("GameBoard");
		this.showHistory= new ShowHistory();
		this.showHistory.setName("History");
		this.playerEntry = new PlayerEntry();
		this.playerEntry.setName("PlayerEntry");
		this.getJContentPane().add(playerEntry,playerEntry.getName());
		this.getJContentPane().add(showHistory,showHistory.getName());
		this.getJContentPane().add(gameBoard,gameBoard.getName());
		CardLayout c = (CardLayout)this.getJContentPane().getLayout();
		c.show(this.getJContentPane(),playerEntry.getName());
	}

	/**
	 * 
	 * @return
	 */
	public GameBoard getGameBoard() {
		return this.gameBoard;
	}

	/**
	 * 
	 * @return
	 */
	public ShowHistory getShowHistory() {
		return this.showHistory;
	}

	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(400, 200);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("Mancala");
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			//jContentPane.setLayout(new BorderLayout());
			jContentPane.setLayout(new CardLayout());
			jContentPane.setPreferredSize(new Dimension(25, 25));
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getHelpMenu());
			jJMenuBar.add(getShowHistoryMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * 
	 * @return
	 */
	private JMenu getShowHistoryMenu() {
		if (showHistoryMenu == null) {
			showHistoryMenu = new JMenu();
			showHistoryMenu.setText("History");
			showHistoryMenu.add(getShowHistoryMenuItem());
		}
		return showHistoryMenu;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	public JMenuItem getShowHistoryMenuItem() {
		if (showHistoryMenuItem == null) {
			showHistoryMenuItem = new JMenuItem();
			showHistoryMenuItem.setText("Show Previous Games");
		}
		return showHistoryMenuItem;
	}

	/**
	 * This method initializes aboutDialog
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("About");
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	/**
	 * This method initializes aboutContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	/**
	 * This method initializes aboutVersionLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("Version 1.0");
			aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return aboutVersionLabel;
	}

	public void setVisible(boolean flag) {
		getJFrame().setVisible(flag);
	}

	public void hidePlayerEntry() {
		//getJContentPane().remove(playerEntry);
		CardLayout c = (CardLayout)this.getJContentPane().getLayout();
		
	}
	
	public void showGameBoard() {
		//getJContentPane().add(gameBoard);
		CardLayout c = (CardLayout)this.getJContentPane().getLayout();
		c.show(this.getJContentPane(), gameBoard.getName());
	}
	
	public void showPlayerEntry() {
		//getJContentPane().add(gameBoard);
		CardLayout c = (CardLayout)this.getJContentPane().getLayout();
		c.show(this.getJContentPane(), playerEntry.getName());
	}

	public void showHistory() {
		//getJContentPane().add(showHistory);
		CardLayout c = (CardLayout)this.getJContentPane().getLayout();
		c.show(this.getJContentPane(),showHistory.getName());
	}

	public void hideHistory() {
		getJContentPane().remove(showHistory);
	}

	public void refresh() {
		this.getJFrame().validate();
	}

	public PlayerEntry getPlayerEntry() {
		System.out.println("Getting Player Entry");
		return this.playerEntry;
	}

}

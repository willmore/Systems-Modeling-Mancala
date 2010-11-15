package ee.ut.mancala.controller;

import javax.swing.SwingUtilities;

import ee.ut.mancala.view.MainApplication;

public class GameController {

	
	public void start() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainApplication application = new MainApplication();
				application.setVisible(true);
			}
		});
	}
	
}

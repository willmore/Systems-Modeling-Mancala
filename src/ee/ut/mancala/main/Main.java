package ee.ut.mancala.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import ee.ut.mancala.controller.GameController;
import ee.ut.mancala.view.MainGui;

public class Main {

	/**
	 * Launches this application
	 */
	public static void main(String[] args) {
		System.out.println("Starting program");
		/*
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainApplication application = new MainApplication();
				application.setVisible(true);
				
				JButton b = application.getGameBoard().getHouseButton(0, 0);
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Pressed");
					}
				});
			}
		});
		*/
		new GameController().start();
	}

}

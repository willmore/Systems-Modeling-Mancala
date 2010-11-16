package ee.ut.mancala.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import ee.ut.mancala.view.MainGui;

public class GameController {

	
	public void start() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final MainGui application = new MainGui();
				
				//Wire start game button
				application.getPlayerEntry().getSubmitButton().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						application.hidePlayerEntry();
						//Get player names
						//Start new game
						application.showGameBoard();
						application.refresh();
					}
				});
				
				application.setVisible(true);
			}
		});
	}
	
}

package ui;

import javax.swing.JFrame;

public class BISystemUI {
	
	/***************  FRAME   ****************/
	
	private static JFrame frame;
	private static int frameWidth = 1000;
	private static int frameHeight = 500;
	
	/*****************************************/
	
	
	public static void run() {
		
		// Initialize JFrame.
		frame = new JFrame("Swayze's Business Intelligence Tool");
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

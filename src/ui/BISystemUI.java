package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BISystemUI {
	
	/*********************   FRAME   **********************/
	private static JFrame frame;
	private static int frameWidth = 1000;
	private static int frameHeight = 500;
	/******************************************************/
	
	/*************** LEFT AND RIGHT PANELS ****************/
	private static JPanel leftPanel;
	private static JPanel rightPanel;
	private static int panelWidth = 490;
	private static int panelHeight = 465;
	/******************************************************/
	
	/************** RIGHT PANEL COMPONENTS ****************/
	private static JTextArea display;
	private static JButton nextPage;
	private static JButton lastPage;
	private static JPanel buttonPanel;
	/******************************************************/
	
	/*************** LEFT PANEL COMPONENTS ****************/
	private static JLabel label;
	/******************************************************/
	
	
	public static void run() {
		
		// Initialize JFrame.
		frame = new JFrame("Swayze's Business Intelligence Tool");
		frame.setSize(frameWidth, frameHeight);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/******************************************************************/
		// Initialize leftPanel
		leftPanel = new JPanel(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		leftPanel.setBackground(Color.GRAY); // Temporary
		
		label = new JLabel("This is the control panel");
		
		leftPanel.add(label, BorderLayout.CENTER);
		/******************************************************************/
		// Initialize rightPanel
		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		//rightPanel.setBackground(Color.CYAN);
		
		// Create the display (JTextArea)
		display = new JTextArea();
		display.setEditable(false);
		display.setText("This is the display area");
		
		// Buttons for display
		lastPage = new JButton("<");
		nextPage = new JButton(">");
		
		// Add buttons to button panel
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(lastPage);
		buttonPanel.add(nextPage);
		/******************************************************************/
		// Add display to rightPanel
		rightPanel.add(display, BorderLayout.CENTER);
		
		// Add button panel to right panel
		rightPanel.add(buttonPanel, BorderLayout.NORTH);
		
		// Add leftPanel and rightPanel to frame.
		frame.add(leftPanel);
		frame.add(rightPanel);
		
		/******************************************************************/
		// And finally show the frame
		frame.setVisible(true);
		/******************************************************************/
		
	}
}

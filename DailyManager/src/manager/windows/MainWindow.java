package manager.windows;

import java.awt.EventQueue;

import javax.swing.*;
import manager.daily.*;
import manager.windows.*;

public class MainWindow {

	private JFrame frame;
	private JPanel currentPanel;
	private DailyManager dailyManager;
	private MainWindow thisWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		thisWindow = this;
		currentPanel = new DailyPanel(thisWindow, new DailyManager());
		initialize();
	}
	
	public MainWindow(JPanel panel){
		currentPanel = panel;
		initialize();
	}
	
	public MainWindow(DailyManager man){
		thisWindow = this;
		dailyManager = man;
		currentPanel = new DailyPanel(thisWindow, man);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(currentPanel);
	}
	
	public void enableFrame(boolean b){
		frame.setEnabled(b);
	}

}

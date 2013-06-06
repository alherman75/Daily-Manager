package manager.windows;

import java.awt.EventQueue;

import javax.swing.*;

import manager.Manager;
import manager.daily.*;
import manager.todo.ToDoManager;
import manager.windows.*;

public class MainWindow {

	private JFrame frame;
	private JPanel currentPanel;
	private DailyManager dailyManager;
	private ToDoManager toDoManager;
	private MainWindow thisWindow;
	private Manager manager;

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
		initialize();
		frame.setVisible(true);
	}
	
	public MainWindow(Manager man){
		manager = man;
		thisWindow = this;
		initialize();
		frame.setVisible(true);
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
	
	public MainWindow(ToDoManager man){
		thisWindow = this;
		toDoManager = man;
		currentPanel = new ToDoPanel(thisWindow, man);
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
		//frame.getContentPane().add(currentPanel);
	}
	
	public void enableFrame(boolean b){
		frame.setEnabled(b);
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public void setPanel(JPanel panel){
		currentPanel = panel;
		frame.getContentPane().removeAll();
		frame.getContentPane().add(manager.getMenuPanel());
		frame.getContentPane().add(currentPanel);
		frame.revalidate();
		//frame.setVisible(true);
		frame.repaint();
	}

}

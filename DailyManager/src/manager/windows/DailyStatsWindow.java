package manager.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import manager.daily.Daily;

import java.awt.Font;
import java.text.DecimalFormat;

public class DailyStatsWindow {

	
	private Daily daily;
	private MainWindow mainWindow;
	
	private JFrame frame;
	private JLabel completionNumber;
	private JLabel longestNumber;
	private JLabel currentNumber;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DailyStatsWindow window = new DailyStatsWindow();
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
	public DailyStatsWindow() {
		initialize();
	}
	
	public DailyStatsWindow(Daily d, MainWindow m){
		mainWindow = m;
		daily = d;
		initialize();
		updateStats();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(mainWindow.getFrame().getX() + 100, mainWindow.getFrame().getY() + 100, 326, 223);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("\"" + daily.getDailyDescription() + "\" Statistics");
		
		JLabel lblCompletionPercentage = new JLabel("Completion Percentage:");
		lblCompletionPercentage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCompletionPercentage.setBounds(10, 11, 158, 24);
		frame.getContentPane().add(lblCompletionPercentage);
		
		JLabel lblLongestStreak = new JLabel("Longest Streak:");
		lblLongestStreak.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLongestStreak.setBounds(10, 46, 158, 24);
		frame.getContentPane().add(lblLongestStreak);
		
		JLabel lblCurrentStreak = new JLabel("Current Streak:");
		lblCurrentStreak.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurrentStreak.setBounds(10, 81, 158, 24);
		frame.getContentPane().add(lblCurrentStreak);
		
		completionNumber = new JLabel("13%");
		completionNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		completionNumber.setBounds(178, 12, 77, 22);
		frame.getContentPane().add(completionNumber);
		
		longestNumber = new JLabel("10");
		longestNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		longestNumber.setBounds(178, 46, 77, 22);
		frame.getContentPane().add(longestNumber);
		
		currentNumber = new JLabel("2");
		currentNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		currentNumber.setBounds(178, 81, 77, 22);
		frame.getContentPane().add(currentNumber);
	}
	
	private void updateStats(){
		DecimalFormat df = new DecimalFormat("###.##");
		
		completionNumber.setText(df.format(daily.getCompletionRate()) + "%");
		longestNumber.setText(daily.getLongestStreak() + "");
		currentNumber.setText(daily.getCurrentStreak() + "");
	}
}

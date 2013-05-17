package manager.daily;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainDailyWindow {

	private JFrame frame;
	private JTable dailyTable;
	private static MainDailyWindow thisWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					thisWindow = new MainDailyWindow();
					thisWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainDailyWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 621, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 605, 463);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JScrollPane dailyWindow = new JScrollPane();
		dailyWindow.setBounds(10, 11, 238, 441);
		mainPanel.add(dailyWindow);
		
		dailyTable = new JTable(){
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		dailyTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Sample Daily", Boolean.FALSE},
			},
			new String[] {
				"Daily", "Completed"
			}
		));
		dailyTable.getColumnModel().getColumn(1).setPreferredWidth(67);
		dailyTable.getColumnModel().getColumn(1).setMinWidth(67);
		dailyTable.getColumnModel().getColumn(1).setMaxWidth(67);
		dailyWindow.setViewportView(dailyTable);
		
		JButton btnAddNewDaily = new JButton("Add New Daily");
		btnAddNewDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDailyWindow a = new AddDailyWindow(thisWindow);
			}
		});
		btnAddNewDaily.setBounds(312, 80, 154, 46);
		mainPanel.add(btnAddNewDaily);
		
		
	}
	
	private void addRow(String s, boolean b){
		DefaultTableModel model = (DefaultTableModel)dailyTable.getModel();
		model.addRow(new Object[]{s, new Boolean(b)});
	}
	public void addDaily(String s){
		Daily d = new Daily(s);
		addRow(d.getDailyDescription(), d.getCompletedToday());
	}
	public void enableFrame(boolean b){
		frame.setEnabled(b);
	}
}



package manager.daily;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainDailyWindow {

	private JFrame frame;
	private JTable dailyTable;
	private static MainDailyWindow thisWindow;
	private Manager dailyManager;

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
		thisWindow = this;
		initialize();
		thisWindow.frame.setVisible(true);
	}
	
	public MainDailyWindow(Manager man){
		dailyManager = man;
		thisWindow = this;
		initialize();
		thisWindow.frame.setVisible(true);
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
		dailyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dailyWindow.setViewportView(dailyTable);
		
		JButton btnAddNewDaily = new JButton("Add New Daily");
		btnAddNewDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDailyWindow a = new AddDailyWindow(thisWindow);
			}
		});
		btnAddNewDaily.setBounds(312, 80, 154, 46);
		mainPanel.add(btnAddNewDaily);
		
		JButton btnDeleteDaily = new JButton("Delete Daily");
		btnDeleteDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteDaily();
			}
		});
		btnDeleteDaily.setBounds(312, 137, 154, 46);
		mainPanel.add(btnDeleteDaily);
		
		
	}
	
	private void addRow(String s, boolean b){
		DefaultTableModel model = (DefaultTableModel)dailyTable.getModel();
		model.addRow(new Object[]{s, new Boolean(b)});
	}
	private void deleteRow(){
		if(dailyTable.getSelectedRow() != -1){
			DefaultTableModel model = (DefaultTableModel)dailyTable.getModel();
			model.removeRow(dailyTable.getSelectedRow());
		}
	}
	public void addDaily(String s){
		if(dailyManager.getNumberofDailys() == 0){
			DefaultTableModel model = (DefaultTableModel)dailyTable.getModel();
			model.removeRow(0);
		}
		Daily d = new Daily(s);
		dailyManager.addDaily(d);
		addRow(d.getDailyDescription(), d.getCompletedToday());
	}
	public void deleteDaily(){
		dailyManager.removeDaily(dailyTable.getSelectedRow());
		deleteRow();
	}
	
	public void enableFrame(boolean b){
		frame.setEnabled(b);
	}
}



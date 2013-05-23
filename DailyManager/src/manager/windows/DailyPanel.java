package manager.windows;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import manager.daily.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DailyPanel extends JPanel {

	private JTable dailyTable;
	private static MainWindow thisWindow;
	private DailyManager dailyManager;
	private DailyPanel mainPanel;
	private JTable historyTable;
	private DefaultTableModel defaultHistoryTable;

	public DailyPanel(MainWindow window, DailyManager man) {
		thisWindow = window;
		dailyManager = man;

		mainPanel = this;
		mainPanel.setBounds(0, 0, 605, 463);
		mainPanel.setLayout(null);
		setLayout(null);

		JScrollPane dailyPane = new JScrollPane();
		dailyPane.setBounds(10, 11, 238, 441);
		mainPanel.add(dailyPane);

		dailyTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		dailyTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Daily", "Completed"
			}
		));
		dailyTable.getColumnModel().getColumn(1).setPreferredWidth(67);
		dailyTable.getColumnModel().getColumn(1).setMinWidth(67);
		dailyTable.getColumnModel().getColumn(1).setMaxWidth(67);
		dailyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dailyTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				updateHistory();
			}	
		});
		dailyPane.setViewportView(dailyTable);

		JButton btnAddNewDaily = new JButton("Add New Daily");
		btnAddNewDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDailyWindow a = new AddDailyWindow(thisWindow, mainPanel);
			}
		});
		btnAddNewDaily.setBounds(258, 11, 154, 46);
		mainPanel.add(btnAddNewDaily);

		JButton btnDeleteDaily = new JButton("Delete Daily");
		btnDeleteDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteDaily();
			}
		});
		btnDeleteDaily.setBounds(422, 11, 154, 46);
		mainPanel.add(btnDeleteDaily);

		JButton btnToggleCompleted = new JButton("Toggle Completed");
		btnToggleCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleCompleted();
			}
		});
		btnToggleCompleted.setBounds(258, 68, 154, 46);
		mainPanel.add(btnToggleCompleted);

		JScrollPane historyPane = new JScrollPane();
		historyPane.setBounds(258, 179, 265, 273);
		add(historyPane);

		historyTable = new JTable();
		defaultHistoryTable = new DefaultTableModel(new Object[][] {},
				new String[] { "Date", "Completed" });
		historyTable.setModel(defaultHistoryTable);
		historyPane.setViewportView(historyTable);
		
		JButton btnGetHistory = new JButton("Get History");
		btnGetHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateHistory();
			}
		});
		btnGetHistory.setBounds(476, 148, 89, 23);
		add(btnGetHistory);
		
		JLabel lblDailyHistory = new JLabel("Daily History");
		lblDailyHistory.setBounds(258, 162, 68, 14);
		add(lblDailyHistory);
		
	}

	private void addRow(String s, boolean b) {
		DefaultTableModel model = (DefaultTableModel) dailyTable.getModel();
		model.addRow(new Object[] { s, new Boolean(b) });
	}

	private void deleteRow() {
		if (dailyTable.getSelectedRow() != -1) {
			DefaultTableModel model = (DefaultTableModel) dailyTable.getModel();
			model.removeRow(dailyTable.getSelectedRow());
		}
	}
	private void updateHistory(){
		defaultHistoryTable = new DefaultTableModel(new Object[][] {},
				new String[] { "Date", "Completed" });
		historyTable.setModel(defaultHistoryTable);
		DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
		if(dailyTable.getSelectedRow() != -1){
			ArrayList<Daily> dailys = dailyManager.getDailyList();
			ArrayList<Boolean> historyComp = dailys.get(dailyTable.getSelectedRow()).getHistoryComplete();
			ArrayList<GregorianCalendar> historyDate = dailys.get(dailyTable.getSelectedRow()).getHistoryDate();
			
			for(int i = 0; i < historyComp.size(); i++){
				GregorianCalendar date = historyDate.get(i);
				String sdate = date.get(GregorianCalendar.MONTH) + "/" + date.get(GregorianCalendar.DAY_OF_MONTH) + "/" 
						+ date.get(GregorianCalendar.YEAR);
				model.addRow(new Object[] {sdate, historyComp.get(i)});
			}
		}
	}

	public void addDaily(String s) {
		Daily d = new Daily(s);
		dailyManager.addDaily(d);
		addRow(d.getDailyDescription(), d.getCompletedToday());
	}

	public void deleteDaily() {
		if (dailyTable.getSelectedRow() != -1) {
			dailyManager.removeDaily(dailyTable.getSelectedRow());
			deleteRow();
		}
	}

	public void toggleCompleted() {
		if (dailyTable.getSelectedRow() != -1) {
			Boolean bool = dailyManager.toggleCompleted(dailyTable
					.getSelectedRow());
			DefaultTableModel model = (DefaultTableModel) dailyTable.getModel();
			model.setValueAt(bool, dailyTable.getSelectedRow(), 1);
			updateHistory();
		}
	}
}

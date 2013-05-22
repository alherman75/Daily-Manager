package manager.windows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import manager.daily.*;

public class DailyPanel extends JPanel {

	private JTable dailyTable;
	private static MainWindow thisWindow;
	private Manager dailyManager;
	private DailyPanel mainPanel;
	private JTable historyTable;
	
	public DailyPanel(MainWindow window, Manager man) {
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
		dailyTable.setModel(new DefaultTableModel(new Object[][] { {
				"Sample Daily", Boolean.FALSE }, }, new String[] { "Daily",
				"Completed" }));
		dailyTable.getColumnModel().getColumn(1).setPreferredWidth(67);
		dailyTable.getColumnModel().getColumn(1).setMinWidth(67);
		dailyTable.getColumnModel().getColumn(1).setMaxWidth(67);
		dailyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dailyPane.setViewportView(dailyTable);

		JButton btnAddNewDaily = new JButton("Add New Daily");
		btnAddNewDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDailyWindow a = new AddDailyWindow(thisWindow, mainPanel);
			}
		});
		btnAddNewDaily.setBounds(312, 11, 154, 46);
		mainPanel.add(btnAddNewDaily);

		JButton btnDeleteDaily = new JButton("Delete Daily");
		btnDeleteDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteDaily();
			}
		});
		btnDeleteDaily.setBounds(312, 68, 154, 46);
		mainPanel.add(btnDeleteDaily);

		JButton btnToggleCompleted = new JButton("Toggle Completed");
		btnToggleCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleCompleted();
			}
		});
		btnToggleCompleted.setBounds(312, 125, 154, 46);
		mainPanel.add(btnToggleCompleted);
		
		JScrollPane historyPane = new JScrollPane();
		historyPane.setBounds(258, 179, 265, 273);
		add(historyPane);
		
		historyTable = new JTable();
		historyTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Completed"
			}
		));
		historyPane.setViewportView(historyTable);

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

	public void addDaily(String s) {
		if (dailyManager.getNumberofDailys() == 0) {
			DefaultTableModel model = (DefaultTableModel) dailyTable.getModel();
			model.removeRow(0);
		}
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
		}
	}
}

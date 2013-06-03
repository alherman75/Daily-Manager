package manager.windows;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import manager.todo.ToDoManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ToDoPanel extends JPanel {


	private ToDoPanel mainPanel;
	private static MainWindow mainWindow;
	private ToDoManager toDoManager;
	
	private JTable toDoTable;
	private JTable completedTable;
	
	public ToDoPanel(MainWindow window, ToDoManager man) {
		mainWindow = window;
		toDoManager = man;
		
		mainPanel = this;
		mainPanel.setBounds(00, 00, 700, 525);
		mainPanel.setLayout(null);
		setLayout(null);
		
		JScrollPane scrollPaneToDo = new JScrollPane();
		scrollPaneToDo.setBounds(10, 11, 277, 503);
		add(scrollPaneToDo);
		
		toDoTable = new JTable();
		toDoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"To-Do Items List"
			}
		));
		scrollPaneToDo.setViewportView(toDoTable);
		
		JScrollPane scrollPaneCompleted = new JScrollPane();
		scrollPaneCompleted.setBounds(413, 11, 277, 503);
		add(scrollPaneCompleted);
		
		completedTable = new JTable();
		completedTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Completed Items List"
			}
		));
		scrollPaneCompleted.setViewportView(completedTable);
		
		JButton moveCompleted = new JButton("->");
		moveCompleted.setBounds(297, 80, 106, 32);
		add(moveCompleted);
		
		JButton moveToDo = new JButton("<-");
		moveToDo.setBounds(297, 123, 106, 32);
		add(moveToDo);
		
		JButton addToDoButton = new JButton("Add To Do Item");
		addToDoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddToDoWindow add = new AddToDoWindow();
			}
		});
		addToDoButton.setBounds(297, 166, 106, 32);
		add(addToDoButton);
	}
}

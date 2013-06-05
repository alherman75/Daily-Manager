package manager.windows;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import manager.todo.ToDoItem;
import manager.todo.ToDoManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ToDoPanel extends JPanel {

	private static final int DAILY_CONST = 1;
	private static final int TODO_CONST = 2;

	private ToDoPanel mainPanel;
	private static MainWindow mainWindow;
	private ToDoManager toDoManager;
	
	private JTable toDoTable;
	private JTable completedTable;
	private JFileChooser fileChooser;
	
	public ToDoPanel(MainWindow window, ToDoManager man) {
		mainWindow = window;
		toDoManager = man;
		
		mainPanel = this;
		mainPanel.setBounds(00, 00, 700, 525);
		mainPanel.setLayout(null);
		setLayout(null);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Daily Save File (.dts)", "dts"));
		
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
		toDoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		completedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneCompleted.setViewportView(completedTable);
		
		JButton moveCompleted = new JButton("->");
		moveCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveToComplete();
			}
		});
		moveCompleted.setBounds(297, 80, 106, 32);
		add(moveCompleted);
		
		JButton moveToDo = new JButton("<-");
		moveToDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveToIncomplete();
			}
		});
		moveToDo.setBounds(297, 123, 106, 32);
		add(moveToDo);
		
		JButton addToDoButton = new JButton("Add To Do Item");
		addToDoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddToDoWindow add = new AddToDoWindow(mainWindow, mainPanel);
			}
		});
		addToDoButton.setBounds(297, 166, 106, 32);
		add(addToDoButton);
		
		JButton btnToDailys = new JButton("To Dailys");
		btnToDailys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toDoManager.getManager().switchPanel(DAILY_CONST);
			}
		});
		btnToDailys.setBounds(297, 209, 106, 32);
		add(btnToDailys);
		
		JButton btnSaveList = new JButton("Save List");
		btnSaveList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveList();
			}
		});
		btnSaveList.setBounds(297, 303, 89, 23);
		add(btnSaveList);
		
		JButton btnLoadList = new JButton("Load List");
		btnLoadList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadList();
			}
		});
		btnLoadList.setBounds(297, 337, 89, 23);
		add(btnLoadList);
	}
	
	private void addRow(JTable table, String s){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addRow(new Object[]{s});
	}
	
	private void removeRow(JTable table, int index){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.removeRow(index);
	}
	
	public void addItem(String s){
		toDoManager.addItem(new ToDoItem(s));
		addRow(toDoTable, s);
	}
	
	public void moveToComplete(){
		int index = toDoTable.getSelectedRow();
		if(index != -1){
			ToDoItem item = toDoManager.setCompleted(index);
			removeRow(toDoTable, index);
			addRow(completedTable, item.getDescription());
		}
	}
	
	public void moveToIncomplete(){
		int index = completedTable.getSelectedRow();
		if(index != -1){
			ToDoItem item = toDoManager.setNotCompeleted(index);
			removeRow(completedTable, index);
			addRow(toDoTable, item.getDescription());
		}
	}
	
	private void loadList(){
		int returnVal = fileChooser.showOpenDialog(mainPanel);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			
			
			try{
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				toDoManager.readLists(br);
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private void saveList(){
		int returnVal = fileChooser.showSaveDialog(mainPanel);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			if(!file.getName().endsWith(".dts"))
				file = new File(fileChooser.getSelectedFile() + ".dts");
			
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(toDoManager.toString());
				bw.close();
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

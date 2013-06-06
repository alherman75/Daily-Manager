package manager.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import manager.Manager;

public class MenuPanel extends JPanel {

	private static final int DAILY_CONST = 1;
	private static final int TODO_CONST = 2;
	
	
	private Manager manager;
	private MenuPanel mainPanel;
	private JFileChooser fileChooser;
	private JLabel lblCurrentSaveFile;

	public MenuPanel(Manager man) {
		
		manager = man;
		
		mainPanel = this;
		mainPanel.setBounds(00, 00, 200, 25);
		mainPanel.setLayout(null);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Daily Save File (.dts)", "dts"));
		setLayout(null);
		
		lblCurrentSaveFile = new JLabel("Current Save File");
		lblCurrentSaveFile.setBounds(107, 0, 144, 21);
		add(lblCurrentSaveFile);
		
		
		// Start of Menu stuff

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		add(menuBar);

		JMenu mnFile = new JMenu(" File ");
		menuBar.add(mnFile);

		JMenuItem mntmLoadHistory = new JMenuItem("Load History");
		mntmLoadHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadList();
			}
		});
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.newFile();
			}
		});
		mnFile.add(mntmNew);
		mnFile.add(mntmLoadHistory);

		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveList();
			}
		});
		
		JMenuItem mntmSaveHistory = new JMenuItem("Save History");
		mntmSaveHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCurrentList();
			}
		});
		mnFile.add(mntmSaveHistory);
		mnFile.add(mntmSaveAs);

		JMenu mnView = new JMenu(" View ");
		menuBar.add(mnView);

		JMenuItem mntmDaily = new JMenuItem("Daily");
		mntmDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.getMainWindow().setCurrentPanelNumber(DAILY_CONST);
				manager.switchPanel(DAILY_CONST);
			}
		});
		mnView.add(mntmDaily);

		JMenuItem mntmToDoList = new JMenuItem("To Do List");
		mntmToDoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.getMainWindow().setCurrentPanelNumber(TODO_CONST);
				manager.switchPanel(TODO_CONST);
			}
		});
		mnView.add(mntmToDoList);
	}
	
	private void loadList() {
		int returnVal = fileChooser.showOpenDialog(mainPanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			setCurrSaveFile(file);
			
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				manager.loadFile(br);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveList() {
		int returnVal = fileChooser.showSaveDialog(mainPanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (!file.getName().endsWith(".dts"))
				file = new File(fileChooser.getSelectedFile() + ".dts");
			
			setCurrSaveFile(file);
			
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				manager.saveFile(bw);
				bw.close();
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void saveCurrentList(){
		File file = manager.getCurrentSaveFile();
		if(file != null){
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				manager.saveFile(bw);
				bw.close();
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			saveList();
		}
	}
	
	private void setCurrSaveFile(File file){
		manager.setCurrentSaveFile(file);
		lblCurrentSaveFile.setText(file.getName());
	}
}

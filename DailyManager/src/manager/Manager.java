package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import manager.daily.DailyManager;
import manager.todo.ToDoManager;
import manager.windows.MainWindow;
import manager.windows.MenuPanel;

public class Manager {

	private static final int DAILY_CONST = 1;
	private static final int TODO_CONST = 2;
	
	private ToDoManager toDoManager;
	private DailyManager dailyManager;
	private MainWindow mainWindow;
	private MenuPanel menuPanel;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager m = new Manager();
	}

	public Manager(){
		mainWindow = new MainWindow(this);
		dailyManager = new DailyManager(mainWindow, this);
		toDoManager = new ToDoManager(mainWindow, this);
		menuPanel = new MenuPanel(this);
		mainWindow.setPanel(dailyManager.getDailyPanel());
	}

	public ToDoManager getToDoManager() {
		return toDoManager;
	}

	public void setToDoManager(ToDoManager toDoManager) {
		this.toDoManager = toDoManager;
	}

	public DailyManager getDailyManager() {
		return dailyManager;
	}

	public void setDailyManager(DailyManager dailyManager) {
		this.dailyManager = dailyManager;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
	}
	
	
	//Other Methods
	
	

	public void switchPanel(int panel){
		switch(panel){
		case 1:
			mainWindow.setPanel(dailyManager.getDailyPanel());
			break;
		case 2:
			mainWindow.setPanel(toDoManager.getToDoPanel());
			break;
		}
	}
	
	public void saveFile(BufferedWriter writer){
		try {
			writer.write(dailyManager.toString());
			writer.write(toDoManager.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveFile(){
		
	}
	
	public void loadFile(BufferedReader reader){
		try {
			dailyManager.readDailys(reader);
			toDoManager.readLists(reader);
			
			dailyManager.getDailyPanel().updateDailyTable();
			toDoManager.getToDoPanel().updateTables();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

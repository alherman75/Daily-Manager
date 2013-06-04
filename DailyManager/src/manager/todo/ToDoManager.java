package manager.todo;

import java.util.ArrayList;

import manager.Manager;
import manager.windows.MainWindow;
import manager.windows.ToDoPanel;

public class ToDoManager {

	private ArrayList<ToDoItem> itemList;
	private ArrayList<ToDoItem> completedList;
	private MainWindow mainWindow;
	private ToDoPanel toDoPanel;
	private Manager manager;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ToDoManager man = new ToDoManager();
		MainWindow mainWindow = new MainWindow(man);
	}
	
	public ToDoManager(){
		itemList = new ArrayList<ToDoItem>();
		completedList = new ArrayList<ToDoItem>();
	}
	
	public ToDoManager(MainWindow window){
		itemList = new ArrayList<ToDoItem>();
		completedList = new ArrayList<ToDoItem>();
		mainWindow = window;
		toDoPanel = new ToDoPanel(mainWindow, this);
	}
	
	public ToDoManager(MainWindow window, Manager m){
		itemList = new ArrayList<ToDoItem>();
		completedList = new ArrayList<ToDoItem>();
		mainWindow = window;
		toDoPanel = new ToDoPanel(mainWindow, this);
		manager = m;
	}
	
	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public ArrayList<ToDoItem> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<ToDoItem> itemList) {
		this.itemList = itemList;
	}

	public ArrayList<ToDoItem> getCompletedList() {
		return completedList;
	}

	public void setCompletedList(ArrayList<ToDoItem> completedList) {
		this.completedList = completedList;
	}
	

	public ToDoPanel getToDoPanel() {
		return toDoPanel;
	}

	public void setToDoPanel(ToDoPanel toDoPanel) {
		this.toDoPanel = toDoPanel;
	}

	@Override
	public String toString(){
		String result = "";
		
		result += "To Do List:\n";
		for(ToDoItem i: itemList){
			result += "\t" + i + "\n";
		}
		
		result += "Completed List:\n";
		for(ToDoItem i: completedList){
			result += "\t" + i + "\n";
		}
		
		return result;
	}
	
	// Various Methods
	public void addItem(ToDoItem item){
		itemList.add(item);
	}
	
	public void removeItem(int index){
		itemList.remove(index);
	}
	
	public void removeCompleted(int index){
		completedList.remove(index);
	}
	
	public ToDoItem setCompleted(int index){
		ToDoItem item = itemList.get(index);
		item.setCompleted(true);
		itemList.remove(index);
		completedList.add(item);
		return item;
	}
	
	public ToDoItem setNotCompeleted(int index){
		ToDoItem item = completedList.get(index);
		item.setCompleted(false);
		completedList.remove(index);
		itemList.add(item);
		return item;
	}
}

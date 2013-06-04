package manager.todo;

import java.util.ArrayList;

import manager.windows.MainWindow;

public class ToDoManager {

	private ArrayList<ToDoItem> itemList;
	private ArrayList<ToDoItem> completedList;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ToDoManager man = new ToDoManager();
		MainWindow mainWindow = new MainWindow(man);
	}
	
	public ToDoManager(){
		itemList = new ArrayList<ToDoItem>();
		completedList = new ArrayList<ToDoItem>();
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

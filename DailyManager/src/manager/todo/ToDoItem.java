package manager.todo;

import java.io.BufferedReader;
import java.io.IOException;

public class ToDoItem {

	private String description;
	private boolean completed;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ToDoItem item = new ToDoItem("Finish this class.");
		System.out.println(item);
		item.toggleCompleted();
		System.out.println(item);
	}

	public ToDoItem(){
		description = "";
		completed = false;
	}
	
	public ToDoItem(String des){
		description = des;
		completed = false;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public void toggleCompleted(){
		completed = !completed;
	}

	@Override
	public String toString(){
		String result = description + "\n";
		if(completed)
			result += "1";
		else
			result += "0";
		return result;
	}
	
	public void readToDo(BufferedReader reader){
		try {
			String line = reader.readLine();
			description = line;
			line = reader.readLine();
			if(line.equals("1"))
				completed = true;
			else if(line.equals("0"))
				completed = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

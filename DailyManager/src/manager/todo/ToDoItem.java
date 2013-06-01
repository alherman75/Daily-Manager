package manager.todo;

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
		String result = description;
		result += " Completed: " + completed;
		return result;
	}
}

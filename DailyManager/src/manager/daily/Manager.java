package manager.daily;

import java.util.*;

import manager.windows.*;

public class Manager {

	private int numberofDailys;
	private ArrayList<Daily> dailyList;
	private static Manager thisManager;
	private static MainWindow mainWindow;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thisManager = new Manager();
		mainWindow = new MainWindow(thisManager);
	}

	public ArrayList<Daily> getDailyList() {
		return dailyList;
	}

	public void setDailyList(ArrayList<Daily> dailyList) {
		this.dailyList = dailyList;
	}

	public int getNumberofDailys() {
		return numberofDailys;
	}
	
	// Not Getters and Setters

	public Manager() {
		numberofDailys = 0;
		dailyList = new ArrayList<Daily>();
	}

	public void addDaily(Daily d) {
		dailyList.add(d);
		numberofDailys++;
		printList();
	}

	public void removeDaily(Daily d) {
		int index = searchList(d);
		if (index != -1) {
			dailyList.remove(index);
			numberofDailys--;
		}
	}

	public void removeDaily(int index) {
		if (index >= 0) {
			dailyList.remove(index);
			numberofDailys--;
			printList();
		}
	}
	
	public Boolean toggleCompleted(int index){
		if (index >= 0) {
			Boolean temp = dailyList.get(index).toggleCompletedToday();
			printList();
			return temp;
		}
		
		return false;
	}

	private int searchList(Daily d) {
		for (int i = 0; i < dailyList.size(); i++) {
			Daily trial = dailyList.get(i);
			if (d.equals(trial)) {
				return i;
			}
		}
		return -1;
	}

	private void printList() {
		for (int i = 0; i < dailyList.size(); i++) {
			System.out.println(i + ": "
					+ dailyList.get(i).getDailyDescription() + " "
					+ dailyList.get(i).getId() + " Completed: " + dailyList.get(i).getCompletedToday());
		}
		System.out.println();
	}

}

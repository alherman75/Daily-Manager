package manager.daily;

import java.util.*;

public class Manager {

	private int numberofDailys;
	private ArrayList<Daily> dailyList;
	private static Manager thisManager;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thisManager = new Manager();
		MainDailyWindow window = new MainDailyWindow(thisManager);
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
					+ dailyList.get(i).getId());
		}
		System.out.println();
	}

}

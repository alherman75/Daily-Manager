package manager.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import manager.Manager;
import manager.windows.*;

public class DailyManager {

	private int numberofDailys;
	private ArrayList<Daily> dailyList;
	private static DailyManager thisManager;
	private static MainWindow mainWindow;
	private DailyPanel dailyPanel;
	private Manager manager;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thisManager = new DailyManager();
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

	public DailyPanel getDailyPanel() {
		return dailyPanel;
	}

	public void setDailyPanel(DailyPanel dailyPanel) {
		this.dailyPanel = dailyPanel;
	}

	public void setNumberofDailys(int numberofDailys) {
		this.numberofDailys = numberofDailys;
	}

	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public DailyManager() {
		numberofDailys = 0;
		dailyList = new ArrayList<Daily>();
		thisManager = this;
		//dailyPanel = new DailyPanel();
	}
	public DailyManager(MainWindow window){
		numberofDailys = 0;
		dailyList = new ArrayList<Daily>();
		thisManager = this;
		mainWindow = window;
		dailyPanel = new DailyPanel(mainWindow, thisManager);
	}
	
	public DailyManager(MainWindow window, Manager m){
		numberofDailys = 0;
		dailyList = new ArrayList<Daily>();
		thisManager = this;
		mainWindow = window;
		manager = m;
		dailyPanel = new DailyPanel(mainWindow, thisManager);
	}

	public void addDaily(Daily d) {
		dailyList.add(d);
		numberofDailys++;
		//printList();
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
			//printList();
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
//		for (int i = 0; i < dailyList.size(); i++) {
//			System.out.println(i + ": "
//					+ dailyList.get(i).getDailyDescription() + " "
//					+ dailyList.get(i).getId() + " Completed: " + dailyList.get(i).getCompletedToday());
//		}
//		System.out.println();
		System.out.println(toString());
	}
	
	@Override
	public String toString(){
		String result = "";
		DecimalFormat format = new DecimalFormat("000");
		result += "daily " + format.format(numberofDailys) + "\n";
		
		for(int i = 0; i < dailyList.size(); i++){
			result += dailyList.get(i).toString();
			if(i + 1 < dailyList.size())
				result += "\n";
		}
		
		return result;
	}
	
	//needs testing
	public void readDailys(BufferedReader reader) throws IOException{
		numberofDailys = 0;
		dailyList = new ArrayList<Daily>();
		String line;
		line = reader.readLine();
		if(line.substring(0, 5).equals("daily")){
			int numDailys;
			numDailys = Integer.parseInt(line.substring(6,9));
			for(int i = 0; i < numDailys; i++){
				Daily d = new Daily();
				d.readDaily(reader);
				addDaily(d);
			}
		}
		System.out.println(this);
	}

}

package manager.daily;

import java.util.*;

public class Manager {

	private int numberofDailys;
	private ArrayList<Daily> dailyList;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainDailyWindow window = new MainDailyWindow();
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
	
	public void addDaily(Daily d){
		dailyList.add(d);
	}
	
	//Doesn't necessarily Work
	public void removeDaily(Daily d){
		int index = searchList(d);
		if(index != -1){
			dailyList.remove(index);
		}
	}
	
	private int searchList(Daily d){
		for(int i = 0; i< dailyList.size(); i++){
			Daily trial = dailyList.get(i);
			if(d.equals(trial)){
				return i;
			}
		}
		return -1;
	}

}

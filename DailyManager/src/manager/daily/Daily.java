package manager.daily;

import java.util.*;

public class Daily {

	/**
	 * @param args
	 */
	
	private static int idCount = 0;
	private int id;
	private String dailyDescription;
	private ArrayList<Boolean> historyComplete;
	private ArrayList<GregorianCalendar> historyDate;
	
	public static void main(String Args[]){
		
	}
	
	public String getDailyDescription() {
		return dailyDescription;
	}
	public void setDailyDescription(String dailyDescription) {
		this.dailyDescription = dailyDescription;
	}
	public ArrayList<Boolean> getHistoryComplete() {
		return historyComplete;
	}
	public void setHistoryComplete(ArrayList<Boolean> historyComplete) {
		this.historyComplete = historyComplete;
	}
	public ArrayList<GregorianCalendar> getHistoryDate() {
		return historyDate;
	}
	public void setHistoryDate(ArrayList<GregorianCalendar> historyDate) {
		this.historyDate = historyDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Daily(){
		idCount++;
		id = idCount;
		dailyDescription = "";
		historyComplete = new ArrayList<Boolean>();
		historyDate = new ArrayList<GregorianCalendar>();
	}
	
	
	
}

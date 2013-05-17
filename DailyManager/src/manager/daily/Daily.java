package manager.daily;

import java.util.*;

public class Daily {

	/**
	 * @param args
	 */
	
	private static int idCount = 0;
	private int id;
	private String dailyDescription;
	private Boolean completedToday;
	private ArrayList<Boolean> historyComplete;
	private ArrayList<GregorianCalendar> historyDate;
	
	public static void main(String Args[]){
		Daily d = new Daily();
		System.out.println(d.getHistoryDate().get(0).get(Calendar.YEAR));
		d.getHistoryDate().get(0).get(Calendar.DAY_OF_MONTH);
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
	public Boolean getCompletedToday() {
		return completedToday;
	}

	public void setCompletedToday(Boolean completedToday) {
		this.completedToday = completedToday;
	}

	//Default Constructor
	public Daily(){
		idCount++;
		id = idCount;
		dailyDescription = "";
		completedToday = false;
		historyComplete = new ArrayList<Boolean>();
		historyDate = new ArrayList<GregorianCalendar>();
		
		historyDate.add(new GregorianCalendar());
		historyComplete.add(new Boolean(false));
	}
	
	public Daily(String description){
		idCount++;
		id = idCount;
		dailyDescription = description;
		completedToday = false;
		historyComplete = new ArrayList<Boolean>();
		historyDate = new ArrayList<GregorianCalendar>();
		
		historyDate.add(new GregorianCalendar());
		historyComplete.add(new Boolean(false));
	}
	
	private void addHistory(GregorianCalendar cal, boolean completed){
		historyDate.add(cal);
		historyComplete.add(new Boolean(completed));
	}
	
	
	
}

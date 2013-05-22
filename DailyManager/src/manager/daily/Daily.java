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

	public static void main(String Args[]) {
		Daily d = new Daily("My string");
		d.getHistoryDate().get(0).get(Calendar.DAY_OF_MONTH);
		System.out.println(d.toString());
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

	public Boolean toggleCompletedToday() {
		completedToday = !completedToday;
		return completedToday;
	}

	// Default Constructor
	public Daily() {
		idCount++;
		id = idCount;
		dailyDescription = "";
		completedToday = false;
		historyComplete = new ArrayList<Boolean>();
		historyDate = new ArrayList<GregorianCalendar>();

		historyDate.add(new GregorianCalendar());
		historyComplete.add(new Boolean(false));
	}

	public Daily(String description) {
		idCount++;
		id = idCount;
		dailyDescription = description;
		completedToday = false;
		historyComplete = new ArrayList<Boolean>();
		historyDate = new ArrayList<GregorianCalendar>();

		historyDate.add(new GregorianCalendar());
		historyComplete.add(new Boolean(false));

	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Daily) {
			Daily second = (Daily) obj;
			if (second.getDailyDescription() == this.getDailyDescription()
					&& second.getId() == this.getId()) {
				result = true;
			}
		}
		return result;
	}

	public void addHistory(GregorianCalendar cal, boolean completed) {
		historyDate.add(cal);
		historyComplete.add(new Boolean(completed));
	}

	public void resetHistory() {
		historyDate = new ArrayList<GregorianCalendar>();
		historyComplete = new ArrayList<Boolean>();
	}

	@Override
	public String toString() {
		String result = "";
		result += dailyDescription;
		result += " /h";
		for (int i = 0; i < historyDate.size(); i++) {
			GregorianCalendar date = historyDate.get(i);
			result += date.get(GregorianCalendar.DAY_OF_YEAR) + "."
					+ date.get(GregorianCalendar.MONTH) + "."
					+ date.get(GregorianCalendar.DAY_OF_MONTH) + " ";
			
			boolean complete = historyComplete.get(i);
			if(complete == true){
				result += 1 + " ";
			} else if(complete == false){
				result += 0 + " ";
			}
		}

		return result;
	}

}

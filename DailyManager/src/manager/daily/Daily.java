package manager.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
		historyComplete.set(historyComplete.size() - 1, completedToday);
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

		addToday();
	}

	public Daily(String description) {
		idCount++;
		id = idCount;
		dailyDescription = description;
		completedToday = false;
		historyComplete = new ArrayList<Boolean>();
		historyDate = new ArrayList<GregorianCalendar>();

		addToday();
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
		// /h indicates start of history
		result += "\n";
		for (int i = 0; i < historyDate.size(); i++) {
			GregorianCalendar date = historyDate.get(i);
			result += new SimpleDateFormat("MM/dd/yyyy").format(date.getTime())
					+ " ";
			boolean complete = historyComplete.get(i);
			if (complete == true) {
				result += 1;
			} else if (complete == false) {
				result += 0;
			}
			result += " ";
		}

		return result;
	}

	private void addToday(){
		historyDate.add(new GregorianCalendar());
		historyComplete.add(new Boolean(false));
	}
	
	// needs testing
	public void readDaily(BufferedReader reader) throws IOException {
		historyComplete = new ArrayList<Boolean>();
		historyDate = new ArrayList<GregorianCalendar>();
		try{
		String line = null;
		line = reader.readLine();
		dailyDescription = line;
		line = reader.readLine();
		for (int i = 0; i < line.length(); i = i+13) {
			int month = Integer.parseInt(line.substring(i, i + 2));
			int day = Integer.parseInt(line.substring(i + 3, i + 5));
			int year = Integer.parseInt(line.substring(i + 6, i + 10));
			String complete = line.substring(i + 11, i + 12);
			boolean comp;
			if (complete.equals("1"))
				comp = true;
			else
				comp = false;
			addHistory(new GregorianCalendar(year, month-1, day), comp);
			
		}
		//See if most Recent is today
		String mostRecent = new SimpleDateFormat("MM/dd/yyyy").format(
				historyDate.get(historyDate.size()-1).getTime());
		String today = new SimpleDateFormat("MM/dd/yyyy").format(new GregorianCalendar().getTime());
		//System.out.println("Compare: " + mostRecent + " " + today);
		if(mostRecent.equals(today)){
			completedToday = historyComplete.get(historyComplete.size()-1);
		}
		else{
			addToday();
		}
		} catch(IOException e){
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}

}

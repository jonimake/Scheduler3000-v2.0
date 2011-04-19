package KaiSei;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Team TA's
 */



public class Event implements Comparable<Event> {

	/*
	 * Muutettu valid_timet privaateiksi ja getterit tehty
	 */
	private static final String[] VALID_START_TIMES = {"08", "10", "12", "14", "16", "18" };
	private static final String[] VALID_END_TIMES = {"10", "12", "14", "16", "18", "20" };
	private String startTime;
	private String endTime;
	private String location;
	private String title;
	private GregorianCalendar end;
	private GregorianCalendar start;
	
	public Event(String startTime, String endTime){
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		
	}
	
	public Event(String startTime, String endTime, String title){
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setTitle(title);
		this.askTime();
	}
	
	public Event(String startTime, String endTime, String title, String location){
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setTitle(title);
		this.setLocation(location);
		this.askTime();
	}
	
	public String getLocation() {
		return location;
	} 
	
	private void askTime(){
		//System.out.println();
		System.out.println("Anna tapahtumasarjan alkuaika (dd.mm.yyyy)");
		this.setStart(InputUtils.askDate());
		System.out.println("Anna tapahtumasarjan loppuaika (dd.mm.yyyy)");
		this.setEnd(InputUtils.askDate());
		
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if (checkIfValid(startTime, getValidStartTimes())){		
			this.startTime = startTime;
		}
		else {
			throw new IllegalArgumentException("Start time must be one of the following: " + getAllValidValues(getValidStartTimes()));
		}
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		if (checkIfValid(endTime, getValidEndTimes())){
			this.endTime = endTime;
		}
		else {
			throw new IllegalArgumentException("End time must be one of the following: "+ getAllValidValues(getValidEndTimes()));
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// PRIVATES
	
	private String getAllValidValues(String[] listOfValids){
		String valids = "";
		for (String v : listOfValids){
			valids += "\""+v+"\", ";
		}
		return valids;
	}
		
	/**
	 * @param value 
	 * @param allValids - Array of Strings that contains all valid Strings
	 * @return true if value is valid, ie. 
	 * @return false if startTime didn't match a valid start time.
	 */
	public static boolean checkIfValid(String value, String[] allValids){
		for (String valid : allValids){
			if (value.equalsIgnoreCase(valid)){
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the validStartTimes
	 */
	public static String[] getValidStartTimes()
	{
		return VALID_START_TIMES;
	}

	/**
	 * @return the validEndTimes
	 */
	public static String[] getValidEndTimes()
	{
		return VALID_END_TIMES;
	}

	@Override
	public int compareTo(Event arg0)
	{
		return (this.start.compareTo(arg0.end));//this.startTime.compareTo(((Event)arg0).startTime));
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(GregorianCalendar start)
	{
		this.start = start;
	}

	/**
	 * @return the start
	 */
	public GregorianCalendar getStart()
	{
		return start;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(GregorianCalendar end)
	{
		this.end = end;
	}

	/**
	 * @return the end
	 */
	public GregorianCalendar getEnd()
	{
		return end;
	}
}

package tests;

/**
 * @author Team TA's
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import KaiSei.*;
import KaiSei.Weekday.Day;


public class MockSchedule extends Schedule {

	// CONSTRUCTORS
	String period;
	Map<Weekday.Day, List<Event>> schedule;

	public MockSchedule(List<Weekday.Day> week){
		this.schedule = new HashMap<Day, List<Event>>();
		for (Weekday.Day d : week){
			this.schedule.put(d, new ArrayList<Event>());
		}
    }
    
    // OTHER
	
	@Override
	public void addEvent(Day eventDay, Event event) {
		schedule.get(eventDay).add(event);
	}

	@Override
	public Map<Weekday.Day, List<Event>> getSchedule() {
		return this.schedule;
	}
/*
	@Override
	public void setPeriod(String period) {
		this.period = period;
	}
*/
	@Override
	public void setSchedule(List<Day> newSchedule) {
		this.schedule = new HashMap<Day, List<Event>>();
		for (Day d : newSchedule){
			this.schedule.put(d, new ArrayList<Event>());
		}
	}

	@Override
	public void setSchedule(Map<Day, List<Event>> newSchedule) {
		this.schedule = newSchedule;
	}

	@Override
	public void setSchedule(Schedule newSchedule) {
		this.schedule = newSchedule.getSchedule();
	}

}

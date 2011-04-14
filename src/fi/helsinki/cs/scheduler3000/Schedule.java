package fi.helsinki.cs.scheduler3000;

/**
 * @author Team TA's
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fi.helsinki.cs.scheduler3000.Weekday.Day;

public class Schedule implements Serializable {
	
    private Map<Day, List<Event>> schedule;

    // this constructor is solely for mock objects to use, hence protected
    protected Schedule(){ 
    	//this.period = null;
    	this.schedule = null;
    }
    
    public Schedule(List<Day> week){
        this.setSchedule(week);
        //this.period = null;
    }
    
    public Schedule(ArrayList<Day> week, String period){
    	this.setSchedule(week);
    	//this.period = period;
    }

    // GETTERS AND SETTERS
    
 
    public void setSchedule(List<Day> newSchedule){
    	if (this.schedule == null){
    		this.schedule = new HashMap<Day, List<Event>>();
    	}
    	
    	// build schedule from given week, initialize empty event-arrays
        for (Day day : newSchedule){
        	this.schedule.put(day, new ArrayList<Event>());
        }
    }
    
    public void setSchedule(Map<Day, List<Event>> newSchedule){
    	this.schedule = newSchedule;
    }
    
    public void setSchedule(Schedule newSchedule) {
		this.schedule = newSchedule.getSchedule();
		//this.period = newSchedule.getPeriod();
	}

    public Map<Day, List<Event>> getSchedule(){
		return this.schedule; 
	}
/*
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
*/
    // OTHER
	public void addEvent(Day eventDay, Event event) {
		if (!this.schedule.containsKey(eventDay)){ // if adding an event to non-existent date
			throw new IllegalArgumentException("No such date in the schedule");
		}
		this.schedule.get(eventDay).add(event);
	}

	public Set<Day> getDaysInSchedule()
    {
		return getSchedule().keySet();
    }


}

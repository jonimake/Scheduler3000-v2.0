package fi.helsinki.cs.scheduler3000;

/**
 * @author Team TA's
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


public class FullReport extends Report {
	
	public FullReport(Schedule schedule, Map<String, Object> options) {
		this.options = options;
		this.schedule = schedule;
	}
	
	@Override
	public String toString(){
		ArrayList<Weekday.Day> eventlist = new ArrayList<Weekday.Day>(schedule.getSchedule().keySet());
		Collections.sort(eventlist);
		String res = "";
		
		for (Weekday.Day day : eventlist){
			res += day + ":\n";
			res += "----\n";
			for (Event event : schedule.getSchedule().get(day)){
				res += event.getTitle();
				res += "\nat " + event.getStartTime() + "-" + event.getEndTime();
				res += "\nin " + event.getLocation();
			}
			res += "\n\n";
		}
		
		return res;
	}

}

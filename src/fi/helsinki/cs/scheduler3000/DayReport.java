package fi.helsinki.cs.scheduler3000;

/**
 * @author Team TA's
 */

import java.util.Collections;
import java.util.List;
import java.util.Map;

import fi.helsinki.cs.scheduler3000.Weekday.Day;

public class DayReport extends Report {

	public DayReport(Schedule schedule, Map<String, Object> options) {
		this.schedule = schedule;
		setOptions(options);
	}
	
	@Override
	public void askOptionsFromUser()
	{
		Day day = InputUtils.askWeekday("Which day you want to see your schedule for?",
				this.schedule.getDaysInSchedule());
		options.put("day", day);
	}
	
	@Override
	public String toString() {
		if (this.options.containsKey("day")){
			String res = "";
			Weekday.Day weekday = (Day)this.options.get("day");
			List<Event> events = this.schedule.getSchedule().get(weekday);
			
			if (events == null) {
				return null;
			}
			Collections.sort(events);
			res += Weekday.dayToString(weekday) + ":\n------\n";
			
			for (Event e : events){
				if(e.getTitle().equals("")){
					res += "<no title for this event>";
				}
				else{
					res += e.getTitle();	
				}
				
				//event must always have start time and end time
				res += "\nat " + e.getStartTime() + " to " + e.getEndTime(); 
				
				if (e.getLocation().equals("")){
					res += "\n<no location specified>";
				}
				else {
					res += "\nin " + e.getLocation();
				}
				res += "\n\n";
			}
			return res;
		}

		return null;
	}
	
}

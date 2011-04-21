package KaiSei;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Map.Entry;

import KaiSei.Weekday.Day;

public class RollingReport extends Report
{
	
	public RollingReport(Schedule schedule, Map<String, Object> options) {
		this.schedule = schedule;
		setOptions(options);
	}
	
	private ArrayList<Event> checkEvents() {
		GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar then = (GregorianCalendar) GregorianCalendar.getInstance();
		then.roll(then.MONTH, true);
		ArrayList<Event> events = new ArrayList<Event>(); 
		
		for(List<Event> le : schedule.getSchedule().values()) {
			events.addAll(le);
		}
		
		
		return events;
	}

	@Override
	public String toString()
	{
		Map<String, String> eventDayMap = new HashMap<String, String>();
		GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar then = (GregorianCalendar) GregorianCalendar.getInstance();
		int n = InputUtils.askInt("Kuinka monen viikon tapahtumat haluat printata?", 1, 52);
		then.roll(Calendar.WEEK_OF_YEAR, n);
		
		String str = "";
		ArrayList<Event> events = new ArrayList<Event>();
		
		for(Entry<Day, List<Event>> ent: schedule.getSchedule().entrySet()) {
			for(Event e : ent.getValue()) {
				eventDayMap.put(e.getTitle(), ent.getKey().toString());
				events.add(e);
			}
		}
		
		/*
		for(List<Event> le : schedule.getSchedule().values()) {
			//mappi.put(le., value)
			events.addAll(le);
		}
		*/
		Collections.sort(events);
		do {
			str += "Viikko: " + now.get(Calendar.WEEK_OF_YEAR) +"\n";
			for(Event e : events) {
				if(e == null)
					System.out.println("e on null");
				if(e.getEnd() == null)
					System.out.println("Getend on null");
				if(now == null)
					System.out.println("now on null");
				if(e.getEnd().after(now)) {
					str += "\t" + eventDayMap.get(e.getTitle()) + " " + 
						e.getTitle() + " " + e.getStartTime() + " - " + 
						e.getEndTime() + " " + e.getLocation();
					str += "\n";
				}
			}
			now.roll(Calendar.WEEK_OF_YEAR, true);
		}while(now.before(then));
		
		
		//for(Event e : events)
		//	str += e.getTitle();
		return str;
	}

}

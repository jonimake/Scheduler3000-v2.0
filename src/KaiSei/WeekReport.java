package KaiSei;


/**
 * @author Team TA's
 */


import java.util.List;
import java.util.Map;

import KaiSei.Weekday.Day;


public class WeekReport extends Report {

	public WeekReport(Schedule schedule, Map<String, Object> options) {
		this.schedule = schedule;
		setOptions(options);
	}
	@Override
	public void askOptionsFromUser()
	{
		List<Weekday.Day> days = InputUtils.askManyWeekdays("Which days do you wish to include in this report", schedule.getDaysInSchedule());
		options.put("days", days);
	}

	@Override
	public String toString() {
		
		if (this.options.containsKey("days")){
			List<Weekday.Day> days = (List<Day>)this.options.get("days");			
			String[][] res = new String[days.size() + 1][7]; // +1 for header row

			res[0][0] = "\t";
			
			for (int i = 1, j = 0; j < Event.getValidStartTimes().length; i++, j++){
				res[0][i] = Event.getValidStartTimes()[j] + "\t";
			}	

			int i = 1;
			for (Day day : days){
				res[i][0] = day.toString() + "\t";
				i++;
			}
			
			i = 1;
			for (Day d : days){		
				List<Event> events = this.schedule.getSchedule().get(d); 
				
				if (events == null){
					return null;
				}
				else if (events.size() == 0){
					for (int x = 1; x < 7; x++) {
						res[i][x] = "\t";
					}
				}
				
				for (Event event : events){
					String entry = "\t"; // if event is null
						
					if (event.getLocation() != null) { 
					  entry = event.getLocation()+"\t";
					}
					
					if (event.getStartTime().equals("08"))     { res[i][1] = entry; } 
					else if(event.getStartTime().equals("10")) { res[i][2] = entry; } 
					else if(event.getStartTime().equals("12")) { res[i][3] = entry;	} 
					else if(event.getStartTime().equals("14")) { res[i][4] = entry; } 
					else if(event.getStartTime().equals("16")) { res[i][5] = entry; } 
					else if(event.getStartTime().equals("18")) { res[i][6] = entry; }
				
					// fill up with empties
					for (int x = 1; x < 7; x++) {
						if (res[i][x] == null){
							res[i][x] = "\t";
						}
					}
					
				}
				i++;
			}
						
			String response = "";
			
			for (int j = 0; j < res.length; j++){
				for (int k = 0; k < res[0].length; k++){
					response += res[j][k];
				}
				response += "\n";
			}
			
			return response;
		}
		return null;
	}
	
}

package fi.helsinki.cs.scheduler3000;

import java.util.ArrayList;
import java.util.Collections;

public class CSVOutput extends FileOutput {
	
	String filename;
	Schedule sched;
	
	public CSVOutput(Schedule sched) {
		this.sched = sched;
	}

	@Override
	public String getContents() {
		ArrayList<Weekday.Day> eventlist = new ArrayList<Weekday.Day>(sched.getSchedule().keySet());
		Collections.sort(eventlist);
		String res = "";
		
		for (Weekday.Day day : eventlist){
			
			res += day + "\n";
			if(sched.getSchedule().get(day).isEmpty()) {
				res += "Tälle päivälle ei ole mitään tilaisuuksia.\n";
				continue;
			}
			res += "Nimi, Aloitusaika, Lopetusaika, Paikka\n";
			
			for (Event event : sched.getSchedule().get(day)){
				res += event.getTitle() + ",";
				res += event.getStartTime() + ",";
				res += event.getEndTime() + ",";
				res += event.getLocation();
				res += "\n";
			}
			res += "\n";
		}
		
		return res;
	}
}

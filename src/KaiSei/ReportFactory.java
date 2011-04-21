package KaiSei;

/**
 * @author Team TA's
 */


import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReportFactory {

	private static Map<String, Class> reports;
	static {
		reports = new HashMap<String, Class>();
		reports.put("Full", FullReport.class);
		reports.put("Day", DayReport.class);
		reports.put("Week", WeekReport.class);
		reports.put("Rolling", RollingReport.class);
	}
	
	public static Report makeReport(String reportName, Schedule schedule, HashMap<String, Object> options){
		try
        {
	        Constructor ctor = reports.get(reportName).getConstructor(Schedule.class, Map.class);
	        return (Report)ctor.newInstance(schedule, options);
        } catch (Exception e)
        {
        	e.printStackTrace();
        	return null;
        }
	}
	public static List<String> getReportTypes()
	{
		return new ArrayList<String>(reports.keySet());
	}
}

package tests;

import java.util.ArrayList;
import java.util.HashMap;

import fi.helsinki.cs.scheduler3000.Event;
import fi.helsinki.cs.scheduler3000.FileOutput;
import fi.helsinki.cs.scheduler3000.FileOutputFactory;
import fi.helsinki.cs.scheduler3000.Weekday;
import fi.helsinki.cs.scheduler3000.FileOutputFactory.FileType;

import junit.framework.TestCase;

public class FileOutputFactoryTest extends TestCase {
	
	MockSchedule sched;
	HashMap<String, Object> options;
	final String STARTTIME = "08", ENDTIME = "12", TITLE = "title", LOCATION = "location";

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		sched = null;
	}

	public void testMakeFileOutputAsCSV() {
		ArrayList<Weekday.Day> weekdays = new ArrayList<Weekday.Day>();
		weekdays.add(Weekday.Day.MON);
		weekdays.add(Weekday.Day.THU);
		sched = new MockSchedule(weekdays);
		
		sched.addEvent(Weekday.Day.MON, new Event(STARTTIME, ENDTIME, TITLE, LOCATION));
		
		FileOutput fo = FileOutputFactory.makeFileOutput(FileType.CSV, sched);
		String contents = fo.getContents();
		
		assertTrue(contents.contains(STARTTIME));
		assertTrue(contents.contains(ENDTIME));
		assertTrue(contents.contains(TITLE));
		assertTrue(contents.contains(LOCATION));
		assertTrue(contents.contains(Weekday.Day.MON.toString()));
		assertTrue(contents.contains(Weekday.Day.THU.toString()));
		
	} 

}

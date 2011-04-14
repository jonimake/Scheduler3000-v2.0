package fi.helsinki.cs.scheduler3000;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Team TA's
 */

public class Weekday {

	public static enum Day { MON, TUE, WED, THU, FRI, SAT, SUN };	
	public static enum LongName { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday };

	public static Day[] WEEKDAYS = Day.values();
	public static Set<Day> WEEKDAYS_SET = new HashSet<Weekday.Day>(Arrays.asList(Weekday.WEEKDAYS));
	public static String dayToString(Day day) {
		return LongName.values()[dayToInt(day) - 1].toString();
	}
	
	public static int dayToInt(Day day)
	{
		return day.ordinal() + 1;
	}
	
	public static Day intToDay(int d)
	{
		return Day.values()[d - 1];
	}
}

package fi.helsinki.cs.scheduler3000;

/**
 * @author Team TA's
 */

public class Weekday {

	public static enum Day { MON, TUE, WED, THU, FRI, SAT, SUN };	
	public static enum LongName { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday };
	
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

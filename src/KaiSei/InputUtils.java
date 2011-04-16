package KaiSei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import KaiSei.Weekday.Day;


public class InputUtils
{
	private static Scanner input = new Scanner(System.in);
	public static String PROMPT = ": ";
	
	public static String showManyDates(List<Day> days, boolean showNumbers)
	{
		String s = "";
		for (Day d : days)
		{
			if(showNumbers)
				s += "" + Weekday.dayToInt(d) + ": ";
			s += Weekday.dayToString(d) + " ";
		}
		return s;
	}
	public static String askTime(String query, String[] t)
	{
		String time;
		do
		{
			System.out.println(query + PROMPT);
			time = input.nextLine();
		} while(!Event.checkIfValid(time, t));
		return time;
	}

	static boolean checkDate(String in)
	{
		Integer day = null;
		// try-catch makes sure that input is numeric
		try
		{
			day = Integer.parseInt(in);
			// check if day is indeed a valid number
			if (day >= 1 && day <= 7)
			{
				return true; // day ok, exit!
			}

			System.out.println("Sorry, but \"" + day
					+ "\" is not a valid number for date");

		} catch (NumberFormatException e)
		{
			System.out.println("Sorry, cannot parse \"" + in + "\"");
		}

		return false;

	}
	static boolean checkDate(String in, Set<Day> days) {
    	// check if the date is valid at all
    	if (!checkDate(in)){
    		return false;
    	}
    	// check if specified date is in the schedule
    	return days.contains(Weekday.intToDay(Integer.parseInt(in)));
    }

	public static int askInt(String query, int min, int max)
    {
		int number = 0;
		do
		{
			System.out.print(query + PROMPT);
			boolean success = true;
			String line = input.nextLine();
			try
			{
				number = Integer.parseInt(line);
			}
			catch(NumberFormatException nfe)
			{
				success = false;
			}
			if(!success || number < min || number > max)
			{
				System.out.println("Invalid input, enter a number between " + min + " and " + max);
				continue;
			}
		} while(false);
		return number;
    }
	public static Weekday.Day askWeekday(String query, Set<Weekday.Day> validDays)
	{
		String in = null;
		ArrayList<Day> validDaysArray = new ArrayList<Day>(validDays);
		Collections.sort(validDaysArray);
		do{
			System.out.println(query);
			System.out.println("Choices are: " + showManyDates(validDaysArray, true));
			System.out.println("Enter a weekday" + PROMPT);
			in = input.nextLine();

			if (!InputUtils.checkDate(in, validDays))
				System.out.println("Not a valid weekday");
			else
				break;
		} while(true);

		return Weekday.intToDay(Integer.parseInt(in));
	}
	public static List<Day> askManyWeekdays(String query, Set<Day> validDays)
	{
		List<Day> days = new ArrayList<Day>();
		ArrayList<Day> validDaysArray = new ArrayList<Day>(validDays);
		Collections.sort(validDaysArray);
		
		System.out.println(query);
		System.out.println("Valid choices: " + showManyDates(validDaysArray, true));
		while (true){
			if(days.size() != 0)
				System.out.println("You have chosen: " + showManyDates(days, false));
			System.out.println("Enter a weekday (empty line to end)" + PROMPT);

			String in = input.nextLine();
			if (in.trim().equals(""))
				break;

			else if (!checkDate(in, validDays)){
				System.out.println("Unvalid date");
			}
			else {
				days.add(Weekday.intToDay(Integer.parseInt(in)));
			}
		}
		return days;
	}
	public static String askLine(String query)
	{
		System.out.println(query + PROMPT);
		return input.nextLine();
	}
}

package fi.helsinki.cs.scheduler3000;

import java.util.HashSet;
import java.util.Scanner;

import fi.helsinki.cs.scheduler3000.Weekday.Day;

public class outputUtils
{
	private static Scanner input = new Scanner(System.in);

	public static void printDates(Schedule schedule)
	{
		System.out.print("Dates are: ");
		for (Day d : schedule.getSchedule().keySet())
		{
			System.out.print(Weekday.dayToInt(d));
			System.out.print(" - ");
			System.out.print(Weekday.dayToString(d));
			System.out.print(" ");
		}
		System.out.println();
	}

	public static void printDates()
	{
		System.out.print("Dates are: ");
		for (Day d : Day.values())
		{
			System.out.print(Weekday.dayToInt(d));
			System.out.print(" - ");
			System.out.print(Weekday.dayToString(d));
			System.out.print(" ");
		}
		System.out.println();
	}

	public static void printSelection(HashSet<Integer> dates)
	{
		if (dates.size() > 0)
		{
			System.out.print("You have selected: ");
			for (Integer d : dates)
			{
				System.out.print(Weekday.dayToString(Weekday.intToDay(d)));
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static String askDate(String query, String[] t)
	{
		String time;
		do
		{
			System.out.println(query);
			printPrompt();
			time = input.nextLine();
		} while(!Event.checkIfValid(time, t));
		return time;
	}

	static void printPrompt()
	{
		System.out.print("?>");
	}

	static boolean checkDate(String in)
	{
		Integer day = null;
		// try-catch makes sure that input is numeric
		try
		{

			day = Integer.parseInt(in);
			// check if day is indeed a valid number
			if (day > 0 && day < 8)
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
}

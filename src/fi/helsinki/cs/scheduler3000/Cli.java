package fi.helsinki.cs.scheduler3000;

/**
 * @author Team TA's
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fi.helsinki.cs.scheduler3000.Weekday.Day;

public class Cli
{

	private static Schedule schedule = null;

	public static void main(String[] args)
	{

		Character choice;

		do
		{
			System.out.println();
			printCommands();
			choice = sanitize(InputUtils.askLine("Choice"));

			switch (choice)
			{

				case 'p':
					if (schedule == null)
					{ // cannot do this if schedule is not existing
						break;
					}
					Cli.printReportDialogToScreenDialog();
					break;

				case 'a':
					if (schedule == null)
					{ // cannot do this if schedule is not existing
						break;
					}
					newEventDialog();
					break;

				case 's':
					if (schedule == null)
					{ // cannot do this if schedule is not existing
						break;
					}
					saveScheduleDialog();
					break;

				case 'f':
					if (schedule == null)
					{ // cannot do this if schedule is not existing
						break;
					}
					printReportToFileDialog();
					break;

				case 'c':
					FileOutput fo = FileOutputFactory.makeFileOutput(
					        FileOutputFactory.FileType.CSV, schedule);
					fo.writeToFile("output.csv");
					break;

				case 'n':
					newScheduleDialog();
					break;

				case 'o':
					openScheduleDialog();
					break;

				case 'q':
					System.exit(0);
					break;

				default:
					System.out.println("Don't know what that command is");
					break;

			}
		} while (true);

	}

	private static void newEventDialog()
	{
		String startTime = null, endTime = null, location = null, title = null, eventDayTemp;
		Event event = null;
		Day eventDay = null;

		do
		{
			System.out.println("");

			eventDay = InputUtils.askWeekday("Which day is the event?",
			        Weekday.WEEKDAYS_SET);
			startTime = InputUtils.askTime("What is the start time?", Event
			        .getValidStartTimes());
			endTime = InputUtils.askTime("What is the end time?", Event
			        .getValidEndTimes());
			title = InputUtils
			        .askLine("What this event should be named as?\n(just press enter to skip this)");
			location = InputUtils
			        .askLine("Where this event is held?\n(just press enter to skip this)");

			try
			{
				event = new Event(startTime, endTime, title, location);
				break; // success, get out of the do-while
			} catch (IllegalArgumentException e)
			{

				System.out.println("Sorry, but some mistakes were made:");
				System.out.println(e.getMessage());
			}
		} while (true);

		System.out.print("Adding event to schedule...");
		try
		{
			schedule.addEvent(eventDay, event);
		} catch (IllegalArgumentException e)
		{
			System.out.println("Something went wrong:");
			System.out.println(e.getMessage());
			System.out.println("Sorry, but once more");
			newEventDialog();
			return; // this is for when newEventDialog finally succeedes, we
					// don't print out the last ok!'s
		}
		System.out.println("ok!");

	}

	private static void newScheduleDialog()
	{
		List<Day> days = InputUtils.askManyWeekdays(
		        "Give dates you want to include in the schedule",
		        Weekday.WEEKDAYS_SET);

		System.out.print("Creating schedule...");
		schedule = new Schedule(days);
	}

	private static boolean open(String filename)
	{

		ObjectInputStream objectInput = null; // nullify in case something is
											  // wrong and it's open

		FileInputStream fos = null;

		try
		{
			fos = new FileInputStream(filename);
		} catch (FileNotFoundException e)
		{
			System.out.println("File \"" + filename + "\" couldn't be opened");
			return false;
		}

		try
		{
			objectInput = new ObjectInputStream(fos);
		} catch (IOException e)
		{
			System.out.println("Cannot read \"" + filename
			        + "\" from FileInputStream");
			return false;
		}

		try
		{
			schedule.setSchedule((Schedule) objectInput.readObject());
			return true;
		} catch (IOException e)
		{
			System.out.println("Cannot read \"" + filename
			        + "\" from ObjectInputStream");
		} catch (ClassNotFoundException e)
		{
			System.out
			        .println("Cannot find class for the object when reading \""
			                + filename + "\"");
		}

		return false;
	}

	private static void openScheduleDialog()
	{
		do
		{
			String filename = InputUtils
			        .askLine(
			                "Give name of the file to be opened (empty line to cancel)")
			        .trim();
			if (filename.equals(""))
				return;

			if (!filename.endsWith(".dat"))
				filename += ".dat";

			if (!open(filename))
				continue;
		} while (false);
	}

	private static void printCommands()
	{
		System.out.println("Commands");
		System.out.println("--------");
		System.out.println("[N]ew schedule");
		System.out.println("[O]pen schedule from file");
		if (schedule != null)
		{
			System.out.println("[A]dd event to schedule");
			System.out.println("[S]ave schedule to file");
			System.out.println("[P]rint a report on screen");
			System.out.println("Print a report to [F]ile");
			System.out.println("[C]reate CSV file");
		}
		System.out.println("[Q]uit");
	}

	private static Report printReportDialog()
	{
		System.out.println("Which type of report do you want to print? Options are: ");

		System.out.println("0: None");
		List<String> reportTypes = ReportFactory.getReportTypes();
		int i = 1;
		for (String reportType : reportTypes)
			System.out.println(i++ + ": " + reportType);

		int choice = InputUtils.askInt("Report type", 0, reportTypes.size());
		if (choice == 0)
			return null;
		Report report = ReportFactory.makeReport(reportTypes.get(choice - 1),
		        schedule, null);
		report.askOptionsFromUser();
		return report;
	}

	private static void printReportToFileDialog()
	{
		Report report = printReportDialog();
		if (report != null)
		{
			PrintWriter out = null;
			String filename = null;

			while (true)
			{
				try
				{
					filename = InputUtils.askLine("Enter the file name and path for the report (empty line to cancel)").trim();
					if(filename.equals(""))
						return;
					out = new PrintWriter(filename);
					break; // break out of the loop
				} catch (FileNotFoundException e)
				{
					System.out.println("File " + filename + " was not found");
				}
			}

			System.out.print("Writing the file...");
			out.print(report);
			out.close();
			System.out.println("ok!");
		}
	}

	private static Character sanitize(String rawInput)
	{
		if(rawInput.length() == 0)
			return ' ';
		return new Character(rawInput.toLowerCase().charAt(0));
	}

	private static boolean save(String filename)
	{
		ObjectOutputStream objectOutput = null;
		FileOutputStream fos = null;

		try
		{
			fos = new FileOutputStream(filename);
		} catch (FileNotFoundException e)
		{
			System.out.println("Cannot open \"" + filename
			        + "\", something's wrong with it");
			return false;
		}
		try
		{
			objectOutput = new ObjectOutputStream(fos);
		} catch (IOException e)
		{
			System.out.println("Cannot write to \"" + filename + "\"");
			return false;
		}

		try
		{
			objectOutput.writeObject(schedule);
			objectOutput.close();
			return true;
		} catch (IOException e)
		{
			System.out.println("Writing to \"" + filename + "\" failed");
			return false;
		}
	}

	private static void saveScheduleDialog()
	{
		String filename = null;
		while (true)
		{
		filename = InputUtils.askLine("Give name of the file to open (empty line to cancel)").trim();
		if(filename.equals(""))
			return;
		if (!filename.endsWith(".dat"))
			filename += ".dat";
			if(save(filename))
				break;
		}
		System.out.println("Schedule saved as \"" + filename + "\"");
	}

	public static void printReportDialogToScreenDialog()
	{
		Report report = printReportDialog();
		if (report != null)
			System.out.println(report);
	}

}

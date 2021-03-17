package com.smoothstack.jb.day5;

import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.Month;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.time.Instant;

public class DateTime {

	public Instant birthday;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateTime m1 = new DateTime();
		Scanner scanner = new Scanner(System.in);
		
		m1.printMenu();
		try {
			m1.menu(scanner);
		}
		catch (InputMismatchException e) {
			e.printStackTrace();
		}
		
	}
	
	private LocalDate createSimpleDate(Scanner scanner) throws InputMismatchException {
		System.out.print("Enter year: ");
		int year=scanner.nextInt();
		System.out.print("Enter month: ");
		int month=scanner.nextInt();
		System.out.print("Enter day: ");
		int day=scanner.nextInt();
		return LocalDate.of(year, month, day);
	}
	
	private void printMenu() {
		System.out.println("1. Store birthday");
		System.out.println("2. Find Previous Thursday");
		System.out.println("3. Convert Instant to ZonedDateTime");
		System.out.println("4. Convert ZonedDateTime to Instant");
		System.out.println("5. Print Month Lengths for a year");
		System.out.println("6. List Mondays of this month");
		System.out.println("7. Friday the 13th?");
		System.out.println("0. Exit");
	}
	
	private boolean menu(Scanner scanner) throws InputMismatchException{
		int choice;
		LocalDate date;
		
		choice=scanner.nextInt();
		
		switch(choice) {
		case 1:
			//store birthday
			System.out.println("Enter time in this format: 007-12-03T10:15:30.00Z");
			try {
				storeBirthday(Instant.parse(scanner.nextLine()));
				System.out.println("Successfully stored");
			}
			catch(DateTimeParseException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			//Find previous Thursday
			date = createSimpleDate(scanner);
			previousThursday(date);
			break;
		case 3:
			//Instant to ZonedDateTime
			System.out.println("Enter time in this format: 007-12-03T10:15:30.00Z");
			try {
				findZonedTime(Instant.parse(scanner.nextLine()),ZoneOffset.ofHours(scanner.nextInt()));
				System.out.println("Successfully stored");
			}
			catch(DateTimeParseException e) {
				e.printStackTrace();
			}
			break;
		case 4:
			//ZonedDateTime to Instant
			//Instant to ZonedDateTime
			System.out.println("Enter time in this format: 2007-12-03T10:15:30+01:00[Europe/Paris]"
					+ " followed by the offset in hours.");
			try {
				findInstantTime(ZonedDateTime.parse(scanner.nextLine()));
				System.out.println("Successfully stored");
			}
			catch(DateTimeParseException e) {
				e.printStackTrace();
			}
			break;
		case 5:
			//find length of each month
			System.out.print("Enter a year to check: ");
			choice = scanner.nextInt();
			printMonthLengths(choice);
			break;
		case 6:
			//list Mondays
			date = createSimpleDate(scanner);
			for (LocalDate i : iHateMondays(date)) {
				System.out.println(i.toString());
			}
			break;
		case 7:
			//Friday 13
			date = createSimpleDate(scanner);
			System.out.println(isThirteenth(date));
			break;
		case 8:
			return false;
		}
		return true;
	}
	
	public boolean storeBirthday(Instant date) {
		birthday = date;
		return true;
	}
	
	public LocalDate previousThursday(LocalDate date) {
		DayOfWeek weekDay = date.getDayOfWeek();
		//each day has a value with monday starting at 1
		//compare takes the first day number and subtracts the second from it
		int offset = DayOfWeek.THURSDAY.compareTo(weekDay);
		// if the Thursday is in a feature date or if the day is Thursday
		if (offset >= 0) {
			offset -= 7;
		}
		int day = date.getDayOfMonth() + offset;
		int month = date.getMonthValue();
		int year = date.getYear();
		
		// if the day is on the previous month
		if (day <= 0) {
			//change to previous month
			month--;
			if (month==0) {
				month=12;
				year--;
			}
			day = Month.of(month).length(Year.isLeap(year));
			LocalDate temp = LocalDate.of(year, month, day);
			while (temp.getDayOfWeek()!=DayOfWeek.THURSDAY) {
				day--;
				temp = LocalDate.of(year, month, day);
			}
		}
		//time doesn't matter in this case
		LocalDate prevThur = LocalDate.of(year, month, day);
		return prevThur;
	}
	
	public ZonedDateTime findZonedTime(Instant date, ZoneOffset offset) {
		//gets the Time zone from given offset from this one
		ZoneId id = ZoneOffset.ofOffset("UTC", offset);
		//returns date time at this zone
		return date.atZone(id);
	}
	
	public Instant findInstantTime(ZonedDateTime date) {
		return date.toInstant();
	}
	
	public boolean printMonthLengths(int choice) {
		boolean leap = Year.isLeap(choice);
		try {
			for (Month month:Month.values()) {
				System.out.println("The length of " + month.name() + " is " + month.length(leap));
			}
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public List<LocalDate> iHateMondays(LocalDate date) {
		// find every Monday date
		List<LocalDate> mondayMadness = new ArrayList<LocalDate>();
		int i = date.getDayOfMonth();
		DayOfWeek day = date.getDayOfWeek();
		i += day.compareTo(DayOfWeek.MONDAY);
		//Goes to the first monday
		while (i>0) {
			i-=7;
			date=date.minusDays(7);
		}
		i+=7;
		date=date.plusDays(7);
		int length = Month.of(date.getMonthValue()).length(Year.isLeap(date.getYear()));
		for (;i<=length;i+=7) {
			mondayMadness.add(date);
			date=date.plusDays(7);
		}
		return mondayMadness;
	}
	
	public boolean isThirteenth(LocalDate date) {
		if (date.getDayOfWeek()==DayOfWeek.FRIDAY&&date.getDayOfMonth()==13){
			return true;
		}
		return false;
	}
	
}

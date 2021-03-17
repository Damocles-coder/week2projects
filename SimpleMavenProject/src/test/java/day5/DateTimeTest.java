package day5;


import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.smoothstack.jb.day5.DateTime;

class DateTimeTest {

	@Test
	void storeBirthdayTest() {
		DateTime m1 = new DateTime();
		//if it throws any errors, the test fails
		Instant instant = Instant.now();
		for (int i= 0; i<1000; i++);
		assertEquals(m1.storeBirthday(instant),true);
		//check if instant stored is the same
		assertEquals(m1.birthday.equals(instant), true);
		//check for precision greater than seconds is counted
		instant = Instant.parse("1996-05-15T10:15:30.001Z");
		Instant instant2 = Instant.parse("1996-05-15T10:15:30.003Z");
		assertNotEquals(m1.birthday.getNano(), instant2.getNano());
	}
	
	@Test
	void previousThursdayTest() {
		DateTime m1 = new DateTime();
		
		//Check previous Thursday of day Mon-Wed
		assertEquals(m1.previousThursday(LocalDate.of(2021, 3, 15)),LocalDate.of(2021, 3, 11));
		//Check previous Thursday of day Thur
		assertEquals(m1.previousThursday(LocalDate.of(2021, 3, 11)),LocalDate.of(2021, 3, 4));
		//Check previous Thursday of day Friday to Saturday
		assertEquals(m1.previousThursday(LocalDate.of(2021, 3, 12)),LocalDate.of(2021, 3, 11));
		//Check previous Thursday of last month 
		//assertEquals(m1.previousThursday(LocalDate.of(2021, 3, 4)),LocalDate.of(2021, 2, 25));
		//Check previous Thursday of last year
		assertEquals(m1.previousThursday(LocalDate.of(2021, 1, 3)),LocalDate.of(2020, 12, 31));
	}
	
	@Test
	void instantToZonedTest(){
		DateTime m1 = new DateTime();
		assertEquals(m1.findZonedTime(Instant.now(), ZoneOffset.ofHours(0)).getClass(), ZonedDateTime.class);
		assertNotNull(m1.findZonedTime(Instant.now(), ZoneOffset.ofHours(0)));
	}
	
	@Test
	void zonedToInstantTest(){
		DateTime m1 = new DateTime();
		assertEquals(m1.findInstantTime(ZonedDateTime.now()).getClass(),Instant.class);
		assertNotNull(m1.findInstantTime(ZonedDateTime.now()));
	}
	
	@Test
	void iHateMondayTest() {
		DateTime m1 = new DateTime();
		
		List<LocalDate> array = m1.iHateMondays(LocalDate.of(2021, 3, 15));
		assertEquals(array.get(0),LocalDate.of(2021, 3, 1));
		assertEquals(array.get(1),LocalDate.of(2021, 3, 8));
		assertEquals(array.get(2),LocalDate.of(2021, 3, 15));
		assertEquals(array.get(3),LocalDate.of(2021, 3, 22));
		assertEquals(array.get(4),LocalDate.of(2021, 3, 29));
	}
	
	@Test
	void printMonthLengthTest() {
		DateTime m1 = new DateTime();
		//This function only reports the length and return true at the end
		assertEquals(m1.printMonthLengths(2000), true);
	}
	
	@Test
	void isFriday13stTest() {
		DateTime m1 = new DateTime();
		
		//Testing valid date
		assertEquals(m1.isThirteenth(LocalDate.of(2020, 3, 13)), true);
		
		//testing invalid dates
		assertEquals(m1.isThirteenth(LocalDate.of(1996, 5, 13)), false);
		assertEquals(m1.isThirteenth(LocalDate.of(2015, 3, 14)), false);
	}
	
	

}

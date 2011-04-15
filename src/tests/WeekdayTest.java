package tests;

/**
 * @author Team TA's
 */


import junit.framework.TestCase;
import org.junit.*;

import KaiSei.Weekday;
import KaiSei.Weekday.Day;


public class WeekdayTest extends TestCase {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDayToString() {
		assertTrue(Weekday.dayToString(Day.WED) != null);
	}

	@Test
	public void testDayToInt() {
		assertTrue(Weekday.dayToInt(Day.WED) == 3);
	}
	
	@Test
	public void testIntToDay() {
		assertTrue(Weekday.intToDay(3) == Day.WED);
	}
}

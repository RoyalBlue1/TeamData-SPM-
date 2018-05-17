package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import org.junit.Test;

import analysis.Analysis;

public class analysisTest {

	@Test
	public void testTopItems() throws Exception {
		
		Analysis analysis;
		
		InputStream csv = new FileInputStream(getClass().getResource("/testCSV.csv").getFile());
		
		analysis = new Analysis(csv);		
		
		//test top 5 items
		assertEquals("Kaffee / Tee", analysis.getTopItems().get(0));
		assertEquals("Backwaren", analysis.getTopItems().get(1));
		assertEquals("Alkoholfreie Getränke", analysis.getTopItems().get(2));
		assertEquals("Tiefkühlware", analysis.getTopItems().get(3));
		assertEquals("Konserven", analysis.getTopItems().get(4));
	}
	
	@Test
	public void testWeekday() throws Exception {
		Analysis analysis;
		
		InputStream csv = new FileInputStream(getClass().getResource("/testCSV.csv").getFile());
		
		analysis = new Analysis(csv);
		
		Map<String, Integer> mapWeek = analysis.getCustomersWeekday();
		
		//weekday
		assertEquals(null, mapWeek.get("Montag"));
		assertEquals(null, mapWeek.get("Dienstag"));
		assertEquals((Integer)3, mapWeek.get("Mittwoch"));
		assertEquals((Integer)1, mapWeek.get("Donnerstag"));
		assertEquals(null, mapWeek.get("Freitag"));
		assertEquals((Integer)1, mapWeek.get("Samstag"));
	}
	
	@Test
	public void testDaytime() throws Exception {
		Analysis analysis;
		
		InputStream csv = new FileInputStream(getClass().getResource("/testCSV.csv").getFile());
		
		analysis = new Analysis(csv);
		
		Map<String, Integer> mapDaytime = analysis.getCustomersDaytime();
		
		//daytime
		assertEquals((Integer)1, mapDaytime.get("<10 Uhr"));
		assertEquals((Integer)3, mapDaytime.get("10-12 Uhr"));
		assertEquals(null, mapDaytime.get("12-14 Uhr"));
		assertEquals(null, mapDaytime.get("14-17 Uhr"));
		assertEquals((Integer)1, mapDaytime.get(">17 Uhr"));
	}

}

package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.Test;

import analysis.Analysis;

public class analysisTest {

	@Test
	public void test() throws Exception {
		
		Analysis analysis;
		
		String csv = getClass().getResource("/testCSV.csv").getFile();
		
		analysis = new Analysis(csv,  new File("week.jpg"), new File("daytime.jpg"));
		
		//test top 5 items
		assertEquals("Kaffee / Tee", analysis.getTopItems().get(0));
		assertEquals("Backwaren", analysis.getTopItems().get(1));
		assertEquals("Alkoholfreie Getränke", analysis.getTopItems().get(2));
		assertEquals("Tiefkühlware", analysis.getTopItems().get(3));
		assertEquals("Konserven", analysis.getTopItems().get(4));
		
	}

}

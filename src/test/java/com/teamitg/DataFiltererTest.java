package com.teamitg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;



class DataFiltererTest {

  @Test
  void shouldReturnEmptyList_WhenLogFileIsEmpty() throws IOException {
    assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
  }
  
  @Test
  void shouldReturnCorrectEntries_WhenLogFileHasSingleLine() throws IOException {
	  List<LogEntry> result = DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "GB");
     // result.forEach(entry -> System.out.println("Country: " + entry.getCountryCode() + ", Response Time: " + entry.getResponseTime()));
      assertEquals(1, result.size()); // Expected 1 entry
  }
  
  @Test
  void shouldReturnCorrectEntries_WhenLogFileHasMultipleLines() throws IOException {
      List<LogEntry> result = DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "US");
     // result.forEach(entry -> System.out.println("Country: " + entry.getCountryCode() + ", Response Time: " + entry.getResponseTime()));
      assertEquals(3, result.size());
  }
  
  @Test
  void shouldReturnEntriesWithResponseTimeAboveLimit() throws IOException {
	  List<LogEntry> result = DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 500);
     // System.out.println("Result entries for country US with response time above 500:");
     // result.forEach(entry -> System.out.println("Country: " + entry.getCountryCode() + ", Response Time: " + entry.getResponseTime()));
      assertEquals(3, result.size()); 
  }

  @Test
  void shouldReturnEntriesWithResponseTimeAboveAverage() throws IOException {
      List<LogEntry> result = DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines"));
     // System.out.println("Result entries for country US with response time above 500:");
     // result.forEach(entry -> System.out.println("Country: " + entry.getCountryCode() + ", Response Time: " + entry.getResponseTime()));
      assertEquals(3, result.size());
  }

  private FileReader openFile(String filename) throws FileNotFoundException {
    return new FileReader(filename);
  }
}

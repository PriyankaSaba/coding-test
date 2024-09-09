package com.teamitg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class DataFilterer {

	public static List<LogEntry> filterByCountry(Reader source, String country) 
			throws IOException {

		Objects.requireNonNull(source, "Source cannot be null");
		Objects.requireNonNull(country, "Country cannot be null");

		try (BufferedReader reader = new BufferedReader(source)) {
			List<LogEntry> entries = parseLogEntries(reader);
			return entries.stream()
					      .filter(entry -> entry.getCountryCode().equalsIgnoreCase(country))
					      .collect(Collectors.toList());
		}
	}

	public static List<LogEntry> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit)
			throws IOException {

		Objects.requireNonNull(source, "Source cannot be null");
		Objects.requireNonNull(country, "Country cannot be null");

		try (BufferedReader reader = new BufferedReader(source)) {
			List<LogEntry> entries = parseLogEntries(reader);

			return entries.stream()
					.filter(entry -> entry.getCountryCode().equalsIgnoreCase(country))
					.filter(entry -> entry.getResponseTime() > limit)
					.collect(Collectors.toList());
		}
	}

	public static List<LogEntry> filterByResponseTimeAboveAverage(Reader source) throws IOException {
		Objects.requireNonNull(source, "Source cannot be null");

		try (BufferedReader reader = new BufferedReader(source)) {
			List<LogEntry> entries = parseLogEntries(reader);

			//entries.forEach(entry -> System.out.println(entry.getCountryCode() + " " + entry.getResponseTime()));

			if (entries.isEmpty()) {
				return Collections.emptyList();
			}

			double average = entries.stream()
					                .mapToLong(LogEntry::getResponseTime)
					                .average()
					                .orElse(0);


			List<LogEntry> result = entries.stream()
					                .filter(entry -> entry.getResponseTime() > average)
					                .collect(Collectors.toList());

			//result.forEach(entry -> System.out.println(entry.getCountryCode() + " " + entry.getResponseTime()));

			return result;
		}

	}

	private static List<LogEntry> parseLogEntries(BufferedReader reader) throws IOException {
		return reader.lines()
				.skip(1).map(line -> line.split(","))
				.filter(parts -> parts.length == 3)
				.map(parts -> {
			try {
				return new LogEntry(Long.parseLong(parts[0]), parts[1], Long.parseLong(parts[2]));
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid number format in log entry", e);
			}
		}).collect(Collectors.toList());
	}

}

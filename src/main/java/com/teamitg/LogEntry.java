package com.teamitg;

public class LogEntry {
    private long timestamp;
    private String countryCode;
    private long responseTime;

    public LogEntry(long timestamp, String countryCode, long responseTime) {
        this.timestamp = timestamp;
        this.countryCode = countryCode;
        this.responseTime = responseTime;
    }
    public long getTimestamp() {
        return timestamp;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public long getResponseTime() {
        return responseTime;
    }
    
    @Override
    public String toString() {
        return "LogEntry{" +
                "timestamp=" + timestamp +
                ", countryCode='" + countryCode + '\'' +
                ", responseTime=" + responseTime +
                '}';
    }
}

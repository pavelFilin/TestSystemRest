package ru.filin.bll.utils;


import com.google.gwt.core.client.Duration;

public class MyProfiler {
    private boolean active;

    private double startTime;
    private double endTime;

    public void start() {
        startTime = Duration.currentTimeMillis();
        active = true;
    }

    public double end() {
        endTime = Duration.currentTimeMillis();
        return endTime-startTime;
    }

    public double getStartTime() throws Exception {
        if (!active) {
            return startTime;
        } else {
            throw new Exception("Profiler wasn't start");
        }
    }

    public double getEndTime() throws Exception {
        if (!active) {
            return endTime;
        } else {
            throw new Exception("Profiler wasn't start");
        }
    }

}
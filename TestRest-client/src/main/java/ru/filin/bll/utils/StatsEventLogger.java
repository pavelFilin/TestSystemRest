package ru.filin.bll.utils;

public class StatsEventLogger {
    public static native void logEvent(String moduleName, String subSystem,
                                       String eventGroup, double millis, String type) /*-{
        $wnd.__gwtStatsEvent({
            'moduleName': moduleName,
            'subSystem': subSystem,
            'evtGroup': eventGroup,
            'millis': millis,
            'type': type
        });
    }-*/;
}

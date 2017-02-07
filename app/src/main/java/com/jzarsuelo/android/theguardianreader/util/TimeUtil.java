package com.jzarsuelo.android.theguardianreader.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for Time conversion
 */

public class TimeUtil {

    private static final String LOG_TAG = TimeUtil.class.getSimpleName();

    private TimeUtil() {}

    /** Convert the String date to milliseconds based on the specified format */
    public static Long toMilliseconds(String dateTime, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateTime);
            return date.getTime();
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error in parsing dateTime. dateTime ("+dateTime+"); format("+format+")");
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }

        return 0l;
    }

    /** Convert milliseconds to formatted date and/or time */
    public static String millisecondsToString(Long webPublicationDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(webPublicationDate);
        return sdf.format(date);
    }
}

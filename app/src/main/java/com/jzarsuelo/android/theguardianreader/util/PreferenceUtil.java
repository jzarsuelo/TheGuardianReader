package com.jzarsuelo.android.theguardianreader.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Utility class for retrieving values from SharedPreference
 */

public class PreferenceUtil {

    private PreferenceUtil(){}

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, defaultValue);
    }
}

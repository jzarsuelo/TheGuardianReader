package com.jzarsuelo.android.theguardianreader.util;

import android.content.Context;
import android.net.Uri;

import com.jzarsuelo.android.theguardianreader.R;

/**
 * Utility class for communicating to The Guardian API
 */

public class TheGuardianApiUtil {
    private TheGuardianApiUtil(){}

    public static String getApiKey(Context context) {
        return context.getString(R.string.the_guardian_api_key);
    }

    public static String getContentEndpoint(Context context) {
        final String uriString = context.getString(R.string.the_guardian_api_content_endpoint);

        Uri baseUri = Uri.parse(uriString);

        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("api-key", getApiKey(context));

        return uriBuilder.toString();
    }
}

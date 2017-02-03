package com.jzarsuelo.android.theguardianreader.util;

import android.content.Context;
import android.net.Uri;

import com.jzarsuelo.android.theguardianreader.R;

/**
 * Utility class for
 */

public class TheGuardianApiUtil {
    private TheGuardianApiUtil(){}

    public static String getApiKey(Context context) {
        return context.getString(R.string.the_guardian_api_key);
    }

    public static String getContentEndpoint(Context context) {
        final String baseUri = context.getString(R.string.the_guardian_api_content_endpoint);
        Uri uri = new Uri.Builder()
                .appendQueryParameter("api-key", getApiKey(context))
                .build();
        return uri.toString();
    }
}

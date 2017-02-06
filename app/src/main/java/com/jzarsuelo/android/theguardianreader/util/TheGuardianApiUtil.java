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

        final String pageSizeValue = PreferenceUtil.getString(
                context,
                context.getString(R.string.settings_number_of_news_to_load_key),
                context.getString(R.string.settings_number_of_news_to_load_default));

        Uri baseUri = Uri.parse(uriString);

        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("api-key", getApiKey(context));
        uriBuilder.appendQueryParameter("page-size", pageSizeValue);

        return uriBuilder.toString();
    }
}

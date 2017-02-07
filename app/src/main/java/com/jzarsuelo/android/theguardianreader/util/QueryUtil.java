package com.jzarsuelo.android.theguardianreader.util;

import android.util.Log;

import com.jzarsuelo.android.theguardianreader.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for fetching data from the given URL
 */
public class QueryUtil {

    private final static String LOG_TAG = QueryUtil.class.getSimpleName();

    /**
     *
     * @param requestUrl
     * @return
     */
    public static List<News> fetchNewsData(String requestUrl){
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(requestUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<News> news = parseJsonResponse(jsonResponse);

        return news;
    }

    /**
     * Perform a HTTP Request for the given url
     * @param requestUrl
     * @return
     * @throws IOException
     */
    private static String makeHttpRequest(String requestUrl) throws IOException {

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            Log.d(LOG_TAG, "Request URL: " + requestUrl);
            URL url = new URL(requestUrl);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(10000);
            connection.connect();

            Log.d(LOG_TAG, "HTTP Response Code: " + connection.getResponseCode());

            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                return readFromStream(inputStream);
            }

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "There's an error in the URL format.");
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        } catch (IOException e) {
            Log.e(LOG_TAG, "There's an error in getting the InputStream.");
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return null;
    }

    /**
     *
     * Read data from stream and convert it to a String
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder response = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                response.append( line );
                line = bufferedReader.readLine();
            }
        }

        return response.toString();
    }

    /**
     * Return a list of news from the parsed JSON Response String.
     */
    private static List<News> parseJsonResponse(String jsonResponse) {
        List<News> newsList = new ArrayList<>();

        if (jsonResponse != null && !jsonResponse.isEmpty()) {
            try {
                JSONObject rootJson = new JSONObject(jsonResponse);
                JSONObject responseJson = rootJson.getJSONObject("response");

                JSONArray newsJsonArray = responseJson.getJSONArray("results");
                for (int i = 0; i < newsJsonArray.length(); i++) {
                    JSONObject newsJsonObject = newsJsonArray.getJSONObject(i);

                    String webPublicationDate = newsJsonObject.optString("webPublicationDate");
                    Long webPublicationDateInMilli =
                            TimeUtil.toMilliseconds(webPublicationDate, News.DATE_TIME_FORMAT);

                    News news = new News(
                            newsJsonObject.getString("sectionId"),
                            newsJsonObject.getString("sectionName"),
                            newsJsonObject.getString("webTitle"),
                            newsJsonObject.getString("webUrl"),
                            newsJsonObject.getString("apiUrl"),
                            newsJsonObject.optString("author"),
                            webPublicationDateInMilli

                    );

                    newsList.add(news);
                }

            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error parsing the JSON Response: \n" + jsonResponse);
                Log.e(LOG_TAG, Log.getStackTraceString(e));
                e.printStackTrace();
            }
        }

        return newsList;
    }
}

package com.jzarsuelo.android.theguardianreader.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.jzarsuelo.android.theguardianreader.model.News;
import com.jzarsuelo.android.theguardianreader.util.QueryUtil;

import java.util.List;

/**
 * Loads a list of news by performing a network request to the
 * given URL
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG = NewsLoader.class.getSimpleName();
    private final String URL;

    public NewsLoader(Context context, String url) {
        super(context);
        URL = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        return QueryUtil.fetchNewsData(URL);
    }
}

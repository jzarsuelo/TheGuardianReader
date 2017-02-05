package com.jzarsuelo.android.theguardianreader;


import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jzarsuelo.android.theguardianreader.loader.NewsLoader;
import com.jzarsuelo.android.theguardianreader.util.TheGuardianApiUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final int NEWS_LOADER_ID = 0;

    private ListView mNewsListView;
    private NewsAdapter mNewsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(NEWS_LOADER_ID, null, this);

        mNewsAdapter = new NewsAdapter(this, new ArrayList<News>());

        mNewsListView = (ListView) findViewById(R.id.news_list_view);
        mNewsListView.setAdapter(mNewsAdapter);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this,
                TheGuardianApiUtil.getContentEndpoint(this));
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        hideLoadingSpinner();

        TextView emptyListTextView = (TextView) findViewById(R.id.empty_list_view);
        mNewsListView.setEmptyView(emptyListTextView);

        if (data != null && !data.isEmpty()) {
            mNewsAdapter.clear();
            mNewsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNewsAdapter.clear();
    }

    private void hideLoadingSpinner() {
        ProgressBar loadingSpinner = (ProgressBar) findViewById(R.id.loading_spinner);
        loadingSpinner.setVisibility(View.GONE);
    }
}

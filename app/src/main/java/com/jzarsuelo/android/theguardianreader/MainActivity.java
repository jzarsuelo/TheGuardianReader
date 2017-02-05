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
import com.jzarsuelo.android.theguardianreader.util.NetworkUtil;
import com.jzarsuelo.android.theguardianreader.util.TheGuardianApiUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final int NEWS_LOADER_ID = 0;

    private ListView mNewsListView;
    private NewsAdapter mNewsAdapter;
    private TextView mEmptyListTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( NetworkUtil.isConnected(this) ) {
            getLoaderManager().initLoader(NEWS_LOADER_ID, null, this);
        } else {
            hideLoadingSpinner();
        }

        mNewsAdapter = new NewsAdapter(this, new ArrayList<News>());

        mEmptyListTextView = (TextView) findViewById(R.id.empty_list_view);

        mNewsListView = (ListView) findViewById(R.id.news_list_view);
        mNewsListView.setAdapter(mNewsAdapter);
        mNewsListView.setEmptyView(mEmptyListTextView);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this,
                TheGuardianApiUtil.getContentEndpoint(this));
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        hideLoadingSpinner();


        if (data != null && !data.isEmpty()) {
            mNewsAdapter.clear();
            mNewsAdapter.addAll(data);
        } else {
            mEmptyListTextView.setText(R.string.empty_list_message);
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

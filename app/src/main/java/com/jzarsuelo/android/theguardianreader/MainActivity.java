package com.jzarsuelo.android.theguardianreader;


import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jzarsuelo.android.theguardianreader.loader.NewsLoader;
import com.jzarsuelo.android.theguardianreader.model.News;
import com.jzarsuelo.android.theguardianreader.util.NetworkUtil;
import com.jzarsuelo.android.theguardianreader.util.TheGuardianApiUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final int NEWS_LOADER_ID = 0;

    private NewsAdapter mNewsAdapter;

    private ListView mNewsListView;
    private TextView mEmptyListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( NetworkUtil.isConnected(this) ) {
            // this will trigger "onCreateLoader(int id, Bundle args)"
            getLoaderManager().initLoader(NEWS_LOADER_ID, null, this);
        } else {
            showEmptyListMessage();
        }

        mNewsAdapter = new NewsAdapter(this, new ArrayList<News>());

        mEmptyListTextView = (TextView) findViewById(R.id.empty_list_view);

        mNewsListView = (ListView) findViewById(R.id.news_list_view);
        mNewsListView.setAdapter(mNewsAdapter);
        mNewsListView.setEmptyView(mEmptyListTextView);
        mNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                News news = mNewsAdapter.getItem(position);

                Intent i = new Intent(Intent.ACTION_VIEW);
                Intent appChooserIntent = i.setData(Uri.parse(news.getWebUrl()));

                if (appChooserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(appChooserIntent);
                }
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this,
                TheGuardianApiUtil.getContentEndpoint(this));
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        showEmptyListMessage();

        if (data == null && data.isEmpty()) {
            showEmptyListMessage();
        } else {
            mNewsAdapter.clear();
            mNewsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNewsAdapter.clear();
    }

    private void showEmptyListMessage() {
        ProgressBar loadingSpinner = (ProgressBar) findViewById(R.id.loading_spinner);
        loadingSpinner.setVisibility(View.GONE);

        mEmptyListTextView.setText(R.string.empty_list_message);
    }
}

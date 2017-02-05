package com.jzarsuelo.android.theguardianreader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jzarsuelo.android.theguardianreader.model.News;

import java.util.List;

/**
 * Adapter class for {@link MainActivity#mNewsListView}
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> data) {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_item, parent, false);
        }

        News news = getItem(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.news_title);
        titleTextView.setText( news.getWebTitle() );

        return convertView;
    }


}

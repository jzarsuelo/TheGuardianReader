package com.jzarsuelo.android.theguardianreader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jzarsuelo.android.theguardianreader.model.News;
import com.jzarsuelo.android.theguardianreader.util.TimeUtil;

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

        TextView sectionNameTextView = (TextView) convertView.findViewById(R.id.section_name);
        sectionNameTextView.setText( news.getSectionName() );

        String author = news.getAuthor();
        if ( author != null && !author.isEmpty() ) {
            TextView authorTextView = (TextView) convertView.findViewById(R.id.news_author);
            authorTextView.setVisibility(View.VISIBLE);
            authorTextView.setText( news.getAuthor() );
        }

        String formattedDate = TimeUtil.millisecondsToString( news.getWebPublicationDate(), News.DATE_FORMAT );
        TextView datePublished = (TextView) convertView.findViewById(R.id.date_published);
        datePublished.setText( formattedDate );

        return convertView;
    }


}

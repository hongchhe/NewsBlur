package com.newsblur.database;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newsblur.R;
import com.newsblur.domain.Feed;
import com.newsblur.domain.Story;

public class FeedItemsAdapter extends SimpleCursorAdapter {

	private Cursor cursor;
	private final Feed feed;
	private int darkGray;
	private int lightGray;

	public FeedItemsAdapter(Context context, Feed feed, int layout, Cursor c, String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		this.feed = feed;
		this.cursor = c;
		
		darkGray = context.getResources().getColor(R.color.darkgray);
		lightGray = context.getResources().getColor(R.color.lightgray);
	}

	@Override
	public int getCount() {
		return cursor.getCount();
	}

	@Override
	public Cursor swapCursor(Cursor c) {
		this.cursor = c;
		return super.swapCursor(c);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		View v = super.getView(position, view, viewGroup);
		View borderOne = v.findViewById(R.id.row_item_favicon_borderbar_1);
		View borderTwo = v.findViewById(R.id.row_item_favicon_borderbar_2);
		View sidebar = v.findViewById(R.id.row_item_sidebar);
		cursor.moveToPosition(position);

		if (!TextUtils.equals(feed.faviconColour, "#null") && !TextUtils.equals(feed.faviconFade, "#null")) {
			borderOne.setBackgroundColor(Color.parseColor(feed.faviconBorder));
			borderTwo.setBackgroundColor(Color.parseColor(feed.faviconColour));
		} else {
			borderOne.setBackgroundColor(Color.GRAY);
			borderTwo.setBackgroundColor(Color.LTGRAY);
		}

		// 1 is read
		if (Story.fromCursor(cursor).read == 0) {
			((TextView) v.findViewById(R.id.row_item_author)).setTextColor(darkGray);
			((TextView) v.findViewById(R.id.row_item_date)).setTextColor(darkGray);
			((TextView) v.findViewById(R.id.row_item_title)).setTypeface(null, Typeface.BOLD);
			borderOne.getBackground().setAlpha(255);
			sidebar.getBackground().setAlpha(255);
			borderTwo.getBackground().setAlpha(255);
		} else {
			((TextView) v.findViewById(R.id.row_item_author)).setTextColor(lightGray);
			((TextView) v.findViewById(R.id.row_item_date)).setTextColor(lightGray);
			((TextView) v.findViewById(R.id.row_item_title)).setTypeface(null, Typeface.NORMAL);
			borderOne.getBackground().setAlpha(125);
			sidebar.getBackground().setAlpha(125);
			borderTwo.getBackground().setAlpha(125);
		}

		return v;
	}

}

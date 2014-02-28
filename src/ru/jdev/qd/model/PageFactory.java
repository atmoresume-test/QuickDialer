package ru.jdev.qd.model;

import android.content.Context;
import ru.jdev.qd.selection.RecentFirstSelection;

import java.util.HashMap;
import java.util.Map;

public class PageFactory {

	private static final Map<Integer, Page2> cache = new HashMap<Integer, Page2>();

	public synchronized static Page2 getPage(Integer appWidgetId, Context context) {
		if (!cache.containsKey(appWidgetId)) {
			cache.put(appWidgetId, createPage(context));
		}

		return cache.get(appWidgetId);
	}

	private static Page2 createPage(Context context) {
		final int recentContactsCount = 4;
		final int favoriteContactsCount = 4;
		return new Page2(recentContactsCount + favoriteContactsCount, new ContactInfoDao(context), new RecentFirstSelection(recentContactsCount, favoriteContactsCount));
	}

}

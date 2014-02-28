package ru.jdev.qd;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import ru.jdev.qd.model.Page2;
import ru.jdev.qd.model.PageFactory;

public class UpdateUIService extends IntentService {

	public UpdateUIService() {
		super("UpdateUIService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final Context context = getApplicationContext();
		final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		final int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, QdWidgetProvider.class));
		for (int appWidgetId : appWidgetIds) {
			updateWidget(appWidgetManager, appWidgetId, PageFactory.getPage(appWidgetId, context));
		}
	}

	private void updateWidget(AppWidgetManager appWidgetManager, int appWidgetId, Page2 page) {

	}

	private class ItemIds {

		public final int imageId;
		public final int labelId;

		private ItemIds(int imageId, int labelId) {
			this.imageId = imageId;
			this.labelId = labelId;
		}
	}

}

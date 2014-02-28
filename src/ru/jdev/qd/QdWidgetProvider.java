package ru.jdev.qd;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import ru.jdev.qd.services.UpdateService;

public class QdWidgetProvider extends AppWidgetProvider {

    public static final String PREFS_FILE_NAME = "qd_prefs";

    private static final String TAG = "QD.WP";

    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.v(TAG, "onUpdate");

        final Intent updateWidgets = new Intent(context, UpdateService.class);
        updateWidgets.putExtra(UpdateService.EXTRA_APP_WIDGET_IDS, appWidgetIds);
        context.startService(updateWidgets);

        Log.v(TAG, "onUpdate finished");
    }

}

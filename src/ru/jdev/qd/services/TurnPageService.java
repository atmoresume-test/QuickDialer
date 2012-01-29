/*
 *
 *  * Copyright (c) 2012 Alexey Zhidkov (Jdev). All Rights Reserved.
 *  
 */

package ru.jdev.qd.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import ru.jdev.qd.QdWidgetProvider;
import ru.jdev.qd.Utils;

public class TurnPageService extends IntentService {

    private static final String TAG = "QD.TPS";

    public static final String EXTRA_APP_WIDGET_ID = "appWidgetId";
    public static final String EXTRA_DIRECTION = "dir";
    
    private static final int NO_WIDGET_ID = -999;

    public TurnPageService() {
        super("TurnPageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "Intent received: " + intent.toString());
        final int appWidgetId = intent.getIntExtra(EXTRA_APP_WIDGET_ID, NO_WIDGET_ID);
        if (appWidgetId == NO_WIDGET_ID) {
            Log.w(TAG, "Invalid intent received: " + intent.toString());
            return;
        }
        final boolean dir = intent.getBooleanExtra(EXTRA_DIRECTION, true);

        final SharedPreferences prefs = getSharedPreferences(QdWidgetProvider.PREFS_FILE_NAME, MODE_PRIVATE);
        final String widgetPagePropName = Utils.getWidgetPageProperty(appWidgetId);
        final int pagesCount = Utils.getPagesCount();

        int curPage = prefs.getInt(widgetPagePropName, 0);
        if (dir) {
            curPage++;
            if (curPage >= pagesCount) {
                curPage = 0;
            }
        } else {
            curPage--;
            if (curPage < 0) {
                curPage = pagesCount - 1;
            }
        }
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(widgetPagePropName, curPage);
        editor.commit();

        final Intent updateWidgets = new Intent(this, UpdateService.class);
        updateWidgets.putExtra(UpdateService.EXTRA_APP_WIDGET_IDS, new int[]{appWidgetId});
        startService(updateWidgets);
    }
}

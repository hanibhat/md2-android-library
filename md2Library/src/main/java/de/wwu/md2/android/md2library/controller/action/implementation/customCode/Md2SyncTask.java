package de.wwu.md2.android.md2library.controller.action.implementation.customCode;

import android.os.Handler;
import android.os.Looper;

import de.wwu.md2.android.md2library.controller.action.implementation.customCode.interfaces.Md2CustomCodeTask;
import de.wwu.md2.android.md2library.exception.Md2WidgetNotCreatedException;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.wwu.md2.android.md2library.model.contentProvider.interfaces.Md2ContentProvider;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Type;
import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;
import de.wwu.md2.android.md2library.view.widgets.interfaces.Md2Content;
import de.wwu.md2.android.md2library.view.widgets.interfaces.Md2Widget;

/**
 * Synchronizes a widget and a content provider.
 * Used by Md2SynchronizeContentProviderDataMappingAction and Md2SynchronizeWidgetDataMappingAction
 * <p/>
 * Created on 11/09/2015
 *
 * @author Fabian Wrede
 * @version 1.0
 * @since 1.0
 */
public class Md2SyncTask implements Md2CustomCodeTask {

    private int widgetId;
    private String contentProvider;
    private String attribute;
    private Md2SyncDirection direction;

    /**
     * Instantiates a new Md 2 sync task.
     *
     * @param widgetId        the widget id
     * @param contentProvider the content provider
     * @param attribute       the attribute
     * @param direction       the direction
     */
    public Md2SyncTask(int widgetId, String contentProvider, String attribute, Md2SyncDirection direction) {
        this.widgetId = widgetId;
        this.contentProvider = contentProvider;
        this.attribute = attribute;
        this.direction = direction;
    }

    @Override
    public void execute() throws Md2WidgetNotCreatedException {
        if (direction == Md2SyncDirection.CONTENTPROVIDER_TO_WIDGET) {
            Md2Widget widget = Md2ViewManager.getInstance().getWidget(widgetId);
            if (widget == null) {
                throw new Md2WidgetNotCreatedException();
            }

            Md2Content content = (Md2Content) widget;
            Md2Type value = Md2ContentProviderRegistry.getInstance().getContentProvider(contentProvider).getValue(attribute);

            if(value != null) { // TODO check if unsetting values works
                // Make change to UI in main thread!
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        content.setValue(value);
                    }
                });
            }

        } else {
            Md2ContentProvider cp = Md2ContentProviderRegistry.getInstance().getContentProvider(contentProvider);
            Md2Type value = Md2ViewManager.getInstance().getWidgetValue(widgetId);
            cp.setValue(attribute, value);
        }
    }
}

package com.tech.ayugram.ui;

import static com.tech.ayugram.messenger.LocaleController.getString;

import android.app.Activity;
import android.graphics.PointF;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.tech.ayugram.messenger.AndroidUtilities;
import com.tech.ayugram.messenger.LocaleController;
import com.tech.ayugram.messenger.R;
import com.tech.ayugram.ui.ActionBar.ActionBarMenuSubItem;
import com.tech.ayugram.ui.ActionBar.ActionBarPopupWindow;
import com.tech.ayugram.ui.ActionBar.INavigationLayout;
import com.tech.ayugram.ui.ActionBar.Theme;
import com.tech.ayugram.ui.Components.LayoutHelper;
import com.tech.ayugram.ui.Components.chat.ViewPositionWatcher;

public class ReadAllMentionsMenu {

    public final static int TYPE_REACTIONS = 0;
    public final static int TYPE_MENTIONS = 1;
    public final static int TYPE_POLL_VOTES = 2;

    public static ActionBarPopupWindow show(int type, Activity activity, INavigationLayout navigationLayout, FrameLayout contentView, View mentionButton, Theme.ResourcesProvider resourcesProvider, Runnable onRead) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout popupWindowLayout = new ActionBarPopupWindow.ActionBarPopupWindowLayout(activity);
        popupWindowLayout.setMinimumWidth(AndroidUtilities.dp(200));

        ActionBarMenuSubItem cell = new ActionBarMenuSubItem(activity, true,true, resourcesProvider);
        cell.setMinimumWidth(AndroidUtilities.dp(200));

        final String text;
        if (type == TYPE_REACTIONS) {
            text = getString(R.string.ReadAllReactions);
        } else if (type == TYPE_MENTIONS) {
            text = getString(R.string.ReadAllMentions);
        } else {
            text = getString(R.string.ReadAllPollVotes);
        }

        cell.setTextAndIcon(text, R.drawable.msg_seen);
        cell.setOnClickListener(view -> {
            if (onRead != null) {
                onRead.run();
            }
        });
        popupWindowLayout.addView(cell);

        ActionBarPopupWindow scrimPopupWindow = new ActionBarPopupWindow(popupWindowLayout, LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT);
        scrimPopupWindow.setPauseNotifications(true);
        scrimPopupWindow.setDismissAnimationDuration(220);
        scrimPopupWindow.setOutsideTouchable(true);
        scrimPopupWindow.setClippingEnabled(true);
        scrimPopupWindow.setAnimationStyle(R.style.PopupContextAnimation);
        scrimPopupWindow.setFocusable(true);
        popupWindowLayout.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000), View.MeasureSpec.AT_MOST));
        scrimPopupWindow.setInputMethodMode(ActionBarPopupWindow.INPUT_METHOD_NOT_NEEDED);
        scrimPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
        scrimPopupWindow.getContentView().setFocusableInTouchMode(true);

        PointF pf = new PointF();
        ViewPositionWatcher.computeCoordinatesInParent(mentionButton, contentView, pf);

        float x = pf.x + mentionButton.getWidth() - popupWindowLayout.getMeasuredWidth() + AndroidUtilities.dp(8);
        float y = pf.y - popupWindowLayout.getMeasuredHeight();
        if (AndroidUtilities.isTablet()) {
            View v = navigationLayout.getView();
            x += v.getX() + v.getPaddingLeft();
            y += v.getY() + v.getPaddingTop();
        }
        scrimPopupWindow.showAtLocation(contentView, Gravity.LEFT | Gravity.TOP, (int) x, (int) y);
        return scrimPopupWindow;
    }
}

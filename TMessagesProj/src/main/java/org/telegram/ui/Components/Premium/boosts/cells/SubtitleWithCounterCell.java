package com.tech.ayugram.ui.Components.Premium.boosts.cells;

import static com.tech.ayugram.messenger.AndroidUtilities.dp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.tech.ayugram.messenger.AndroidUtilities;
import com.tech.ayugram.messenger.LocaleController;
import com.tech.ayugram.ui.ActionBar.Theme;
import com.tech.ayugram.ui.Components.AnimatedTextView;
import com.tech.ayugram.ui.Components.CubicBezierInterpolator;
import com.tech.ayugram.ui.Components.LayoutHelper;

@SuppressLint("ViewConstructor")
public class SubtitleWithCounterCell extends com.tech.ayugram.ui.Cells.HeaderCell {

    private final AnimatedTextView counterTextView;

    public SubtitleWithCounterCell(@NonNull Context context, Theme.ResourcesProvider resourcesProvider) {
        super(context, resourcesProvider);

        counterTextView = new AnimatedTextView(context, true, true, true);
        counterTextView.setAnimationProperties(.45f, 0, 240, CubicBezierInterpolator.EASE_OUT_QUINT);
        counterTextView.setGravity(LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT);
        counterTextView.setTextSize(dp(15));
        counterTextView.setTypeface(AndroidUtilities.bold());
        counterTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, resourcesProvider));
        addView(counterTextView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, 24, (LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT) | Gravity.BOTTOM, 24, 0, 24, 0));
        setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, resourcesProvider));
    }

    public void updateCounter(boolean animated, int count) {
        CharSequence text = count <= 0 ? "" : LocaleController.formatPluralString("BoostingBoostsCountTitle", count, count);
        counterTextView.cancelAnimation();
        counterTextView.setText(text, animated);
    }
}

package org.telegram.ui.Stars;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import java.util.HashMap;
import java.util.Locale;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.Cells.SessionCell;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.ColoredImageSpan;

public class StarHelpers {

    public static HashMap<String, CombinedDrawable> cachedPlatformDrawables;

    public static CharSequence replaceUnderstood(CharSequence cs) {
        if (cs == null) return null;
        SpannableStringBuilder ssb;
        if (!(cs instanceof SpannableStringBuilder)) {
            ssb = new SpannableStringBuilder(cs);
        } else {
            ssb = (SpannableStringBuilder) cs;
        }

        final SpannableString ok = new SpannableString("👌");
        ok.setSpan(new ColoredImageSpan(R.drawable.filled_understood), 0, ok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        final SpannableString thumbs = new SpannableString("👍");
        thumbs.setSpan(new ColoredImageSpan(R.drawable.filled_reactions), 0, thumbs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        AndroidUtilities.replaceMultipleCharSequence("👌", ssb, ok);
        AndroidUtilities.replaceMultipleCharSequence("👍", ssb, thumbs);

        return ssb;
    }

    public static CharSequence percents(int commission) {
        float f = commission / 10.0f;
        if ((int) f == f) {
            return String.format(Locale.US, "%d%%", commission / 10);
        } else {
            return String.format(Locale.US, "%.1f%%", f);
        }
    }

    public static CombinedDrawable getPlatformDrawable(String platform) {
        if (cachedPlatformDrawables == null) {
            cachedPlatformDrawables = new HashMap<>();
        }
        CombinedDrawable drawable = cachedPlatformDrawables.get(platform);
        if (drawable == null) {
            cachedPlatformDrawables.put(platform, drawable = SessionCell.createDrawable(44, platform));
        }
        return drawable;
    }
}

package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;

import java.util.Date;

@SuppressLint("ViewConstructor")
public class TableCell extends FrameLayout {

    private final TextView fromTextView;
    private final TextView toTextView;
    private final TextView giftTextView;
    private final TextView reasonTextView;
    private final TextView dateTextView;

    private final BackupImageView fromImageView;
    private final BackupImageView toImageView;

    private final TextView fromNameTextView;
    private final TextView toNameTextView;
    private final TextView giftNameTextView;
    private final TextView reasonNameTextView;
    private final TextView dateNameTextView;

    private final Theme.ResourcesProvider resourcesProvider;
    private final Paint linePaint = new Paint();
    private final Path roundPath = new Path();
    private final RectF roundRect = new RectF();
    private FrameLayout fromFrameLayout;
    private FrameLayout toFrameLayout;
    private TableRow tableRow4;

    public TableCell(@NonNull Context context, Theme.ResourcesProvider resourcesProvider) {
        super(context);
        linePaint.setStyle(Paint.Style.STROKE);
        this.resourcesProvider = resourcesProvider;

        fromNameTextView = createTextView(LocaleController.getString(R.string.BoostingFrom), false);
        toNameTextView = createTextView(LocaleController.getString(R.string.BoostingTo), false);
        giftNameTextView = createTextView(LocaleController.getString(R.string.BoostingGift), false);
        reasonNameTextView = createTextView(LocaleController.getString(R.string.BoostingReason), false);
        dateNameTextView = createTextView(LocaleController.getString(R.string.BoostingDate), false);

        fromTextView = createTextView(true);
        toTextView = createTextView(true);
        giftTextView = createTextView(false);
        reasonTextView = createTextView(true);
        dateTextView = createTextView(false);

        fromImageView = new BackupImageView(context);
        fromImageView.setRoundRadius(AndroidUtilities.dp(12));
        toImageView = new BackupImageView(context);
        toImageView.setRoundRadius(AndroidUtilities.dp(12));

        TableRow tableRow1 = new TableRow(context);
        fromFrameLayout = new FrameLayout(context);
        fromFrameLayout.addView(fromImageView, LayoutHelper.createFrame(24, 24, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT), LocaleController.isRTL ? 0 : 12, 0, LocaleController.isRTL ? 12 : 0, 0));
        fromFrameLayout.addView(fromTextView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL, LocaleController.isRTL ? 0 : 29, 0, LocaleController.isRTL ? 29 : 0, 0));
        TableRow.LayoutParams lpFromFrameLayout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, LocaleController.isRTL ? 1 : 0);
        lpFromFrameLayout.gravity = Gravity.CENTER_VERTICAL;
        if (LocaleController.isRTL) {
            tableRow1.addView(fromFrameLayout, lpFromFrameLayout);
            tableRow1.addView(fromNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        } else {
            tableRow1.addView(fromNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow1.addView(fromFrameLayout, lpFromFrameLayout);
        }
        fromFrameLayout.setPadding(0, AndroidUtilities.dp(6), 0, AndroidUtilities.dp(6));

        TableRow tableRow2 = new TableRow(context);
        toFrameLayout = new FrameLayout(context);
        toFrameLayout.addView(toImageView, LayoutHelper.createFrame(24, 24, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT), LocaleController.isRTL ? 0 : 12, 0, LocaleController.isRTL ? 12 : 0, 0));
        toFrameLayout.addView(toTextView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL, LocaleController.isRTL ? 0 : 29, 0, LocaleController.isRTL ? 29 : 0, 0));

        TableRow.LayoutParams lpToFrameLayout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, LocaleController.isRTL ? 1 : 0);
        lpToFrameLayout.gravity = Gravity.CENTER_VERTICAL;
        if (LocaleController.isRTL) {
            tableRow2.addView(toFrameLayout, lpToFrameLayout);
            tableRow2.addView(toNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        } else {
            tableRow2.addView(toNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow2.addView(toFrameLayout, lpToFrameLayout);
        }
        toFrameLayout.setPadding(0, AndroidUtilities.dp(6), 0, AndroidUtilities.dp(6));

        TableRow tableRow3 = new TableRow(context);
        if (LocaleController.isRTL) {
            tableRow3.addView(giftTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
            tableRow3.addView(giftNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        } else {
            tableRow3.addView(giftNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow3.addView(giftTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        }

        tableRow4 = new TableRow(context);
        if (LocaleController.isRTL) {
            tableRow4.addView(reasonTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
            tableRow4.addView(reasonNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        } else {
            tableRow4.addView(reasonNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow4.addView(reasonTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        }

        TableRow tableRow5 = new TableRow(context);
        if (LocaleController.isRTL) {
            tableRow5.addView(dateTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
            tableRow5.addView(dateNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        } else {
            tableRow5.addView(dateNameTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow5.addView(dateTextView, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        }

        TableLayout tableLayout = new TableLayout(context) {
            @Override
            protected void dispatchDraw(Canvas canvas) {
                roundRect.set(0, 0, getWidth(), getHeight());
                roundPath.rewind();
                roundPath.addRoundRect(roundRect, AndroidUtilities.dp(6), AndroidUtilities.dp(6), Path.Direction.CW);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    canvas.clipPath(roundPath);
                }
                super.dispatchDraw(canvas);
                linePaint.setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_divider, resourcesProvider), Color.WHITE, 0.1f));
                linePaint.setStrokeWidth(AndroidUtilities.dp(1));
                float oneRow = getHeight() / (tableRow4.getVisibility() == VISIBLE ? 5f : 4f);
                for (int i = 1; i <= 4; i++) {
                    float y = oneRow * i;
                    canvas.drawLine(0, y, getWidth(), y, linePaint);
                }
                float x = LocaleController.isRTL ? dateTextView.getRight() : dateTextView.getLeft();
                canvas.drawLine(x, 0, x, getHeight(), linePaint);

                linePaint.setStrokeWidth(AndroidUtilities.dp(2));
                canvas.drawPath(roundPath, linePaint);
            }
        };
        tableLayout.addView(tableRow1);
        tableLayout.addView(tableRow2);
        tableLayout.addView(tableRow3);
        tableLayout.addView(tableRow4);
        tableLayout.addView(tableRow5);
        if (LocaleController.isRTL) {
            tableLayout.setColumnShrinkable(0, true);
        } else {
            tableLayout.setColumnShrinkable(1, true);
        }
        addView(tableLayout, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tableLayout.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), AndroidUtilities.dp(6));
                }
            });
            tableLayout.setClipToOutline(true);
        }
        setPaddingRelative(AndroidUtilities.dp(14), AndroidUtilities.dp(18), AndroidUtilities.dp(14), 0);
    }

    private TextView createTextView(boolean blueColor) {
        return createTextView(null, blueColor);
    }

    private TextView createTextView(String text, boolean blueColor) {
        TextView textView;

        if (blueColor) {
            textView = new LinkSpanDrawable.LinksTextView(getContext(), resourcesProvider);
            textView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, resourcesProvider));
        } else {
            textView = new TextView(getContext());
        }

        textView.setTextColor(Theme.getColor(blueColor ? Theme.key_dialogTextBlue : Theme.key_dialogTextBlack, resourcesProvider));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        if (!blueColor) {
            textView.setGravity(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT);
        }
        if (text != null) {
            textView.setTypeface(AndroidUtilities.bold());
            textView.setText(text);
            textView.setBackgroundColor(Theme.getColor(Theme.key_graySection, resourcesProvider));
            textView.setPadding(AndroidUtilities.dp(LocaleController.isRTL ? 32 : 12), AndroidUtilities.dp(11), AndroidUtilities.dp(LocaleController.isRTL ? 12 : 32), AndroidUtilities.dp(11));
        } else {
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setPadding(AndroidUtilities.dp(14), 0, AndroidUtilities.dp(14), 0);
        }
        return textView;
    }
}

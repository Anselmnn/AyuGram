package org.telegram.ui.Components.Premium.boosts;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet;
import org.telegram.ui.Components.Premium.boosts.adapters.GiftInfoAdapter;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.LaunchActivity;

import java.util.concurrent.atomic.AtomicBoolean;

public class GiftInfoBottomSheet extends BottomSheetWithRecyclerListView {

    public static void show(BaseFragment fragment, String slug, Browser.Progress progress) {
        if (progress != null) {
            progress.init();
            progress.end();
        }
    }

    public static void show(BaseFragment fragment, String slug) {
        show(fragment, slug, null);
    }

    public static boolean handleIntent(Intent intent, Browser.Progress progress) {
        return false;
    }

    private GiftInfoAdapter adapter;

    @Override
    public void onViewCreated(FrameLayout containerView) {
        super.onViewCreated(containerView);
        Bulletin.addDelegate(container, new Bulletin.Delegate() {
            @Override
            public int getTopOffset(int tag) {
                return AndroidUtilities.statusBarHeight;
            }
        });
    }

    @Override
    protected CharSequence getTitle() {
        return "";
    }

    @Override
    protected RecyclerListView.SelectionAdapter createAdapter(RecyclerListView listView) {
        return adapter = new GiftInfoAdapter(resourcesProvider) {
            @Override
            protected void dismiss() {
                GiftInfoBottomSheet.this.dismiss();
            }

            @Override
            protected void afterCodeApplied() {

            }

            @Override
            protected void onObjectClicked(TLObject object) {
                dismiss();
            }

            @Override
            protected void onHiddenLinkClicked() {

            }
        };
    }
}

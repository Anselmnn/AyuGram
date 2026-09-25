package com.tech.ayugram.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.tech.ayugram.ui.ActionBar.BaseFragment;
import com.tech.ayugram.ui.Components.SizeNotifierFrameLayout;

public class EmptyBaseFragment extends BaseFragment {

    @Override
    public View createView(Context context) {
        return fragmentView = new SizeNotifierFrameLayout(context);
    }

}

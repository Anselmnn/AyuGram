package com.tech.ayugram.ui.Components.Premium.boosts;

import android.app.Activity;

import com.tech.ayugram.ui.ActionBar.BaseFragment;
import com.tech.ayugram.ui.ActionBar.Theme;
import com.tech.ayugram.ui.Stories.DarkThemeResourceProvider;
import com.tech.ayugram.ui.WrappedResourceProvider;

public class DarkFragmentWrapper extends BaseFragment {

    private final BaseFragment parentFragment;

    DarkFragmentWrapper(BaseFragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    public boolean isLightStatusBar() {
        return false;
    }

    @Override
    public Activity getParentActivity() {
        return parentFragment.getParentActivity();
    }

    @Override
    public Theme.ResourcesProvider getResourceProvider() {
        return new WrappedResourceProvider(new DarkThemeResourceProvider());
    }

    @Override
    public boolean presentFragment(BaseFragment fragment) {
        return false;
    }
}

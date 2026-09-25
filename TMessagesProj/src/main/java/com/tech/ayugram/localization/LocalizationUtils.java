package com.tech.ayugram.localization;

import android.content.Context;
import android.text.TextUtils;

public class LocalizationUtils {
    public static final String DEFAULT_LOCALIZATION = "localization.json";

    public static String getLocalizationAsset(String locale) {
        if (TextUtils.isEmpty(locale)) {
            return DEFAULT_LOCALIZATION;
        }
        return "localization_" + locale + ".json";
    }
}

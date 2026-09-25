package com.tech.ayugram.localization;

import android.content.Context;
import android.text.TextUtils;
import java.util.Locale;

public class LocalizationUtils {
    public static final String DEFAULT_LOCALIZATION = "localization.json";

    public static String getLocalizationAsset(Locale locale) {
        if (locale == null) {
            return DEFAULT_LOCALIZATION;
        }
        return "localization_" + locale.getLanguage() + "_" + locale.getCountry() + ".json";
    }

    public static String getLocalizationAsset(String locale) {
        if (TextUtils.isEmpty(locale)) {
            return DEFAULT_LOCALIZATION;
        }
        return "localization_" + locale + ".json";
    }
}

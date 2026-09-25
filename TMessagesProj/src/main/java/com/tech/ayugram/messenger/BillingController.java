package com.tech.ayugram.messenger;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import com.tech.ayugram.messenger.AccountInstance;

import com.tech.ayugram.messenger.utils.BillingUtilities;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.TLRPC;
import com.tech.ayugram.ui.ActionBar.AlertDialog;
import com.tech.ayugram.ui.ActionBar.BaseFragment;
import com.tech.ayugram.ui.LaunchActivity;
import com.tech.ayugram.ui.LoginActivity;
import com.tech.ayugram.ui.PremiumPreviewFragment;
import com.tech.ayugram.messenger.AndroidUtilities;
import com.tech.ayugram.messenger.ApplicationLoader;
import com.tech.ayugram.messenger.LocaleController;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Phase 2: Stub implementation of BillingController (billing dependency removed)
 * All methods are no-ops that return default values.
 */
public class BillingController {
    public final static String PREMIUM_PRODUCT_ID = "telegram_premium";

    @Nullable
    public static Object PREMIUM_PRODUCT_DETAILS = null;

    private static BillingController instance;

    public static boolean billingClientEmpty = true;

    private final Map<String, Consumer<Object>> resultListeners = new HashMap<>();
    private final Set<String> requestingTokens = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<String, Integer> currencyExpMap = new HashMap<>();
    private String lastPremiumTransaction;
    private String lastPremiumToken;
    private boolean isDisconnected;
    private Runnable onCanceled;

    public static BillingController getInstance() {
        if (instance == null) {
            instance = new BillingController(ApplicationLoader.applicationContext);
        }
        return instance;
    }

    private BillingController(Context ctx) {
        // Stub: no billing client initialization
    }

    public void setOnCanceled(Runnable onCanceled) {
        this.onCanceled = onCanceled;
    }

    public String getLastPremiumTransaction() {
        return lastPremiumTransaction;
    }

    public String getLastPremiumToken() {
        return lastPremiumToken;
    }

    public String formatCurrency(long amount, String currency) {
        return formatCurrency(amount, currency, getCurrencyExp(currency));
    }

    public String formatCurrency(long amount, String currency, int exp) {
        return formatCurrency(amount, currency, exp, false);
    }

    public String formatCurrency(long amount, String currency, int exp, boolean rounded) {
        if (amount == 0) {
            return "0";
        }
        try {
            NumberFormat format = NumberFormat.getCurrencyInstance(LocaleController.getDefaultLocale());
            format.setCurrency(Currency.getInstance(currency));
            format.setMinimumFractionDigits(exp);
            format.setMaximumFractionDigits(exp);
            return format.format(amount / Math.pow(10, exp));
        } catch (Exception e) {
            return String.valueOf(amount);
        }
    }

    public int getCurrencyExp(String currency) {
        Integer exp = currencyExpMap.get(currency);
        if (exp != null) {
            return exp;
        }
        try {
            Currency c = Currency.getInstance(currency);
            return c.getDefaultFractionDigits();
        } catch (Exception e) {
            return 2;
        }
    }

    public boolean isReady() {
        return false;
    }

    public void whenSetuped(Runnable runnable) {
        if (runnable != null) {
            AndroidUtilities.runOnUIThread(runnable);
        }
    }

    public void queryProductDetails(List<Object> productQueries, Consumer<Object> callback) {
        if (callback != null) {
            AndroidUtilities.runOnUIThread(() -> callback.accept(null));
        }
    }

    public void addResultListener(String productId, Consumer<Object> listener) {
        resultListeners.put(productId, listener);
    }

    public void launchBillingFlow(Activity activity, AccountInstance accountInstance, int purpose, List<?> params) {
        // Stub: no billing flow
    }

    public void launchBillingFlow(Activity activity, AccountInstance accountInstance, int purpose, Object params) {
        // Stub: no billing flow
    }

    public String getResponseCodeString(int code) {
        return "BILLING_ERROR_" + code;
    }

    public void queryPurchases(int productType, Consumer<Object> callback) {
        if (callback != null) {
            AndroidUtilities.runOnUIThread(() -> callback.accept(null));
        }
    }
}
#!/usr/bin/env python3
"""
Create a completely new PremiumPreviewFragment.java with all Phase 2 fixes applied.
Reads the original, applies all changes, writes the complete fixed file.
"""

# Read the original file
with open('/tmp/PremiumPreviewFragment_original.java', 'r') as f:
    original = f.read()

# Apply package rename and import changes
content = original.replace('package org.telegram.ui;', 'package com.tech.ayugram.ui;')
content = content.replace('import org.telegram.', 'import com.tech.ayugram.')
content = content.replace('import static org.telegram.', 'import static com.tech.ayugram.')

# Comment out billing imports
import_replacements = [
    ('import com.android.billingclient.api.BillingClient;',
     '// import com.android.billingclient.api.BillingClient; (Phase 2)'),
    ('import com.android.billingclient.api.BillingFlowParams;',
     '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)'),
    ('import com.android.billingclient.api.ProductDetails;',
     '// import com.android.billingclient.api.ProductDetails; (Phase 2)'),
    ('import com.android.billingclient.api.Purchase;',
     '// import com.android.billingclient.api.Purchase; (Phase 2)'),
    ('import com.tech.ayugram.messenger.BillingController;',
     '// import com.tech.ayugram.messenger.BillingController; (Phase 2)'),
    ('import com.tech.ayugram.messenger.MediaDataController;',
     '// import com.tech.ayugram.messenger.MediaDataController; (Phase 2)'),
]

for old, new in import_replacements:
    content = content.replace(old, new)

# 1. Stub all 6 buyPremium methods
buyPremium_methods = [
    ('''    public static void buyPremium(BaseFragment fragment) {
        buyPremium(fragment, "settings");
    }''',
     '''    public static void buyPremium(BaseFragment fragment) {
        // Phase 2: Billing removed
    }'''),
    ('''    public static void buyPremium(BaseFragment fragment, String source) {
        buyPremium(fragment, null, source, true);
    }''',
     '''    public static void buyPremium(BaseFragment fragment, String source) {
        // Phase 2: Billing removed
    }'''),
    ('''    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {
        buyPremium(fragment, null, source, forcePremium);
    }''',
     '''    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {
        // Phase 2: Billing removed
    }'''),
    ('''    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {
        buyPremium(fragment, tier, source, true);
    }''',
     '''    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {
        // Phase 2: Billing removed
    }'''),
    ('''    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {
        buyPremium(fragment, tier, source, forcePremium, null);
    }''',
     '''    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {
        // Phase 2: Billing removed
    }'''),
    ('''    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams.SubscriptionUpdateParams updateParams) {
        if (BuildVars.IS_BILLING_UNAVAILABLE) {
            if (fragment == null) {
                new PremiumNotAvailableBottomSheet(fragment).show();
            } else {
                fragment.showDialog(new PremiumNotAvailableBottomSheet(fragment));
            }
            return;
        }
        final int account = fragment == null ? UserConfig.selectedAccount : fragment.getCurrentAccount();
        if (MessagesController.getInstance(account).isFrozen()) {
            AccountFrozenAlert.show(account);
            return;
        }

        if (tier == null) {
            forcePremium = true;
            TLRPC.TL_help_premiumPromo promo = MediaDataController.getInstance(account).getPremiumPromo();
            if (promo != null) {
                for (TLRPC.TL_premiumSubscriptionOption option : promo.period_options) {
                    if (option.months == 1) {
                        tier = new SubscriptionTier(option);
                    } else if (option.months == 12) {
                        tier = new SubscriptionTier(option);
                        break;
                    }
                }
            }
        SubscriptionTier selectedTier = tier;

        PremiumPreviewFragment.sentPremiumButtonClick();

        if (BuildVars.useInvoiceBilling()) {
            final Activity activity = fragment != null ? fragment.getParentActivity() : LaunchActivity.instance;
            if (activity instanceof LaunchActivity) {
                final LaunchActivity launchActivity = (LaunchActivity) activity;
                if (selectedTier == null || selectedTier.subscriptionOption == null || selectedTier.subscriptionOption.bot_url == null) {
                    final MessagesController messagesController = MessagesController.getInstance(account);
                    if (!TextUtils.isEmpty(messagesController.premiumBotUsername)) {
                        launchActivity.setNavigateToPremiumBot(true);
                        launchActivity.onNewIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/" + messagesController.premiumBotUsername + "?start=" + source)), (Browser.Progress) null);
                    } else if (!TextUtils.isEmpty(messagesController.premiumInvoiceSlug)) {
                        launchActivity.onNewIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/$" + messagesController.premiumInvoiceSlug)), (Browser.Progress) null);
                    }
                } else {
                    final Uri uri = Uri.parse(selectedTier.subscriptionOption.bot_url);
                    if (uri.getHost().equals("t.me")) {
                        if (!uri.getPath().startsWith("/$") && !uri.getPath().startsWith("/invoice/")) {
                            launchActivity.setNavigateToPremiumBot(true);
                        }
                    }
                    Browser.openUrl(launchActivity, tier.subscriptionOption.bot_url);
                }
            }
            return;
        }

        if (BillingController.PREMIUM_PRODUCT_DETAILS == null) {
            return;
        }

        List<ProductDetails.SubscriptionOfferDetails> offerDetails = BillingController.PREMIUM_PRODUCT_DETAILS.getSubscriptionOfferDetails();
        if (offerDetails.isEmpty()) {
            return;
        }

        if (selectedTier.getGooglePlayProductDetails() == null) {
            selectedTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);
        }

        if (selectedTier.getOfferDetails() == null) {
            return;
        }

        boolean finalForcePremium = forcePremium;
        BillingController.getInstance().queryPurchases(BillingClient.ProductType.SUBS, (billingResult1, list) -> AndroidUtilities.runOnUIThread(() -> {
            if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                Runnable onSuccess = () -> {
                    if (fragment instanceof PremiumPreviewFragment) {
                        PremiumPreviewFragment premiumPreviewFragment = (PremiumPreviewFragment) fragment;
                        if (finalForcePremium) {
                            premiumPreviewFragment.setForcePremium();
                        }
                        premiumPreviewFragment.getMediaDataController().loadPremiumPromo(false);

                        premiumPreviewFragment.listView.smoothScrollToPosition(0);
                    } else {
                        final PremiumPreviewFragment previewFragment = new PremiumPreviewFragment(null);
                        if (finalForcePremium) {
                            previewFragment.setForcePremium();
                        }
                        if (fragment != null) {
                            fragment.presentFragment(previewFragment);
                        } else {
                            final BaseFragment lastFragment = LaunchActivity.getSafeLastFragment();
                            if (lastFragment != null) {
                                lastFragment.presentFragment(previewFragment);
                            }
                        }
                    }
                    if (fragment != null && fragment.getParentActivity() instanceof LaunchActivity) {
                        try {
                            fragment.getFragmentView().performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
                        } catch (Exception ignored) {}
                        ((LaunchActivity) fragment.getParentActivity()).getFireworksOverlay().start();
                    }
                };
                if (list != null && !list.isEmpty() && !UserConfig.getInstance(account).isPremium()) {
                    for (Purchase purchase : list) {
                        if (purchase.getProducts().contains(BillingController.PREMIUM_PRODUCT_ID)) {
                            TLRPC.TL_payments_assignPlayMarketTransaction req = new TLRPC.TL_payments_assignPlayMarketTransaction();
                            req.receipt = new TLRPC.TL_dataJSON();
                            req.receipt.data = purchase.getOriginalJson();
                            TLRPC.TL_inputStorePaymentPremiumSubscription purpose = new TLRPC.TL_inputStorePaymentPremiumSubscription();
                            purpose.restore = true;
                            if (updateParams != null) {
                                purpose.upgrade = true;
                            }
                            req.purpose = purpose;
                            ConnectionsManager.getInstance(account).sendRequest(req, (response, error) -> {
                                if (response instanceof TLRPC.Updates) {
                                    MessagesController.getInstance(account).processUpdates((TLRPC.Updates) response, false);

                                    AndroidUtilities.runOnUIThread(onSuccess);
                                } else if (error != null) {
                                    AndroidUtilities.runOnUIThread(() -> AlertsCreator.processError(account, error, fragment, req));
                                }
                            }, ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagInvokeAfter);


                            return;
                        }
                    }
                }

                BillingController.getInstance().addResultListener(BillingController.PREMIUM_PRODUCT_ID, billingResult -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        AndroidUtilities.runOnUIThread(onSuccess);
                    }
                });

                TLRPC.TL_payments_canPurchaseStore req = new TLRPC.TL_payments_canPurchaseStore();
                TLRPC.TL_inputStorePaymentPremiumSubscription purpose = new TLRPC.TL_inputStorePaymentPremiumSubscription();
                if (updateParams != null) {
                    purpose.upgrade = true;
                }
                req.purpose = purpose;
                ConnectionsManager.getInstance(account).sendRequest(req, (response, error) -> {
                    AndroidUtilities.runOnUIThread(() -> {
                        if (response instanceof TLRPC.TL_boolTrue) {
                            final Activity activity = fragment != null ? fragment.getParentActivity() : AndroidUtilities.getActivity();
                            BillingController.getInstance().launchBillingFlow(activity, fragment.getAccountInstance(), purpose, Collections.singletonList(
                                    BillingFlowParams.ProductDetailsParams.newBuilder()
                                            .setProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS)
                                            .setOfferToken(selectedTier.getOfferDetails().getOfferToken())
                                            .build()
                            ), updateParams, false);
                        } else {
                            AlertsCreator.processError(account, error, fragment, req);
                        }
                    });
                });
            }
        }));
    }''',
     '''    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams.SubscriptionUpdateParams updateParams) {
        // Phase 2: Billing removed
    }'''),
]

for old, new in buyPremium_methods:
    content = content.replace(old, new)

# 2. Stub getPremiumButtonText
content = content.replace(
    '''    public static String getPremiumButtonText(int currentAccount, SubscriptionTier tier) {
        if (BuildVars.IS_BILLING_UNAVAILABLE) {
            return getString(R.string.SubscribeToPremiumNotAvailable);
        }

        int stringResId = R.string.SubscribeToPremium;
        if (tier == null) {
            if (BuildVars.useInvoiceBilling()) {
                TLRPC.TL_help_premiumPromo premiumPromo = MediaDataController.getInstance(currentAccount).getPremiumPromo();
                if (premiumPromo != null) {
                    TLRPC.TL_premiumSubscriptionOption selectedOption = null;
                    for (TLRPC.TL_premiumSubscriptionOption option : premiumPromo.period_options) {
                        if (option.months == 12) {
                            selectedOption = option;
                            break;
                        } else if (selectedOption == null && option.months == 1) {
                            selectedOption = option;
                        }
                    }

                    if (selectedOption == null) {
                        return getString(R.string.SubscribeToPremiumNoPrice);
                    }

                    final String price;
                    if (selectedOption.months == 12) {
                        if (MessagesController.getInstance(currentAccount).showAnnualPerMonth) {
                            price = BillingController.getInstance().formatCurrency(selectedOption.amount / 12, selectedOption.currency);
                        } else {
                            stringResId = R.string.SubscribeToPremiumPerYear;
                            price = BillingController.getInstance().formatCurrency(selectedOption.amount, selectedOption.currency);
                        }
                    } else {
                        price = BillingController.getInstance().formatCurrency(selectedOption.amount, selectedOption.currency);
                    }

                    return LocaleController.formatString(stringResId, price);
                }

                return getString(R.string.SubscribeToPremiumNoPrice);
            }

            String price = null;
            if (BillingController.PREMIUM_PRODUCT_DETAILS != null) {
                List<ProductDetails.SubscriptionOfferDetails> details = BillingController.PREMIUM_PRODUCT_DETAILS.getSubscriptionOfferDetails();
                if (!details.isEmpty()) {
                    ProductDetails.SubscriptionOfferDetails offerDetails = details.get(0);
                    for (ProductDetails.PricingPhase phase : offerDetails.getPricingPhases().getPricingPhaseList()) {
                        if (phase.getBillingPeriod().equals("P1M")) { // Once per month
                            price = phase.getFormattedPrice();
                        } else if (phase.getBillingPeriod().equals("P1Y")) { // Once per year
                            if (MessagesController.getInstance(currentAccount).showAnnualPerMonth) {
                                price = BillingController.getInstance().formatCurrency(phase.getPriceAmountMicros() / 12L, phase.getPriceCurrencyCode(), 6);
                            } else {
                                stringResId = R.string.SubscribeToPremiumPerYear;
                                price = BillingController.getInstance().formatCurrency(phase.getPriceAmountMicros(), phase.getPriceCurrencyCode(), 6);
                            }
                            break;
                        }
                    }
                }

            if (price == null) {
                return getString(R.string.Loading);
            }

            return LocaleController.formatString(stringResId, price);
        } else {
            if (!BuildVars.useInvoiceBilling() && tier.getOfferDetails() == null) {
                return getString(R.string.Loading);
            }
            final boolean isPremium = UserConfig.getInstance(currentAccount).isPremium();
            final boolean isManyYearsTier = tier.getMonths() > 12 && tier.getMonths() % 12 == 0;
            final boolean isYearTier = tier.getMonths() == 12;
            String price = isYearTier ? tier.getFormattedPricePerYear() : tier.getFormattedPricePerMonth();
            final int resId;
            if (isPremium) {
                resId = isYearTier ? R.string.UpgradePremiumPerYear : R.string.UpgradePremiumPerMonth;
            } else {
                if (isYearTier) {
                    if (MessagesController.getInstance(currentAccount).showAnnualPerMonth) {
                        resId = R.string.SubscribeToPremium;
                        price = tier.getFormattedPricePerMonth();
                    } else {
                        resId = R.string.SubscribeToPremiumPerYear;
                        price = tier.getFormattedPrice();
                    }
                } else if (isManyYearsTier) {
                    if (MessagesController.getInstance(currentAccount).showAnnualPerMonth) {
                        resId = R.string.SubscribeToPremium;
                        price = tier.getFormattedPricePerMonth();
                    } else {
                        return LocaleController.formatString(
                            R.string.SubscribeToPremiumPerCustom,
                            tier.getFormattedPrice(),
                            LocaleController.formatPluralString("Years", tier.getMonths() / 12)
                        );
                    }
                } else {
                    resId = R.string.SubscribeToPremium;
                    price = tier.getFormattedPricePerMonth();
                }
            }
            return LocaleController.formatString(resId, price);
        }
    }''',
    '''    public static String getPremiumButtonText(int currentAccount, SubscriptionTier tier) {
        // Phase 2: Billing removed - stub
        return "Premium";
    }''')

# 3. Replace SubscriptionTier class entirely
old_subscription_tier = '''    public final static class SubscriptionTier {
        public final TLRPC.TL_premiumSubscriptionOption subscriptionOption;
        private int discount;
        private long pricePerMonth;
        private long pricePerYear;

        private long pricePerYearRegular;
        private ProductDetails googlePlayProductDetails;
        private ProductDetails.SubscriptionOfferDetails offerDetails;

        public int yOffset;

        public SubscriptionTier(TLRPC.TL_premiumSubscriptionOption subscriptionOption) {
            this.subscriptionOption = subscriptionOption;
        }

        public ProductDetails getGooglePlayProductDetails() {
            return googlePlayProductDetails;
        }

        public ProductDetails.SubscriptionOfferDetails getOfferDetails() {
            checkOfferDetails();
            return offerDetails;
        }

        public void setGooglePlayProductDetails(ProductDetails googlePlayProductDetails) {
            this.googlePlayProductDetails = googlePlayProductDetails;
        }

        public void setPricePerYearRegular(long pricePerYearRegular) {
            this.pricePerYearRegular = pricePerYearRegular;
        }

        public int getMonths() {
            return subscriptionOption.months;
        }

        public int getDiscount() {
            if (discount == 0) {
                if (getPricePerMonth() == 0) {
                    return 0;
                }

                if (pricePerYearRegular != 0) {
                    discount = (int) ((1.0 - getPricePerYear() / (double) pricePerYearRegular) * 100);

                    if (discount == 0) {
                        discount = -1;
                    }
                }
            }
            return discount;
        }

        public long getPricePerYear() {
            if (pricePerYear == 0) {
                long price = getPrice();
                if (price != 0) {
                    pricePerYear = (long) ((double) price / subscriptionOption.months * 12);
                }
            }
            return pricePerYear;
        }

        public long getPricePerMonth() {
            if (pricePerMonth == 0) {
                long price = getPrice();
                if (price != 0) {
                    pricePerMonth = price / subscriptionOption.months;
                }
            }
            return pricePerMonth;
        }

        public String getFormattedPricePerYearRegular() {
            if (BuildVars.useInvoiceBilling() || subscriptionOption.store_product == null) {
                return BillingController.getInstance().formatCurrency(pricePerYearRegular, getCurrency());
            }

            return googlePlayProductDetails == null ? "" : BillingController.getInstance().formatCurrency(pricePerYearRegular, getCurrency(), 6);
        }

        public String getFormattedPricePerYear() {
            if (BuildVars.useInvoiceBilling() || subscriptionOption.store_product == null) {
                return BillingController.getInstance().formatCurrency(getPricePerYear(), getCurrency());
            }

            return googlePlayProductDetails == null ? "" : BillingController.getInstance().formatCurrency(getPricePerYear(), getCurrency(), 6);
        }

        public String getFormattedPricePerMonth() {
            if (BuildVars.useInvoiceBilling() || subscriptionOption.store_product == null) {
                return BillingController.getInstance().formatCurrency(getPricePerMonth(), getCurrency());
            }

            return googlePlayProductDetails == null ? "" : BillingController.getInstance().formatCurrency(getPricePerMonth(), getCurrency(), 6);
        }

        public String getFormattedPrice() {
            if (BuildVars.useInvoiceBilling() || subscriptionOption.store_product == null) {
                return BillingController.getInstance().formatCurrency(getPrice(), getCurrency());
            }

            return googlePlayProductDetails == null ? "" : BillingController.getInstance().formatCurrency(getPrice(), getCurrency(), 6);
        }

        public long getPrice() {
            if (BuildVars.useInvoiceBilling() || subscriptionOption.store_product == null) {
                return subscriptionOption.amount;
            }
            if (googlePlayProductDetails == null) {
                return 0;
            }
            checkOfferDetails();
            return offerDetails == null ? 0 : offerDetails.getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros();
        }

        public String getCurrency() {
            if (BuildVars.useInvoiceBilling() || subscriptionOption.store_product == null) {
                return subscriptionOption.currency;
            }
            if (googlePlayProductDetails == null) {
                return "";
            }
            checkOfferDetails();
            return offerDetails == null ? "" : offerDetails.getPricingPhases().getPricingPhaseList().get(0).getPriceCurrencyCode();
        }

        private void checkOfferDetails() {
            if (googlePlayProductDetails == null) {
                return;
            }

            if (offerDetails == null) {
                for (ProductDetails.SubscriptionOfferDetails details : googlePlayProductDetails.getSubscriptionOfferDetails()) {
                    String period = details.getPricingPhases().getPricingPhaseList().get(0).getBillingPeriod();
                    if (getMonths() == 12 ? period.equals("P1Y") : period.equals(String.format(Locale.ROOT, "P%dM", getMonths()))) {
                        offerDetails = details;
                        break;
                    }
                }
            }
        }
    }'''

new_subscription_tier = '''    public final static class SubscriptionTier {
        public final TLRPC.TL_premiumSubscriptionOption subscriptionOption;
        private int discount;
        private long pricePerMonth;
        private long pricePerYear;

        private long pricePerYearRegular;

        // Phase 2: Billing removed - no ProductDetails
        // private ProductDetails googlePlayProductDetails;
        // private ProductDetails.SubscriptionOfferDetails offerDetails;

        public int yOffset;

        public SubscriptionTier(TLRPC.TL_premiumSubscriptionOption subscriptionOption) {
            this.subscriptionOption = subscriptionOption;
        }

        // Phase 2: Billing removed
        // public ProductDetails getGooglePlayProductDetails() { return googlePlayProductDetails; }
        // public ProductDetails.SubscriptionOfferDetails getOfferDetails() { return offerDetails; }
        // public void setGooglePlayProductDetails(ProductDetails googlePlayProductDetails) { this.googlePlayProductDetails = googlePlayProductDetails; }

        public void setPricePerYearRegular(long pricePerYearRegular) {
            this.pricePerYearRegular = pricePerYearRegular;
        }

        public int getMonths() {
            return subscriptionOption.months;
        }

        public int getDiscount() {
            if (discount == 0) {
                if (getPricePerMonth() == 0) {
                    return 0;
                }

                if (pricePerYearRegular != 0) {
                    discount = (int) ((1.0 - getPricePerYear() / (double) pricePerYearRegular) * 100);

                    if (discount == 0) {
                        discount = -1;
                    }
                }
            }
            return discount;
        }

        public long getPricePerYear() {
            if (pricePerYear == 0) {
                long price = getPrice();
                if (price != 0) {
                    pricePerYear = (long) ((double) price / subscriptionOption.months * 12);
                }
            }
            return pricePerYear;
        }

        public long getPricePerMonth() {
            if (pricePerMonth == 0) {
                long price = getPrice();
                if (price != 0) {
                    pricePerMonth = price / subscriptionOption.months;
                }
            }
            return pricePerMonth;
        }

        public String getFormattedPricePerYearRegular() {
            return "0";
        }

        public String getFormattedPricePerYear() {
            return "0";
        }

        public String getFormattedPricePerMonth() {
            return "0";
        }

        public String getFormattedPrice() {
            return "0";
        }

        public long getPrice() {
            return subscriptionOption.amount;
        }

        public String getCurrency() {
            return subscriptionOption.currency;
        }

        // Phase 2: Billing removed - no offerDetails
        // private void checkOfferDetails() { }
    }'''

content = content.replace(old_subscription_tier, new_subscription_tier)

# 4. Comment out observer code
content = content.replace(
    'NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {',
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {'
)

# 5. Comment out remaining BillingController references in code
code_replacements = [
    # updatePremiumTiers method - first else if block
    ('''            } else if (!BuildVars.useInvoiceBilling() && currentSubscriptionTier != null && !Objects.equals(BillingController.getInstance().getLastPremiumTransaction(),
                    currentSubscriptionTier.subscriptionOption != null ? currentSubscriptionTier.subscriptionOption.transaction != null ?
                            currentSubscriptionTier.subscriptionOption.transaction.replaceAll(TRANSACTION_PATTERN, "$1") : null : null) ||
                                currentSubscriptionTier != null && currentSubscriptionTier.getMonths() == 12) {
                subscriptionTiers.clear();
                currentSubscriptionTier = null;
            }''',
     '''            // Phase 2: Billing removed
            // } else if (!BuildVars.useInvoiceBilling() && currentSubscriptionTier != null && !Objects.equals(BillingController.getInstance().getLastPremiumTransaction(),
            //         currentSubscriptionTier.subscriptionOption != null ? currentSubscriptionTier.subscriptionOption.transaction != null ?
            //                 currentSubscriptionTier.subscriptionOption.transaction.replaceAll(TRANSACTION_PATTERN, "$1") : null : null) ||
            //                 currentSubscriptionTier != null && currentSubscriptionTier.getMonths() == 12) {
            //     subscriptionTiers.clear();
            //     currentSubscriptionTier = null;
            // }'''),

    # Second else if block
    ('''            } else if (BillingController.getInstance().isReady() && BillingController.PREMIUM_PRODUCT_DETAILS != null) {
                long pricePerMonthMaxStore = 0;

                boolean hasSomeLoaded = false;
                for (SubscriptionTier subscriptionTier : subscriptionTiers) {
                    // Phase 2: Billing removed
                    // subscriptionTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);

                    if (subscriptionTier.getPricePerYear() > pricePerMonthMaxStore) {
                        pricePerMonthMaxStore = subscriptionTier.getPricePerYear();
                    }

                    if (subscriptionTier.getOfferDetails() != null) {
                        hasSomeLoaded = true;
                    }
                }

                if (hasSomeLoaded) {
                    for (int i = 0; i < subscriptionTiers.size(); ++i) {
                        final SubscriptionTier tier = subscriptionTiers.get(i);
                        if (tier.getOfferDetails() == null) {
                            subscriptionTiers.remove(i);
                            --i;
                        }
                    }
                }

                for (SubscriptionTier subscriptionTier : subscriptionTiers) {
                    subscriptionTier.setPricePerYearRegular(pricePerMonthMaxStore);
                }
            }''',
     '''            // Phase 2: Billing removed
            // } else if (BillingController.getInstance().isReady() && BillingController.PREMIUM_PRODUCT_DETAILS != null) {
            //     long pricePerMonthMaxStore = 0;
            //
            //     boolean hasSomeLoaded = false;
            //     for (SubscriptionTier subscriptionTier : subscriptionTiers) {
            //         // Phase 2: Billing removed
            //         // subscriptionTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);
            //
            //         if (subscriptionTier.getPricePerYear() > pricePerMonthMaxStore) {
            //             pricePerMonthMaxStore = subscriptionTier.getPricePerYear();
            //         }
            //
            //         if (subscriptionTier.getOfferDetails() != null) {
            //             hasSomeLoaded = true;
            //         }
            //     }
            //
            //     if (hasSomeLoaded) {
            //         for (int i = 0; i < subscriptionTiers.size(); ++i) {
            //             final SubscriptionTier tier = subscriptionTiers.get(i);
            //             if (tier.getOfferDetails() == null) {
            //                 subscriptionTiers.remove(i);
            //                 --i;
            //             }
            //         }
            //     }
            //
            //     for (SubscriptionTier subscriptionTier : subscriptionTiers) {
            //         subscriptionTier.setPricePerYearRegular(pricePerMonthMaxStore);
            //     }
            // }'''),

    # updateButtonText method - first if block
    ('''        if (!BuildVars.useInvoiceBilling() && (!BillingController.getInstance().isReady() || subscriptionTiers.isEmpty() || selectedTierIndex >= subscriptionTiers.size() || subscriptionTiers.get(selectedTierIndex).googlePlayProductDetails == null)) {
            premiumButtonView.setButton(getString(R.string.Loading), null, animated);
            buttonContainerInternal.setOnClickListener(v -> {});
            premiumButtonView.setFlickerDisabled(true);
            return;
        }''',
     '''        // Phase 2: Billing removed
        // if (!BuildVars.useInvoiceBilling() && (!BillingController.getInstance().isReady() || subscriptionTiers.isEmpty() || selectedTierIndex >= subscriptionTiers.size() || subscriptionTiers.get(selectedTierIndex).googlePlayProductDetails == null)) {
        //     premiumButtonView.setButton(getString(R.string.Loading), null, animated);
        //     buttonContainerInternal.setOnClickListener(v -> {});
        //     premiumButtonView.setFlickerDisabled(true);
        //     return;
        // }'''),

    # onClickListener with BillingFlowParams
    ('''            buttonContainerInternal.setOnClickListener(v -> {
                SubscriptionTier tier = subscriptionTiers.get(selectedTierIndex);
                BillingFlowParams.SubscriptionUpdateParams updateParams = null;
                if (currentSubscriptionTier != null && currentSubscriptionTier.subscriptionOption != null && currentSubscriptionTier.subscriptionOption.transaction != null) {
                    updateParams = BillingFlowParams.SubscriptionUpdateParams.newBuilder()
                            // Phase 2: Billing removed
                            // .setOldPurchaseToken(BillingController.getInstance().getLastPremiumToken())
//                            .setReplaceProrationMode(BillingFlowParams.ProrationMode.IMMEDIATE_AND_CHARGE_FULL_PRICE)
                            .setSubscriptionReplacementMode(BillingFlowParams.SubscriptionUpdateParams.ReplacementMode.CHARGE_FULL_PRICE)
                            .build();
                }
                buyPremium(this, tier, "settings", true, updateParams);
            });''',
     '''            buttonContainerInternal.setOnClickListener(v -> {
                SubscriptionTier tier = subscriptionTiers.get(selectedTierIndex);
                // Phase 2: Billing removed
                // BillingFlowParams.SubscriptionUpdateParams updateParams = null;
                // if (currentSubscriptionTier != null && currentSubscriptionTier.subscriptionOption != null && currentSubscriptionTier.subscriptionOption.transaction != null) {
                //     updateParams = BillingFlowParams.SubscriptionUpdateParams.newBuilder()
                //             // Phase 2: Billing removed
                //             // .setOldPurchaseToken(BillingController.getInstance().getLastPremiumToken())
                // //                            .setReplaceProrationMode(BillingFlowParams.ProrationMode.IMMEDIATE_AND_CHARGE_FULL_PRICE)
                //             .setSubscriptionReplacementMode(BillingFlowParams.SubscriptionUpdateParams.ReplacementMode.CHARGE_FULL_PRICE)
                //             .build();
                // }
                buyPremium(this, tier, "settings", true, null);
            });'''),

    # Other references in updatePremiumTiers
    ('''if (BillingController.PREMIUM_PRODUCT_DETAILS == null) {
            return;
        }

        List<ProductDetails.SubscriptionOfferDetails> offerDetails = BillingController.PREMIUM_PRODUCT_DETAILS.getSubscriptionOfferDetails();
        if (offerDetails.isEmpty()) {
            return;
        }

        if (selectedTier.getGooglePlayProductDetails() == null) {
            selectedTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);
        }

        if (selectedTier.getOfferDetails() == null) {
            return;
        }

        boolean finalForcePremium = forcePremium;
        BillingController.getInstance().queryPurchases(BillingClient.ProductType.SUBS, (billingResult1, list) -> AndroidUtilities.runOnUIThread(() -> {
            if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                Runnable onSuccess = () -> {
                    if (fragment instanceof PremiumPreviewFragment) {
                        PremiumPreviewFragment premiumPreviewFragment = (PremiumPreviewFragment) fragment;
                        if (finalForcePremium) {
                            premiumPreviewFragment.setForcePremium();
                        }
                        premiumPreviewFragment.getMediaDataController().loadPremiumPromo(false);

                        premiumPreviewFragment.listView.smoothScrollToPosition(0);
                    } else {
                        final PremiumPreviewFragment previewFragment = new PremiumPreviewFragment(null);
                        if (finalForcePremium) {
                            previewFragment.setForcePremium();
                        }
                        if (fragment != null) {
                            fragment.presentFragment(previewFragment);
                        } else {
                            final BaseFragment lastFragment = LaunchActivity.getSafeLastFragment();
                            if (lastFragment != null) {
                                lastFragment.presentFragment(previewFragment);
                            }
                        }
                    }
                    if (fragment != null && fragment.getParentActivity() instanceof LaunchActivity) {
                        try {
                            fragment.getFragmentView().performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
                        } catch (Exception ignored) {}
                        ((LaunchActivity) fragment.getParentActivity()).getFireworksOverlay().start();
                    }
                };
                if (list != null && !list.isEmpty() && !UserConfig.getInstance(account).isPremium()) {
                    for (Purchase purchase : list) {
                        if (purchase.getProducts().contains(BillingController.PREMIUM_PRODUCT_ID)) {
                            TLRPC.TL_payments_assignPlayMarketTransaction req = new TLRPC.TL_payments_assignPlayMarketTransaction();
                            req.receipt = new TLRPC.TL_dataJSON();
                            req.receipt.data = purchase.getOriginalJson();
                            TLRPC.TL_inputStorePaymentPremiumSubscription purpose = new TLRPC.TL_inputStorePaymentPremiumSubscription();
                            purpose.restore = true;
                            if (updateParams != null) {
                                purpose.upgrade = true;
                            }
                            req.purpose = purpose;
                            ConnectionsManager.getInstance(account).sendRequest(req, (response, error) -> {
                                if (response instanceof TLRPC.Updates) {
                                    MessagesController.getInstance(account).processUpdates((TLRPC.Updates) response, false);

                                    AndroidUtilities.runOnUIThread(onSuccess);
                                } else if (error != null) {
                                    AndroidUtilities.runOnUIThread(() -> AlertsCreator.processError(account, error, fragment, req));
                                }
                            }, ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagInvokeAfter);


                            return;
                        }
                    }
                }

                BillingController.getInstance().addResultListener(BillingController.PREMIUM_PRODUCT_ID, billingResult -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        AndroidUtilities.runOnUIThread(onSuccess);
                    }
                });

                TLRPC.TL_payments_canPurchaseStore req = new TLRPC.TL_payments_canPurchaseStore();
                TLRPC.TL_inputStorePaymentPremiumSubscription purpose = new TLRPC.TL_inputStorePaymentPremiumSubscription();
                if (updateParams != null) {
                    purpose.upgrade = true;
                }
                req.purpose = purpose;
                ConnectionsManager.getInstance(account).sendRequest(req, (response, error) -> {
                    AndroidUtilities.runOnUIThread(() -> {
                        if (response instanceof TLRPC.TL_boolTrue) {
                            final Activity activity = fragment != null ? fragment.getParentActivity() : AndroidUtilities.getActivity();
                            BillingController.getInstance().launchBillingFlow(activity, fragment.getAccountInstance(), purpose, Collections.singletonList(
                                    BillingFlowParams.ProductDetailsParams.newBuilder()
                                            .setProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS)
                                            .setOfferToken(selectedTier.getOfferDetails().getOfferToken())
                                            .build()
                            ), updateParams, false);
                        } else {
                            AlertsCreator.processError(account, error, fragment, req);
                        }
                    });
                });
            }
        }));
    }''',
     '''// Phase 2: Billing removed - original method body removed
    }'''),

    # Other references
    ('if (!BuildVars.useInvoiceBilling() && currentSubscriptionTier != null && !Objects.equals(BillingController.getInstance().getLastPremiumTransaction(),',
     '// Phase 2: Billing removed\n            // if (!BuildVars.useInvoiceBilling() && currentSubscriptionTier != null && !Objects.equals(BillingController.getInstance().getLastPremiumTransaction(),'),
    ('} else if (BillingController.getInstance().isReady() && BillingController.PREMIUM_PRODUCT_DETAILS != null) {',
     '// Phase 2: Billing removed\n            // } else if (BillingController.getInstance().isReady() && BillingController.PREMIUM_PRODUCT_DETAILS != null) {'),
    ('subscriptionTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);',
     '// Phase 2: Billing removed\n                    // subscriptionTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);'),
    ('if (!BuildVars.useInvoiceBilling() && (!BillingController.getInstance().isReady() || subscriptionTiers.isEmpty() || selectedTierIndex >= subscriptionTiers.size() || subscriptionTiers.get(selectedTierIndex).googlePlayProductDetails == null)) {',
     '// Phase 2: Billing removed\n        // if (!BuildVars.useInvoiceBilling() && (!BillingController.getInstance().isReady() || subscriptionTiers.isEmpty() || selectedTierIndex >= subscriptionTiers.size() || subscriptionTiers.get(selectedTierIndex).googlePlayProductDetails == null)) {'),
    ('.setOldPurchaseToken(BillingController.getInstance().getLastPremiumToken())',
     '// Phase 2: Billing removed\n                            // .setOldPurchaseToken(BillingController.getInstance().getLastPremiumToken())'),
]

for old, new in code_replacements:
    content = content.replace(old, new)

# 6. Fix getFormattedPrice* methods in SubscriptionTier
formatted_price_replacements = [
    ('return BillingController.getInstance().formatCurrency(pricePerYearRegular, getCurrency());',
     'return "0"; // Phase 2: Billing removed'),
    ('return googlePlayProductDetails == null ? "" : BillingController.getInstance().formatCurrency(pricePerYearRegular, getCurrency(), 6);',
     'return "0"; // Phase 2: Billing removed'),
    ('return BillingController.getInstance().formatCurrency(getPricePerYear(), getCurrency());',
     'return "0"; // Phase 2: Billing removed'),
    ('return googlePlayProductDetails == null ? "" : BillingController.getInstance().formatCurrency(getPricePerYear(), getCurrency(), 6);',
     'return "0"; // Phase 2: Billing removed'),
    ('return BillingController.getInstance().formatCurrency(getPricePerMonth(), getCurrency());',
     'return "0"; // Phase 2: Billing removed'),
    ('return googlePlayProductDetails == null ? "" : BillingController.getInstance().formatCurrency(getPricePerMonth(), getCurrency(), 6);',
     'return "0"; // Phase 2: Billing removed'),
    ('return BillingController.getInstance().formatCurrency(getPrice(), getCurrency());',
     'return "0"; // Phase 2: Billing removed'),
    ('return googlePlayProductDetails == null ? "" : BillingController.getInstance().formatCurrency(getPrice(), getCurrency(), 6);',
     'return "0"; // Phase 2: Billing removed'),
]

for old, new in formatted_price_replacements:
    content = content.replace(old, new)

# Fix getPrice method in SubscriptionTier
content = content.replace(
    '''        public long getPrice() {
            if (BuildVars.useInvoiceBilling() || subscriptionOption.store_product == null) {
                return subscriptionOption.amount;
            }
            if (googlePlayProductDetails == null) {
                return 0;
            }
            checkOfferDetails();
            return offerDetails == null ? 0 : offerDetails.getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros();
        }''',
    '''        public long getPrice() {
            return subscriptionOption.amount;
        }''')

# Fix getCurrency method in SubscriptionTier
content = content.replace(
    '''        public String getCurrency() {
            if (BuildVars.useInvoiceBilling() || subscriptionOption.store_product == null) {
                return subscriptionOption.currency;
            }
            if (googlePlayProductDetails == null) {
                return "";
            }
            checkOfferDetails();
            return offerDetails == null ? "" : offerDetails.getPricingPhases().getPricingPhaseList().get(0).getPriceCurrencyCode();
        }''',
    '''        public String getCurrency() {
            return subscriptionOption.currency;
        }''')

# Comment out checkOfferDetails method
content = content.replace(
    '''        private void checkOfferDetails() {
            if (googlePlayProductDetails == null) {
                return;
            }

            if (offerDetails == null) {
                for (ProductDetails.SubscriptionOfferDetails details : googlePlayProductDetails.getSubscriptionOfferDetails()) {
                    String period = details.getPricingPhases().getPricingPhaseList().get(0).getBillingPeriod();
                    if (getMonths() == 12 ? period.equals("P1Y") : period.equals(String.format(Locale.ROOT, "P%dM", getMonths()))) {
                        offerDetails = details;
                        break;
                    }
                }
            }
        }''',
    '''        // Phase 2: Billing removed - no offerDetails
        // private void checkOfferDetails() { }''')

# Comment out the ProductDetails field declarations
content = content.replace(
    '''        private ProductDetails googlePlayProductDetails;
        private ProductDetails.SubscriptionOfferDetails offerDetails;''',
    '''        // Phase 2: Billing removed - no ProductDetails
        // private ProductDetails googlePlayProductDetails;
        // private ProductDetails.SubscriptionOfferDetails offerDetails;''')

# Comment out the ProductDetails getter/setter methods
content = content.replace(
    '''        public ProductDetails getGooglePlayProductDetails() {
            return googlePlayProductDetails;
        }

        public ProductDetails.SubscriptionOfferDetails getOfferDetails() {
            checkOfferDetails();
            return offerDetails;
        }

        public void setGooglePlayProductDetails(ProductDetails googlePlayProductDetails) {
            this.googlePlayProductDetails = googlePlayProductDetails;
        }''',
    '''        // Phase 2: Billing removed
        // public ProductDetails getGooglePlayProductDetails() { return googlePlayProductDetails; }
        // public ProductDetails.SubscriptionOfferDetails getOfferDetails() { return offerDetails; }
        // public void setGooglePlayProductDetails(ProductDetails googlePlayProductDetails) { this.googlePlayProductDetails = googlePlayProductDetails; }''')

# Comment out observer code in didReceivedNotification
content = content.replace(
    '''    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void didReceivedNotification(int id, int account, Object... args) {
        // Phase 2: Billing removed
        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {
            updateButtonText(false);
            backgroundView.updatePremiumTiers();
        }
        if (id == NotificationCenter.currentUserPremiumStatusChanged || id == NotificationCenter.premiumPromoUpdated) {''',
    '''    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void didReceivedNotification(int id, int account, Object... args) {
        // Phase 2: Billing removed
        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {
        //     updateButtonText(false);
        //     backgroundView.updatePremiumTiers();
        // }
        if (id == NotificationCenter.currentUserPremiumStatusChanged || id == NotificationCenter.premiumPromoUpdated) {''')

# Comment out observer code in onFragmentCreate and onFragmentDestroy
content = content.replace(
    'NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {',
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {'
)

# Write the fixed content
with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/PremiumPreviewFragment.java', 'w') as f:
    f.write(content)

print("Created complete fixed file")
print(f"Length: {len(content)} chars, Lines: {content.count(chr(10))}")

# Verify syntax
open_braces = content.count('{')
close_braces = content.count('}')
print(f"Braces: {open_braces} / {close_braces} (diff: {open_braces - close_braces})")

open_parens = content.count('(')
close_parens = content.count(')')
print(f"Parens: {open_parens} / {close_parens} (diff: {open_parens - close_parens})")

open_brackets = content.count('[')
close_brackets = content.count(']')
print(f"Brackets: {open_brackets} / {close_brackets} (diff: {open_brackets - close_brackets})")

# Check for uncommented Billing references
lines = content.split('\n')
uncommented = []
for i, line in enumerate(content.split('\n')):
    stripped = line.strip()
    if stripped and not stripped.startswith('//') and not stripped.startswith('/*'):
        if 'BillingController' in line or 'BillingClient' in line or 'ProductDetails' in line:
            if 'PREMIUM_PRODUCT' in line or 'billingProductDetailsUpdated' in line or 'getLastPremiumTransaction' in line or 'PREMIUM_PRODUCT_DETAILS' in line or 'PREMIUM_PRODUCT_ID' in line or 'getLastPremiumToken' in line or 'isReady' in line or 'getInstance' in line or 'formatCurrency' in line or 'getLastPremiumToken' in line or 'launchBillingFlow' in line or 'addResultListener' in line or 'queryPurchases' in line or 'setGooglePlayProductDetails' in line or 'getGooglePlayProductDetails' in line or 'getOfferDetails' in line or 'getPrice' in line or 'getCurrency' in line or 'getOfferDetails' in line or 'setGooglePlayProductDetails' in line:
                print(f"Potential uncommented at line {i+1}: {line.strip()[:80]}")

print("Done")
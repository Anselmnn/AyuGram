#!/usr/bin/env python3
import re

# Read the original file
with open('/tmp/PremiumPreviewFragment_original.java', 'r') as f:
    content = f.read()

# Apply package rename
content = content.replace('package org.telegram.ui;', 'package com.tech.ayugram.ui;')
content = content.replace('import org.telegram.', 'import com.tech.ayugram.')
content = content.replace('import static org.telegram.', 'import static com.tech.ayugram.')

# Comment out billing imports
content = re.sub(r'^import com\.android\.billingclient\.api\.BillingClient;$', '// import com.android.billingclient.api.BillingClient; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.android\.billingclient\.api\.BillingFlowParams;$', '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.android\.billingclient\.api\.ProductDetails;$', '// import com.android.billingclient.api.ProductDetails; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.android\.billingclient\.api\.Purchase;$', '// import com.android.billingclient.api.Purchase; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.tech\.ayugram\.messenger\.BillingController;$', '// import com.tech.ayugram.messenger.BillingController; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.tech\.ayugram\.messenger\.MediaDataController;$', '// import com.tech.ayugram.messenger.MediaDataController; (Phase 2)', content, flags=re.MULTILINE)

# Stub buyPremium methods (6 overloads)
content = content.replace(
    '    public static void buyPremium(BaseFragment fragment) {\n        buyPremium(fragment, "settings");\n    }',
    '    public static void buyPremium(BaseFragment fragment) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        buyPremium(fragment, null, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        buyPremium(fragment, null, source, forcePremium);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        buyPremium(fragment, tier, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        buyPremium(fragment, tier, source, forcePremium, null);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

# Replace the sixth buyPremium method (the one with the full body)
content = re.sub(
    r'public static void buyPremium\(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams\.SubscriptionUpdateParams updateParams\) \{[\s\S]*?\n    \}',
    'public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams.SubscriptionUpdateParams updateParams) {\n        // Phase 2: Billing removed\n    }',
    content,
    flags=re.DOTALL
)

# Stub getPremiumButtonText
content = re.sub(
    r'public static String getPremiumButtonText\(int currentAccount, SubscriptionTier tier\) \{[\s\S]*?\n    \}',
    'public static String getPremiumButtonText(int currentAccount, SubscriptionTier tier) {\n        // Phase 2: Billing removed - stub\n        return "Premium";\n    }',
    content,
    flags=re.DOTALL
)

# Replace SubscriptionTier class
old_st = '''    public final static class SubscriptionTier {
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

new_st = '''    public final static class SubscriptionTier {
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

content = content.replace(old_st, new_st)

# Comment out observer code
content = content.replace(
    'NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {',
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

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
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

# Fix missing closing brace for updatePremiumTiers
content = content.replace(
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;',
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;')

# Stub buyPremium methods
content = content.replace(
    '    public static void buyPremium(BaseFragment fragment) {\n        buyPremium(fragment, "settings");\n    }',
    '    public static void buyPremium(BaseFragment fragment) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        buyPremium(fragment, null, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        buyPremium(fragment, null, source, forcePremium);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        buyPremium(fragment, tier, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        buyPremium(fragment, tier, source, forcePremium, null);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

# Replace the sixth buyPremium method
content = re.sub(
    r'public static void buyPremium\(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams\.SubscriptionUpdateParams updateParams\) \{[\s\S]*?\n    \}',
    'public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams.SubscriptionUpdateParams updateParams) {\n        // Phase 2: Billing removed\n    }',
    content,
    flags=re.DOTALL
)

# Stub getPremiumButtonText
content = re.sub(
    r'public static String getPremiumButtonText\(int currentAccount, SubscriptionTier tier\) \{[\s\S]*?\n    \}',
    'public static String getPremiumButtonText(int currentAccount, SubscriptionTier tier) {\n        // Phase 2: Billing removed - stub\n        return "Premium";\n    }',
    content,
    flags=re.DOTALL
)

# Replace SubscriptionTier class
old_st = '''    public final static class SubscriptionTier {
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

new_st = '''    public final static class SubscriptionTier {
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

content = content.replace(old_st, new_st)

# Comment out observer code
content = content.replace(
    'NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {',
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

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
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

# Fix missing closing brace for updatePremiumTiers
content = content.replace(
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;',
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;')

# Stub buyPremium methods
content = content.replace(
    '    public static void buyPremium(BaseFragment fragment) {\n        buyPremium(fragment, "settings");\n    }',
    '    public static void buyPremium(BaseFragment fragment) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        buyPremium(fragment, null, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        buyPremium(fragment, null, source, forcePremium);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        buyPremium(fragment, tier, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        buyPremium(fragment, tier, source, forcePremium, null);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

# Replace the sixth buyPremium method
content = re.sub(
    r'public static void buyPremium\(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams\.SubscriptionUpdateParams updateParams\) \{[\s\S]*?\n    \}',
    'public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams.SubscriptionUpdateParams updateParams) {\n        // Phase 2: Billing removed\n    }',
    content,
    flags=re.DOTALL
)

# Stub getPremiumButtonText
content = re.sub(
    r'public static String getPremiumButtonText\(int currentAccount, SubscriptionTier tier\) \{[\s\S]*?\n    \}',
    'public static String getPremiumButtonText(int currentAccount, SubscriptionTier tier) {\n        // Phase 2: Billing removed - stub\n        return "Premium";\n    }',
    content,
    flags=re.DOTALL
)

# Replace SubscriptionTier class
content = content.replace(old_st, new_st)

# Comment out observer code
content = content.replace(
    'NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {',
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

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
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

# Fix missing closing brace for updatePremiumTiers
content = content.replace(
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;',
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;')

# Stub buyPremium methods
content = content.replace(
    '    public static void buyPremium(BaseFragment fragment) {\n        buyPremium(fragment, "settings");\n    }',
    '    public static void buyPremium(BaseFragment fragment) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        buyPremium(fragment, null, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        buyPremium(fragment, null, source, forcePremium);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        buyPremium(fragment, tier, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        buyPremium(fragment, tier, source, forcePremium, null);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

# Replace the sixth buyPremium method
content = re.sub(
    r'public static void buyPremium\(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams\.SubscriptionUpdateParams updateParams\) \{[\s\S]*?\n    \}',
    'public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams.SubscriptionUpdateParams updateParams) {\n        // Phase 2: Billing removed\n    }',
    content,
    flags=re.DOTALL
)

# Stub getPremiumButtonText
content = re.sub(
    r'public static String getPremiumButtonText\(int currentAccount, SubscriptionTier tier\) \{[\s\S]*?\n    \}',
    'public static String getPremiumButtonText(int currentAccount, SubscriptionTier tier) {\n        // Phase 2: Billing removed - stub\n        return "Premium";\n    }',
    content,
    flags=re.DOTALL
)

# Replace SubscriptionTier class
content = content.replace(old_st, new_st)

# Comment out observer code
content = content.replace(
    'NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {',
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

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
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

# Fix missing closing brace for updatePremiumTiers
content = content.replace(
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;',
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;')

# Stub buyPremium methods
content = content.replace(
    '    public static void buyPremium(BaseFragment fragment) {\n        buyPremium(fragment, "settings");\n    }',
    '    public static void buyPremium(BaseFragment fragment) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        buyPremium(fragment, null, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        buyPremium(fragment, null, source, forcePremium);\n    }',
    '    public static void buyPremium(BaseFragment fragment, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        buyPremium(fragment, tier, source, true);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source) {\n        // Phase 2: Billing removed\n    }')

content = content.replace(
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        buyPremium(fragment, tier, source, forcePremium, null);\n    }',
    '    public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium) {\n        // Phase 2: Billing removed\n    }')

# Replace the sixth buyPremium method
content = re.sub(
    r'public static void buyPremium\(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams\.SubscriptionUpdateParams updateParams\) \{[\s\S]*?\n    \}',
    'public static void buyPremium(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams.SubscriptionUpdateParams updateParams) {\n        // Phase 2: Billing removed\n    }',
    content,
    flags=re.DOTALL
)

# Stub getPremiumButtonText
content = re.sub(
    r'public static String getPremiumButtonText\(int currentAccount, SubscriptionTier tier\) \{[\s\S]*?\n    \}',
    'public static String getPremiumButtonText(int currentAccount, SubscriptionTier tier) {\n        // Phase 2: Billing removed - stub\n        return "Premium";\n    }',
    content,
    flags=re.DOTALL
)

# Replace SubscriptionTier class
content = content.replace(old_st, new_st)

# Comment out observer code
content = content.replace(
    'NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);')

content = content.replace(
    'if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {',
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

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
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {')

# Fix missing closing brace for updatePremiumTiers
content = content.replace(
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;',
    '            updateButtonText(false);\n            tierListView.getAdapter().notifyDataSetChanged();\n        }\n\n        private boolean setTierListViewVisibility;')

# Apply package rename and import changes
content = content.replace('package org.telegram.ui;', 'package com.tech.ayugram.ui;')
content = content.replace('import org.telegram.', 'import com.tech.ayugram.')
content = content.replace('import static org.telegram.', 'import static com.tech.ayugram.')

# Comment out billing imports
content = re.sub(r'^import com\.android\.billingclient\.api\.BillingClient;$', '// import com.android.billingclient.api.BillingClient; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.android\.billingclient\.api\.BillingFlowParams;$', '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.android\.billingclient\.api\.ProductDetails;$', '// import com.android.billingclient.api.ProductDetails; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.android\.billingclient\.api\.Purchase;$', '// import com.android.billingclient.api.Purchase; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.tech\.ayugram\.messenger\.BillingController;$', '// import com.tech.ayugram.messenger.BillingController; (Phase 2)', content, flags=re.MULTILINE)
content = re.sub(r'^import com\.tech\.ayugram\.messenger\.MediaDataController;$', '// import com.tech.ayugram.messenger.MediaDataController; (Phase 2)', content, flags=re.MULTILINE)

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
uncommented = []
for i, line in enumerate(content.split('\n')):
    stripped = line.strip()
    if stripped and not stripped.startswith('//') and not stripped.startswith('/*'):
        if 'BillingController' in line or 'BillingClient' in line or 'ProductDetails' in line:
            if 'PREMIUM_PRODUCT' in line or 'billingProductDetailsUpdated' in line or 'getLastPremiumTransaction' in line or 'PREMIUM_PRODUCT_DETAILS' in line or 'PREMIUM_PRODUCT_ID' in line or 'getLastPremiumToken' in line or 'isReady' in line or 'getInstance' in line or 'formatCurrency' in line or 'getLastPremiumToken' in line or 'launchBillingFlow' in line or 'addResultListener' in line or 'queryPurchases' in line or 'setGooglePlayProductDetails' in line or 'getGooglePlayProductDetails' in line or 'getOfferDetails' in line or 'getPrice' in line or 'getCurrency' in line or 'getOfferDetails' in line or 'setGooglePlayProductDetails' in line:
                print(f"Line {i+1}: {line.strip()[:80]}")

print("Done")
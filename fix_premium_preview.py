#!/usr/bin/env python3
"""
Carefully apply Phase 2 changes to PremiumPreviewFragment.java:
1. Stub buyPremium methods (5 overloads)
2. Stub getPremiumButtonText
3. Fix SubscriptionTier class - remove ProductDetails, keep only invoice billing logic
4. Comment out observer code for billingProductDetailsUpdated
5. Comment out all BillingController/GooglePlay references in code
"""

import re

with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/PremiumPreviewFragment.java', 'r') as f:
    content = f.read()

# 1. Stub the first buyPremium method (line ~989)
content = re.sub(
    r'(public static void buyPremium\(BaseFragment fragment\) \{\n)(.*?)(\n    \})',
    r'\1        // Phase 2: Billing removed\n    }',
    content,
    count=1,
    flags=re.DOTALL
)

# 2. Stub the second buyPremium method (line ~1105)
content = re.sub(
    r'(public static void buyPremium\(BaseFragment fragment, String source\) \{\n)(.*?)(\n    \})',
    r'\1        // Phase 2: Billing removed\n    }',
    content,
    count=1,
    flags=re.DOTALL
)

# 3. Stub the third buyPremium method (line ~1109)
content = re.sub(
    r'(public static void buyPremium\(BaseFragment fragment, String source, boolean forcePremium\) \{\n)(.*?)(\n    \})',
    r'\1        // Phase 2: Billing removed\n    }',
    content,
    count=1,
    flags=re.DOTALL
)

# 4. Stub the fourth buyPremium method (line ~1113)
content = re.sub(
    r'(public static void buyPremium\(BaseFragment fragment, SubscriptionTier tier, String source\) \{\n)(.*?)(\n    \})',
    r'\1        // Phase 2: Billing removed\n    }',
    content,
    count=1,
    flags=re.DOTALL
)

# 5. Stub the fifth buyPremium method (line ~1117)
content = re.sub(
    r'(public static void buyPremium\(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium\) \{\n)(.*?)(\n    \})',
    r'\1        // Phase 2: Billing removed\n    }',
    content,
    count=1,
    flags=re.DOTALL
)

# 6. Stub the sixth buyPremium method (line ~1121) - this is the main one with body
content = re.sub(
    r'(public static void buyPremium\(BaseFragment fragment, SubscriptionTier tier, String source, boolean forcePremium, BillingFlowParams\.SubscriptionUpdateParams updateParams\) \{\n)(.*?)(\n    \})',
    r'\1        // Phase 2: Billing removed\n    }',
    content,
    count=1,
    flags=re.DOTALL
)

# 7. Stub getPremiumButtonText method
content = re.sub(
    r'(public static String getPremiumButtonText\(int currentAccount, SubscriptionTier tier\) \{\n)(.*?)(\n    \})',
    r'\1        // Phase 2: Billing removed - stub\n        return "Premium";\n    }',
    content,
    count=1,
    flags=re.DOTALL
)

# 8. Fix SubscriptionTier class - remove ProductDetails fields and methods, keep only invoice billing logic
# First, let's find and replace the entire SubscriptionTier class
subscription_tier_pattern = r'(    public final static class SubscriptionTier \{[\s\S]*?^\s*\}\s*$)'
def replace_subscription_tier(match):
    return '''    public final static class SubscriptionTier {
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

content = re.sub(subscription_tier_pattern, replace_subscription_tier, content, count=1, flags=re.MULTILINE)

# 9. Comment out observer code in onFragmentCreate
content = content.replace(
    'NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);'
)

# 10. Comment out observer code in onFragmentDestroy
content = content.replace(
    'NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);',
    '// Phase 2: Billing removed\n        // NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);'
)

# 11. Comment out the if condition in didReceivedNotification
content = content.replace(
    'if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {',
    '// Phase 2: Billing removed\n        // if (id == NotificationCenter.billingProductDetailsUpdated || id == NotificationCenter.premiumPromoUpdated) {'
)

# 12. Comment out remaining BillingController references in code
# Let's do this carefully for each occurrence
replacements = [
    # Line 1179
    ('if (BillingController.PREMIUM_PRODUCT_DETAILS == null) {',
     '// Phase 2: Billing removed\n            // if (BillingController.PREMIUM_PRODUCT_DETAILS == null) {'),
    # Line 1183
    ('List<ProductDetails.SubscriptionOfferDetails> offerDetails = BillingController.PREMIUM_PRODUCT_DETAILS.getSubscriptionOfferDetails();',
     '// Phase 2: Billing removed\n            // List<ProductDetails.SubscriptionOfferDetails> offerDetails = BillingController.PREMIUM_PRODUCT_DETAILS.getSubscriptionOfferDetails();'),
    # Line 1188
    ('if (selectedTier.getGooglePlayProductDetails() == null) {',
     '// Phase 2: Billing removed\n            // if (selectedTier.getGooglePlayProductDetails() == null) {'),
    # Line 1189
    ('selectedTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);',
     '// Phase 2: Billing removed\n            // selectedTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);'),
    # Line 1197
    ('BillingController.getInstance().queryPurchases(BillingClient.ProductType.SUBS, (billingResult1, list) -> AndroidUtilities.runOnUIThread(() -> {',
     '// Phase 2: Billing removed\n            // BillingController.getInstance().queryPurchases(BillingClient.ProductType.SUBS, (billingResult1, list) -> AndroidUtilities.runOnUIThread(() -> {'),
    # Line 1198
    ('if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {',
     '// Phase 2: Billing removed\n                // if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {'),
    # Line 1231
    ('if (purchase.getProducts().contains(BillingController.PREMIUM_PRODUCT_ID)) {',
     '// Phase 2: Billing removed\n                        // if (purchase.getProducts().contains(BillingController.PREMIUM_PRODUCT_ID)) {'),
    # Line 1256
    ('BillingController.getInstance().addResultListener(BillingController.PREMIUM_PRODUCT_ID, billingResult -> {',
     '// Phase 2: Billing removed\n                // BillingController.getInstance().addResultListener(BillingController.PREMIUM_PRODUCT_ID, billingResult -> {'),
    # Line 1257
    ('if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {',
     '// Phase 2: Billing removed\n                    // if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {'),
    # Line 1272
    ('BillingController.getInstance().launchBillingFlow(activity, fragment.getAccountInstance(), purpose, Collections.singletonList(',
     '// Phase 2: Billing removed\n                            // BillingController.getInstance().launchBillingFlow(activity, fragment.getAccountInstance(), purpose, Collections.singletonList('),
    # Line 1273-1274
    ('BillingFlowParams.ProductDetailsParams.newBuilder()\n                                            .setProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS)',
     '// Phase 2: Billing removed\n                                            // BillingFlowParams.ProductDetailsParams.newBuilder()\n                                            // .setProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS)'),
    # Line 1314
    ('price = BillingController.getInstance().formatCurrency(selectedOption.amount / 12, selectedOption.currency);',
     '// Phase 2: Billing removed\n                            // price = BillingController.getInstance().formatCurrency(selectedOption.amount / 12, selectedOption.currency);'),
    # Line 1317
    ('price = BillingController.getInstance().formatCurrency(selectedOption.amount, selectedOption.currency);',
     '// Phase 2: Billing removed\n                            // price = BillingController.getInstance().formatCurrency(selectedOption.amount, selectedOption.currency);'),
    # Line 1320
    ('price = BillingController.getInstance().formatCurrency(selectedOption.amount, selectedOption.currency);',
     '// Phase 2: Billing removed\n                        // price = BillingController.getInstance().formatCurrency(selectedOption.amount, selectedOption.currency);'),
    # Line 1330
    ('if (BillingController.PREMIUM_PRODUCT_DETAILS != null) {',
     '// Phase 2: Billing removed\n            // if (BillingController.PREMIUM_PRODUCT_DETAILS != null) {'),
    # Line 1331
    ('List<ProductDetails.SubscriptionOfferDetails> details = BillingController.PREMIUM_PRODUCT_DETAILS.getSubscriptionOfferDetails();',
     '// Phase 2: Billing removed\n                // List<ProductDetails.SubscriptionOfferDetails> details = BillingController.PREMIUM_PRODUCT_DETAILS.getSubscriptionOfferDetails();'),
    # Line 1333
    ('ProductDetails.SubscriptionOfferDetails offerDetails = details.get(0);',
     '// Phase 2: Billing removed\n                    // ProductDetails.SubscriptionOfferDetails offerDetails = details.get(0);'),
    # Line 1334
    ('for (ProductDetails.PricingPhase phase : offerDetails.getPricingPhases().getPricingPhaseList()) {',
     '// Phase 2: Billing removed\n                    // for (ProductDetails.PricingPhase phase : offerDetails.getPricingPhases().getPricingPhaseList()) {'),
    # Line 1339
    ('price = BillingController.getInstance().formatCurrency(phase.getPriceAmountMicros() / 12L, phase.getPriceCurrencyCode(), 6);',
     '// Phase 2: Billing removed\n                                // price = BillingController.getInstance().formatCurrency(phase.getPriceAmountMicros() / 12L, phase.getPriceCurrencyCode(), 6);'),
    # Line 1342
    ('price = BillingController.getInstance().formatCurrency(phase.getPriceAmountMicros(), phase.getPriceCurrencyCode(), 6);',
     '// Phase 2: Billing removed\n                                // price = BillingController.getInstance().formatCurrency(phase.getPriceAmountMicros(), phase.getPriceCurrencyCode(), 6);'),
    # Line 1998
    ('} else if (!BuildVars.useInvoiceBilling() && currentSubscriptionTier != null && !Objects.equals(BillingController.getInstance().getLastPremiumTransaction(),',
     '// Phase 2: Billing removed\n            // } else if (!BuildVars.useInvoiceBilling() && currentSubscriptionTier != null && !Objects.equals(BillingController.getInstance().getLastPremiumTransaction(),'),
    # Line 2010
    ('} else if (BillingController.getInstance().isReady() && BillingController.PREMIUM_PRODUCT_DETAILS != null) {',
     '// Phase 2: Billing removed\n            // } else if (BillingController.getInstance().isReady() && BillingController.PREMIUM_PRODUCT_DETAILS != null) {'),
    # Line 2015
    ('subscriptionTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);',
     '// Phase 2: Billing removed\n                    // subscriptionTier.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);'),
    # Line 2130
    ('if (!BuildVars.useInvoiceBilling() && (!BillingController.getInstance().isReady() || subscriptionTiers.isEmpty() || selectedTierIndex >= subscriptionTiers.size() || subscriptionTiers.get(selectedTierIndex).googlePlayProductDetails == null)) {',
     '// Phase 2: Billing removed\n        // if (!BuildVars.useInvoiceBilling() && (!BillingController.getInstance().isReady() || subscriptionTiers.isEmpty() || selectedTierIndex >= subscriptionTiers.size() || subscriptionTiers.get(selectedTierIndex).googlePlayProductDetails == null)) {'),
    # Line 2143
    ('.setOldPurchaseToken(BillingController.getInstance().getLastPremiumToken())',
     '// Phase 2: Billing removed\n                            // .setOldPurchaseToken(BillingController.getInstance().getLastPremiumToken())'),
]

for old, new in replacements:
    content = content.replace(old, new)

# 13. Fix getFormattedPrice* methods in SubscriptionTier to return "0" instead of calling BillingController
# These are in the SubscriptionTier class
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

# 14. Fix getPrice method in SubscriptionTier
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
        }'''
)

# 15. Fix getCurrency method in SubscriptionTier
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
        }'''
)

# 16. Remove checkOfferDetails method and googlePlayProductDetails checks in SubscriptionTier
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

# 17. Remove the ProductDetails field declarations in SubscriptionTier
content = content.replace(
    '''        private ProductDetails googlePlayProductDetails;
        private ProductDetails.SubscriptionOfferDetails offerDetails;''',
    '''        // Phase 2: Billing removed - no ProductDetails
        // private ProductDetails googlePlayProductDetails;
        // private ProductDetails.SubscriptionOfferDetails offerDetails;''')

# Write the modified content back
with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/PremiumPreviewFragment.java', 'w') as f:
    f.write(content)

print("Done applying Phase 2 changes to PremiumPreviewFragment.java")
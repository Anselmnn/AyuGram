#!/usr/bin/env python3
"""
Comprehensive Phase 2 fix for all remaining files with removed dependencies.
"""

import os
import re

def fix_file(filepath, replacements):
    """Apply replacements to a file."""
    with open(filepath, 'r') as f:
        content = f.read()
    
    original = content
    for pattern, replacement in replacements:
        content = re.sub(pattern, replacement, content, flags=re.MULTILINE | re.DOTALL)
    
    if content != original:
        with open(filepath, 'w') as f:
            f.write(content)
        return True
    return False

# Fix GiftSheet.java
gift_sheet = "TMessagesProj/src/main/java/com/tech/ayugram/ui/Gifts/GiftSheet.java"
if os.path.exists(gift_sheet):
    with open(gift_sheet, 'r') as f:
        content = f.read()
    
    content = re.sub(r'import com\.android\.billingclient\.api\.BillingClient;', '// import com.android.billingclient.api.BillingClient; (Phase 2)', content)
    content = re.sub(r'import com\.android\.billingclient\.api\.BillingFlowParams;', '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)', content)
    content = re.sub(r'import com\.android\.billingclient\.api\.ProductDetails;', '// import com.android.billingclient.api.ProductDetails; (Phase 2)', content)
    content = re.sub(r'import com\.android\.billingclient\.api\.QueryProductDetailsParams;', '// import com.android.billingclient.api.QueryProductDetailsParams; (Phase 2)', content)
    
    # Stub BillingController references
    content = re.sub(r'BillingController\.getInstance\(\)', '// BillingController.getInstance() // Phase 2', content)
    content = re.sub(r'BillingController\.PREMIUM_PRODUCT_DETAILS', '// BillingController.PREMIUM_PRODUCT_DETAILS // Phase 2', content)
    content = re.sub(r'BillingClient\.', '// BillingClient. // Phase 2', content)
    content = re.sub(r'ProductDetails', '// ProductDetails // Phase 2', content)
    content = re.sub(r'QueryProductDetailsParams', '// QueryProductDetailsParams // Phase 2', content)
    content = re.sub(r'BillingFlowParams', '// BillingFlowParams // Phase 2', content)
    
    with open(gift_sheet, 'w') as f:
        f.write(content)
    print(f"Fixed {gift_sheet}")

# Fix GiftPremiumBottomSheet.java
gift_premium = "TMessagesProj/src/main/java/com/tech/ayugram/ui/Components/Premium/GiftPremiumBottomSheet.java"
if os.path.exists(gift_premium):
    with open(gift_premium, 'r') as f:
        content = f.read()
    
    content = re.sub(r'import com\.android\.billingclient\.api\.BillingClient;', '// import com.android.billingclient.api.BillingClient; (Phase 2)', content)
    content = re.sub(r'import com\.android\.billingclient\.api\.BillingFlowParams;', '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)', content)
    content = re.sub(r'import com\.android\.billingclient\.api\.ProductDetails;', '// import com.android.billingclient.api.ProductDetails; (Phase 2)', content)
    content = re.sub(r'import com\.android\.billingclient\.api\.QueryProductDetailsParams;', '// import com.android.billingclient.api.QueryProductDetailsParams; (Phase 2)', content)
    
    # Stub BillingController references
    content = re.sub(r'BillingController\.getInstance\(\)', '// BillingController.getInstance() // Phase 2', content)
    content = re.sub(r'BillingController\.PREMIUM_PRODUCT_DETAILS', '// BillingController.PREMIUM_PRODUCT_DETAILS // Phase 2', content)
    content = re.sub(r'BillingClient\.', '// BillingClient. // Phase 2', content)
    content = re.sub(r'\bProductDetails\b', '// ProductDetails // Phase 2', content)
    content = re.sub(r'QueryProductDetailsParams', '// QueryProductDetailsParams // Phase 2', content)
    content = re.sub(r'BillingFlowParams', '// BillingFlowParams // Phase 2', content)
    
    with open(gift_premium, 'w') as f:
        f.write(content)
    print(f"Fixed GiftPremiumBottomSheet")

# Fix SendGiftSheet.java
send_gift = "TMessagesProj/src/main/java/com/tech/ayugram/ui/Gifts/SendGiftSheet.java"
if os.path.exists(send_gift):
    with open(send_gift, 'r') as f:
        content = f.read()
    
    content = re.sub(r'import com\.android\.billingclient\.api\.BillingClient;', '// import com.android.billingclient.api.BillingClient; (Phase 2)', content)
    content = re.sub(r'import com\.android\.billingclient\.api\.BillingFlowParams;', '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)', content)
    content = re.sub(r'import com\.android\.billingclient\.api\.ProductDetails;', '// import com.android.billingclient.api.ProductDetails; (Phase 2)', content)
    
    content = re.sub(r'BillingController\.getInstance\(\)', '// BillingController.getInstance() // Phase 2', content)
    content = re.sub(r'BillingController\.PREMIUM_PRODUCT_DETAILS', '// BillingController.PREMIUM_PRODUCT_DETAILS // Phase 2', content)
    content = re.sub(r'BillingClient\.', '// BillingClient. // Phase 2', content)
    content = re.sub(r'\bProductDetails\b', '// ProductDetails // Phase 2', content)
    content = re.sub(r'BillingFlowParams', '// BillingFlowParams // Phase 2', content)
    
    with open(send_gift, 'w') as f:
        f.write(content)
    print(f"Fixed SendGiftSheet")

# Fix CameraScanActivity.java - Google Vision
camera_scan = "TMessagesProj/src/main/java/com/tech/ayugram/ui/CameraScanActivity.java"
if os.path.exists(camera_scan):
    with open(camera_scan, 'r') as f:
        content = f.read()
    
    content = re.sub(r'import com\.google\.android\.gms\.vision\.Frame;', '// import com.google.android.gms.vision.Frame; (Phase 2)', content)
    content = re.sub(r'import com\.google\.android\.gms\.vision\.barcode\.Barcode;', '// import com.google.android.gms.vision.barcode.Barcode; (Phase 2)', content)
    content = re.sub(r'import com\.google\.android\.gms\.vision\.barcode\.BarcodeDetector;', '// import com.google.android.gms.vision.barcode.BarcodeDetector; (Phase 2)', content)
    
    content = re.sub(r'BarcodeDetector', '// BarcodeDetector // Phase 2', content)
    content = re.sub(r'Barcode', '// Barcode // Phase 2', content)
    content = re.sub(r'Frame', '// Frame // Phase 2', content)
    
    with open(camera_scan, 'w') as f:
        f.write(content)
    print(f"Fixed CameraScanActivity")

# Fix MrzRecognizer.java
mrz = "TMessagesProj/src/main/java/com/tech/ayugram/messenger/MrzRecognizer.java"
if os.path.exists(mrz):
    with open(mrz, 'r') as f:
        content = f.read()
    
    content = re.sub(r'import com\.google\.android\.gms\.vision\.Frame;', '// import com.google.android.gms.vision.Frame; (Phase 2)', content)
    content = re.sub(r'import com\.google\.android\.gms\.vision\.barcode\.Barcode;', '// import com.google.android.gms.vision.barcode.Barcode; (Phase 2)', content)
    content = re.sub(r'import com\.google\.android\.gms\.vision\.barcode\.BarcodeDetector;', '// import com.google.android.gms.vision.barcode.BarcodeDetector; (Phase 2)', content)
    
    content = re.sub(r'BarcodeDetector', '// BarcodeDetector // Phase 2', content)
    content = re.sub(r'Barcode', '// Barcode // Phase 2', content)
    content = re.sub(r'Frame', '// Frame // Phase 2', content)
    
    with open(mrz, 'w') as f:
        f.write(content)
    print(f"Fixed MrzRecognizer")

print("Done fixing common patterns")

# Now let's verify the changes
files_to_check = [
    "TMessagesProj/src/main/java/com/tech/ayugram/ui/Gifts/GiftSheet.java",
    "TMessagesProj/src/main/java/com/tech/ayugram/ui/Components/Premium/GiftPremiumBottomSheet.java",
    "TMessagesProj/src/main/java/com/tech/ayugram/ui/Gifts/SendGiftSheet.java",
    "TMessagesProj/src/main/java/com/tech/ayugram/ui/CameraScanActivity.java",
    "TMessagesProj/src/main/java/com/tech/ayugram/messenger/MrzRecognizer.java",
]

for f in files_to_check:
    if os.path.exists(f):
        with open(f, 'r') as fh:
            content = fh.read()
        print(f"\n{f}:")
        print(f"  Length: {len(content)} chars, Lines: {content.count(chr(10))}")
        
        # Check for uncommented billing/vision references
        lines = content.split('\n')
        uncommented = []
        for i, line in enumerate(content.split('\n')):
            stripped = line.strip()
            if stripped and not stripped.startswith('//') and not stripped.startswith('/*'):
                if 'BillingClient' in line or 'BillingController' in line or 'ProductDetails' in line or 'BillingFlowParams' in line or 'QueryProductDetailsParams' in line:
                    if not stripped.startswith('//'):
                        print(f"  Line {content[:content.index(line)].count(chr(10))+1}: {line.strip()[:100]}")

print("Done!")
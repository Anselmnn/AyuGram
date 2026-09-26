#!/usr/bin/env python3
"""
Comprehensive Phase 2 fix for LoginActivity.java
Replaces imports and comments out code using removed dependencies.
"""

with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'r') as f:
    content = f.read()

# 1. Fix imports
content = content.replace(
    'import com.android.billingclient.api.BillingClient;',
    '// import com.android.billingclient.api.BillingClient; (Phase 2)'
)
content = content.replace(
    'import com.android.billingclient.api.BillingFlowParams;',
    '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)'
)
content = content.replace(
    'import com.android.billingclient.api.ProductDetails;',
    '// import com.android.billingclient.api.ProductDetails; (Phase 2)'
)
content = content.replace(
    'import com.android.billingclient.api.Purchase;',
    '// import com.android.billingclient.api.Purchase; (Phase 2)'
)
content = content.replace(
    'import com.android.billingclient.api.QueryProductDetailsParams;',
    '// import com.android.billingclient.api.QueryProductDetailsParams; (Phase 2)'
)

content = content.replace(
    'import com.google.android.gms.auth.api.signin.GoogleSignIn;',
    'import com.tech.ayugram.play.stub.GoogleSignIn;'
)
content = content.replace(
    'import com.google.android.gms.auth.api.signin.GoogleSignInAccount;',
    'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount;'
)
content = content.replace(
    'import com.google.android.gms.auth.api.signin.GoogleSignInClient;',
    'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient;'
)
content = content.replace(
    'import com.google.android.gms.auth.api.signin.GoogleSignInOptions;',
    'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions;'
)

content = content.replace(
    'import com.google.android.gms.common.api.ApiException;',
    'import com.tech.ayugram.play.stub.ApiException;'
)
content = content.replace(
    'import com.google.android.gms.safetynet.SafetyNet;',
    'import com.tech.ayugram.play.stub.SafetyNet;'
)
content = content.replace(
    'import com.google.android.gms.tasks.Task;',
    'import com.tech.ayugram.play.stub.Task;'
)

content = content.replace(
    'import com.google.android.play.core.integrity.IntegrityManager;',
    'import com.tech.ayugram.play.stub.IntegrityManager;'
)
content = content.replace(
    'import com.google.android.play.core.integrity.IntegrityManagerFactory;',
    'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory;'
)
content = content.replace(
    'import com.google.android.play.core.integrity.IntegrityTokenRequest;',
    'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest;'
)
content = content.replace(
    'import com.google.android.play.core.integrity.IntegrityTokenResponse;',
    'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse;'
)

content = content.replace(
    'import com.tech.ayugram.messenger.BillingController;',
    '// import com.tech.ayugram.messenger.BillingController; (Phase 2)'
)

# Now fix the code body - replace usage of removed classes with stubs or comments

# SafetyNet
content = content.replace(
    'SafetyNet.getClient(ApplicationLoader.applicationContext).attest(',
    '// Phase 2: SafetyNet.getClient(ApplicationLoader.applicationContext).attest('
)
content = content.replace(
    'SafetyNet.getClient(',
    'com.tech.ayugram.play.stub.SafetyNet.getClient('
)
content = content.replace(
    'SafetyNet.getClient(',
    'com.tech.ayugram.play.stub.SafetyNet.getClient('
)

# IntegrityManager
content = content.replace(
    'IntegrityManagerFactory.create(',
    'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create('
)
content = content.replace(
    'IntegrityManagerFactory',
    'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory'
)
content = content.replace(
    'IntegrityTokenRequest.builder(',
    'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest.builder('
)
content = content.replace(
    'IntegrityTokenRequest',
    'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest'
)
content = content.replace(
    'IntegrityTokenResponse',
    'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse'
)
content = content.replace(
    'IntegrityManagerFactory.create(',
    'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create('
)
content = content.replace(
    'IntegrityManager',
    'com.tech.ayugram.play.stub.IntegrityManager'
)

# SafetyNet
content = content.replace(
    'SafetyNet.getClient(',
    'com.tech.ayugram.play.stub.SafetyNet.getClient('
)

# GoogleSignIn
content = content.replace(
    'GoogleSignIn.getSignedInAccountFromIntent(',
    'com.tech.ayugram.play.stub.GoogleSignIn.getSignedInAccountFromIntent('
)
content = content.replace(
    'GoogleSignIn.getClient(',
    'com.tech.ayugram.play.stub.GoogleSignIn.getClient('
)

# GoogleSignInAccount
content = content.replace(
    'GoogleSignInAccount',
    'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount'
)

# GoogleSignInClient
content = content.replace(
    'GoogleSignInClient',
    'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient'
)

# GoogleSignInOptions
content = content.replace(
    'GoogleSignInOptions.Builder()',
    'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions.Builder()'
)
content = content.replace(
    'GoogleSignInOptions',
    'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions'
)

# GoogleSignInClient
content = content.replace(
    'GoogleSignInClient',
    'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient'
)

# ApiException
content = content.replace(
    'ApiException',
    'com.tech.ayugram.play.stub.ApiException'
)

# SafetyNet
content = content.replace(
    'SafetyNet.getClient(',
    'com.tech.ayugram.play.stub.SafetyNet.getClient('
)

# Task
content = content.replace(
    'Task<IntegrityTokenResponse>',
    'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse>'
)
content = content.replace(
    'Task<GoogleSignInAccount>',
    'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount>'
)

# Write the fixed content
with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'w') as f:
    f.write(content)

print("Fixed LoginActivity")
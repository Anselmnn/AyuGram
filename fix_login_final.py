#!/usr/bin/env python3
"""
Comprehensive single-pass fix for LoginActivity.java Phase 2
"""

import re

with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'r') as f:
    content = f.read()

# === STEP 1: Fix imports ===
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

# Now fix the code body - process line by line to avoid multiple replacements
lines = content.split('\n')
new_lines = []

for line in content.split('\n'):
    # Skip empty lines and comments
    stripped = line.strip()
    if not stripped or stripped.startswith('//') or stripped.startswith('/*'):
        new_lines.append(line)
        continue
    
    # Skip import lines (already handled)
    if stripped.startswith('import ') or stripped.startswith('package ') or stripped.startswith('import static'):
        new_lines.append(line)
        continue
    
    # For code lines, apply careful replacements
    # We need to be very specific about what we replace
    
    # SafetyNet.getClient
    if 'SafetyNet.getClient(' in line and not line.strip().startswith('//'):
        line = line.replace('SafetyNet.getClient(', 'com.tech.ayugram.play.stub.SafetyNet.getClient(')
    
    # SafetyNet
    if 'SafetyNet' in line and 'com.tech.ayugram.play.stub.SafetyNet' not in line and not line.strip().startswith('//'):
        # Replace SafetyNet with fully qualified name
        line = line.replace('SafetyNet', 'com.tech.ayugram.play.stub.SafetyNet')
    
    # IntegrityManager
    if 'IntegrityManagerFactory.create(' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create' not in line:
        line = line.replace('IntegrityManagerFactory.create(', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create(')
    if 'IntegrityManagerFactory' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory' not in line and 'IntegrityManagerFactory' in line and not line.strip().startswith('//'):
        line = line.replace('IntegrityManagerFactory', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory')
    
    if 'IntegrityTokenRequest.builder(' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest.builder' not in line:
        line = line.replace('IntegrityTokenRequest.builder(', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest.builder(')
    if 'IntegrityTokenRequest' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest' not in line and 'IntegrityTokenRequest' in line and not line.strip().startswith('//'):
        line = line.replace('IntegrityTokenRequest', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest')
    
    if 'IntegrityTokenResponse' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse' not in line and 'IntegrityTokenResponse' in line and not line.strip().startswith('//'):
        line = line.replace('IntegrityTokenResponse', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse')
    
    if 'IntegrityManagerFactory.create(' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create' not in line:
        line = line.replace('IntegrityManagerFactory.create(', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create(')
    if 'IntegrityManagerFactory' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory' not in line:
        line = line.replace('IntegrityManagerFactory', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory')
    
    if 'IntegrityManager' in line and 'com.tech.ayugram.play.stub.IntegrityManager' not in line and 'IntegrityManager' in line and not line.strip().startswith('//'):
        line = line.replace('IntegrityManager', 'com.tech.ayugram.play.stub.IntegrityManager')
    
    # SafetyNet
    if 'SafetyNet.getClient(' in line and 'com.tech.ayugram.play.stub.SafetyNet.getClient' not in line:
        line = line.replace('SafetyNet.getClient(', 'com.tech.ayugram.play.stub.SafetyNet.getClient(')
    
    # GoogleSignIn
    if 'GoogleSignIn.getSignedInAccountFromIntent(' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.getSignedInAccountFromIntent' not in line:
        line = line.replace('GoogleSignIn.getSignedInAccountFromIntent(', 'com.tech.ayugram.play.stub.GoogleSignIn.getSignedInAccountFromIntent(')
    if 'GoogleSignIn.getClient(' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.getClient' not in line:
        line = line.replace('GoogleSignIn.getClient(', 'com.tech.ayugram.play.stub.GoogleSignIn.getClient(')
    
    # GoogleSignInAccount
    if 'GoogleSignInAccount' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount' not in line:
        line = line.replace('GoogleSignInAccount', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount')
    
    # GoogleSignInClient
    if 'GoogleSignInClient' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient' not in line:
        line = line.replace('GoogleSignInClient', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient')
    
    # GoogleSignInOptions
    if 'GoogleSignInOptions.Builder()' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions.Builder()' not in line:
        line = line.replace('GoogleSignInOptions.Builder()', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions.Builder()')
    if 'GoogleSignInOptions' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions' not in line:
        line = line.replace('GoogleSignInOptions', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions')
    
    # GoogleSignInClient
    if 'GoogleSignInClient' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient' not in line:
        line = line.replace('GoogleSignInClient', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient')
    
    # ApiException
    if 'ApiException' in line and 'com.tech.ayugram.play.stub.ApiException' not in line:
        line = line.replace('ApiException', 'com.tech.ayugram.play.stub.ApiException')
    
    # SafetyNet
    if 'SafetyNet.getClient(' in line and 'com.tech.ayugram.play.stub.SafetyNet.getClient' not in line:
        line = line.replace('SafetyNet.getClient(', 'com.tech.ayugram.play.stub.SafetyNet.getClient(')
    
    # Task
    if 'Task<IntegrityTokenResponse>' in line and 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse>' not in line:
        line = line.replace('Task<IntegrityTokenResponse>', 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse>')
    if 'Task<GoogleSignInAccount>' in line and 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount>' not in line:
        line = line.replace('Task<GoogleSignInAccount>', 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount>')
    
    new_lines.append(line)

content = '\n'.join(new_lines)

# Now write the fixed content
with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'w') as f:
    f.write('\n'.join(content.split('\n')))

print("Fixed LoginActivity completely")
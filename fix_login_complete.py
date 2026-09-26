#!/usr/bin/env python3
"""
Fix LoginActivity.java Phase 2 - careful line-by-line processing
"""

with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'r') as f:
    lines = f.readlines()

# Import replacements dictionary
import_replacements = {
    'import com.android.billingclient.api.BillingClient;': '// import com.android.billingclient.api.BillingClient; (Phase 2)',
    'import com.android.billingclient.api.BillingFlowParams;': '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)',
    'import com.android.billingclient.api.ProductDetails;': '// import com.android.billingclient.api.ProductDetails; (Phase 2)',
    'import com.android.billingclient.api.Purchase;': '// import com.android.billingclient.api.Purchase; (Phase 2)',
    'import com.android.billingclient.api.QueryProductDetailsParams;': '// import com.android.billingclient.api.QueryProductDetailsParams; (Phase 2)',
    'import com.google.android.gms.auth.api.signin.GoogleSignIn;': 'import com.tech.ayugram.play.stub.GoogleSignIn;',
    'import com.google.android.gms.auth.api.signin.GoogleSignInAccount;': 'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount;',
    'import com.google.android.gms.auth.api.signin.GoogleSignInClient;': 'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient;',
    'import com.google.android.gms.auth.api.signin.GoogleSignInOptions;': 'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions;',
    'import com.google.android.gms.common.api.ApiException;': 'import com.tech.ayugram.play.stub.ApiException;',
    'import com.google.android.gms.safetynet.SafetyNet;': 'import com.tech.ayugram.play.stub.SafetyNet;',
    'import com.google.android.gms.tasks.Task;': 'import com.tech.ayugram.play.stub.Task;',
    'import com.google.android.play.core.integrity.IntegrityManager;': 'import com.tech.ayugram.play.stub.IntegrityManager;',
    'import com.google.android.play.core.integrity.IntegrityManagerFactory;': 'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory;',
    'import com.google.android.play.core.integrity.IntegrityTokenRequest;': 'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest;',
    'import com.google.android.play.core.integrity.IntegrityTokenResponse;': 'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse;',
    'import com.tech.ayugram.messenger.BillingController;': '// import com.tech.ayugram.messenger.BillingController; (Phase 2)',
}

# Code body replacements - exact string replacements to avoid cascading
code_replacements = [
    # SafetyNet
    ('SafetyNet.getClient(ApplicationLoader.applicationContext).attest(', 'com.tech.ayugram.play.stub.SafetyNet.getClient(ApplicationLoader.applicationContext).attest('),
    ('SafetyNet.getClient(', 'com.tech.ayugram.play.stub.SafetyNet.getClient('),
    
    # IntegrityManager
    ('IntegrityManagerFactory.create(', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create('),
    ('IntegrityManagerFactory', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory'),
    ('IntegrityTokenRequest.builder(', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest.builder('),
    ('IntegrityTokenRequest', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest'),
    ('IntegrityTokenResponse', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse'),
    ('IntegrityManagerFactory.create(', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create('),
    ('IntegrityManagerFactory', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory'),
    ('IntegrityManager', 'com.tech.ayugram.play.stub.IntegrityManager'),
    
    # SafetyNet
    ('SafetyNet.getClient(', 'com.tech.ayugram.play.stub.SafetyNet.getClient('),
    ('SafetyNet', 'com.tech.ayugram.play.stub.SafetyNet'),
    
    # GoogleSignIn
    ('GoogleSignIn.getSignedInAccountFromIntent(', 'com.tech.ayugram.play.stub.GoogleSignIn.getSignedInAccountFromIntent('),
    ('GoogleSignIn.getClient(', 'com.tech.ayugram.play.stub.GoogleSignIn.getClient('),
    
    # GoogleSignInAccount
    ('GoogleSignInAccount', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount'),
    
    # GoogleSignInClient
    ('GoogleSignInClient', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient'),
    
    # GoogleSignInOptions
    ('GoogleSignInOptions.Builder()', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions.Builder()'),
    ('GoogleSignInOptions', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions'),
    
    # GoogleSignInClient
    ('GoogleSignInClient', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient'),
    
    # ApiException
    ('ApiException', 'com.tech.ayugram.play.stub.ApiException'),
    
    # SafetyNet
    ('SafetyNet.getClient(', 'com.tech.ayugram.play.stub.SafetyNet.getClient('),
    
    # Task
    ('Task<IntegrityTokenResponse>', 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse>'),
    ('Task<GoogleSignInAccount>', 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount>'),
]

# Read the file
with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'r') as f:
    content = f.read()

# Step 1: Fix imports - exact line matches
import_map = {
    'import com.android.billingclient.api.BillingClient;': '// import com.android.billingclient.api.BillingClient; (Phase 2)',
    'import com.android.billingclient.api.BillingFlowParams;': '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)',
    'import com.android.billingclient.api.ProductDetails;': '// import com.android.billingclient.api.ProductDetails; (Phase 2)',
    'import com.android.billingclient.api.Purchase;': '// import com.android.billingclient.api.Purchase; (Phase 2)',
    'import com.android.billingclient.api.QueryProductDetailsParams;': '// import com.android.billingclient.api.QueryProductDetailsParams; (Phase 2)',
    'import com.google.android.gms.auth.api.signin.GoogleSignIn;': 'import com.tech.ayugram.play.stub.GoogleSignIn;',
    'import com.google.android.gms.auth.api.signin.GoogleSignInAccount;': 'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount;',
    'import com.google.android.gms.auth.api.signin.GoogleSignInClient;': 'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient;',
    'import com.google.android.gms.auth.api.signin.GoogleSignInOptions;': 'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions;',
    'import com.google.android.gms.common.api.ApiException;': 'import com.tech.ayugram.play.stub.ApiException;',
    'import com.google.android.gms.safetynet.SafetyNet;': 'import com.tech.ayugram.play.stub.SafetyNet;',
    'import com.google.android.gms.tasks.Task;': 'import com.tech.ayugram.play.stub.Task;',
    'import com.google.android.play.core.integrity.IntegrityManager;': 'import com.tech.ayugram.play.stub.IntegrityManager;',
    'import com.google.android.play.core.integrity.IntegrityManagerFactory;': 'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory;',
    'import com.google.android.play.core.integrity.IntegrityTokenRequest;': 'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest;',
    'import com.google.android.play.core.integrity.IntegrityTokenResponse;': 'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse;',
    'import com.tech.ayugram.messenger.BillingController;': '// import com.tech.ayugram.messenger.BillingController; (Phase 2)',
}

# Process line by line
lines = []
with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'r') as f:
    for line in f:
        stripped = line.strip()
        
        # Check import lines first
        if stripped in [
            'import com.android.billingclient.api.BillingClient;',
            'import com.android.billingclient.api.BillingFlowParams;',
            'import com.android.billingclient.api.ProductDetails;',
            'import com.android.billingclient.api.Purchase;',
            'import com.android.billingclient.api.QueryProductDetailsParams;',
            'import com.google.android.gms.auth.api.signin.GoogleSignIn;',
            'import com.google.android.gms.auth.api.signin.GoogleSignInAccount;',
            'import com.google.android.gms.auth.api.signin.GoogleSignInClient;',
            'import com.google.android.gms.auth.api.signin.GoogleSignInOptions;',
            'import com.google.android.gms.common.api.ApiException;',
            'import com.google.android.gms.safetynet.SafetyNet;',
            'import com.google.android.gms.tasks.Task;',
            'import com.google.android.play.core.integrity.IntegrityManager;',
            'import com.google.android.play.core.integrity.IntegrityManagerFactory;',
            'import com.google.android.play.core.integrity.IntegrityTokenRequest;',
            'import com.google.android.play.core.integrity.IntegrityTokenResponse;',
            'import com.tech.ayugram.messenger.BillingController;',
        ]:
            line = {
                'import com.android.billingclient.api.BillingClient;': '// import com.android.billingclient.api.BillingClient; (Phase 2)\n',
                'import com.android.billingclient.api.BillingFlowParams;': '// import com.android.billingclient.api.BillingFlowParams; (Phase 2)\n',
                'import com.android.billingclient.api.ProductDetails;': '// import com.android.billingclient.api.ProductDetails; (Phase 2)\n',
                'import com.android.billingclient.api.Purchase;': '// import com.android.billingclient.api.Purchase; (Phase 2)\n',
                'import com.android.billingclient.api.QueryProductDetailsParams;': '// import com.android.billingclient.api.QueryProductDetailsParams; (Phase 2)\n',
                'import com.google.android.gms.auth.api.signin.GoogleSignIn;': 'import com.tech.ayugram.play.stub.GoogleSignIn;\n',
                'import com.google.android.gms.auth.api.signin.GoogleSignInAccount;': 'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount;\n',
                'import com.google.android.gms.auth.api.signin.GoogleSignInClient;': 'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient;\n',
                'import com.google.android.gms.auth.api.signin.GoogleSignInOptions;': 'import com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions;\n',
                'import com.google.android.gms.common.api.ApiException;': 'import com.tech.ayugram.play.stub.ApiException;\n',
                'import com.google.android.gms.safetynet.SafetyNet;': 'import com.tech.ayugram.play.stub.SafetyNet;\n',
                'import com.google.android.gms.tasks.Task;': 'import com.tech.ayugram.play.stub.Task;\n',
                'import com.google.android.play.core.integrity.IntegrityManager;': 'import com.tech.ayugram.play.stub.IntegrityManager;\n',
                'import com.google.android.play.core.integrity.IntegrityManagerFactory;': 'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory;\n',
                'import com.google.android.play.core.integrity.IntegrityTokenRequest;': 'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest;\n',
                'import com.google.android.play.core.integrity.IntegrityTokenResponse;': 'import com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse;\n',
                'import com.tech.ayugram.messenger.BillingController;': '// import com.tech.ayugram.messenger.BillingController; (Phase 2)\n',
            }[stripped]
        else:
            line = line

        # For code body, we need to be more careful
        # Skip lines that are imports, comments, or empty
        if stripped.startswith('import ') or stripped.startswith('package ') or stripped.startswith('import static ') or stripped.startswith('//') or stripped.startswith('/*') or stripped == '':
            pass  # Don't modify
        else:
            # Apply code body replacements - only on non-import, non-comment lines
            if not stripped.startswith('import ') and not stripped.startswith('package ') and not stripped.startswith('import static ') and not stripped.startswith('//') and not stripped.startswith('/*') and stripped != '':
                # Apply code replacements carefully
                if 'SafetyNet.getClient(ApplicationLoader.applicationContext).attest(' in line:
                    line = line.replace('SafetyNet.getClient(ApplicationLoader.applicationContext).attest(', 'com.tech.ayugram.play.stub.SafetyNet.getClient(ApplicationLoader.applicationContext).attest(')
                elif 'SafetyNet.getClient(' in line and 'com.tech.ayugram.play.stub.SafetyNet.getClient' not in line:
                    line = line.replace('SafetyNet.getClient(', 'com.tech.ayugram.play.stub.SafetyNet.getClient(')
                elif 'SafetyNet' in line and 'com.tech.ayugram.play.stub.SafetyNet' not in line and not line.strip().startswith('//'):
                    line = line.replace('SafetyNet', 'com.tech.ayugram.play.stub.SafetyNet')
                elif 'IntegrityManagerFactory.create(' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create' not in line:
                    line = line.replace('IntegrityManagerFactory.create(', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create(')
                elif 'IntegrityManagerFactory' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory' not in line and 'IntegrityManagerFactory' in line and not line.strip().startswith('//'):
                    line = line.replace('IntegrityManagerFactory', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory')
                elif 'IntegrityTokenRequest.builder(' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest.builder' not in line:
                    line = line.replace('IntegrityTokenRequest.builder(', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest.builder(')
                elif 'IntegrityTokenRequest' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest' not in line and 'IntegrityTokenRequest' in line and not line.strip().startswith('//'):
                    line = line.replace('IntegrityTokenRequest', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenRequest')
                elif 'IntegrityTokenResponse' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse' not in line and 'IntegrityTokenResponse' in line and not line.strip().startswith('//'):
                    line = line.replace('IntegrityTokenResponse', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse')
                elif 'IntegrityManagerFactory.create(' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create' not in line:
                    line = line.replace('IntegrityManagerFactory.create(', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory.create(')
                elif 'IntegrityManagerFactory' in line and 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory' not in line and 'IntegrityManagerFactory' in line and not line.strip().startswith('//'):
                    line = line.replace('IntegrityManagerFactory', 'com.tech.ayugram.play.stub.IntegrityManager.IntegrityManagerFactory')
                elif 'IntegrityManager' in line and 'com.tech.ayugram.play.stub.IntegrityManager' not in line and 'IntegrityManager' in line and not line.strip().startswith('//'):
                    line = line.replace('IntegrityManager', 'com.tech.ayugram.play.stub.IntegrityManager')
                elif 'SafetyNet.getClient(' in line and 'com.tech.ayugram.play.stub.SafetyNet.getClient' not in line:
                    line = line.replace('SafetyNet.getClient(', 'com.tech.ayugram.play.stub.SafetyNet.getClient(')
                elif 'SafetyNet' in line and 'com.tech.ayugram.play.stub.SafetyNet' not in line and not line.strip().startswith('//'):
                    line = line.replace('SafetyNet', 'com.tech.ayugram.play.stub.SafetyNet')
                elif 'GoogleSignIn.getSignedInAccountFromIntent(' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.getSignedInAccountFromIntent' not in line:
                    line = line.replace('GoogleSignIn.getSignedInAccountFromIntent(', 'com.tech.ayugram.play.stub.GoogleSignIn.getSignedInAccountFromIntent(')
                elif 'GoogleSignIn.getClient(' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.getClient' not in line:
                    line = line.replace('GoogleSignIn.getClient(', 'com.tech.ayugram.play.stub.GoogleSignIn.getClient(')
                elif 'GoogleSignInAccount' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount' not in line:
                    line = line.replace('GoogleSignInAccount', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount')
                elif 'GoogleSignInClient' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient' not in line:
                    line = line.replace('GoogleSignInClient', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient')
                elif 'GoogleSignInOptions.Builder()' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions.Builder()' not in line:
                    line = line.replace('GoogleSignInOptions.Builder()', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions.Builder()')
                elif 'GoogleSignInOptions' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions' not in line and 'GoogleSignInOptions' in line and not line.strip().startswith('//'):
                    line = line.replace('GoogleSignInOptions', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInOptions')
                elif 'GoogleSignInClient' in line and 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient' not in line:
                    line = line.replace('GoogleSignInClient', 'com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInClient')
                elif 'ApiException' in line and 'com.tech.ayugram.play.stub.ApiException' not in line:
                    line = line.replace('ApiException', 'com.tech.ayugram.play.stub.ApiException')
                elif 'SafetyNet.getClient(' in line and 'com.tech.ayugram.play.stub.SafetyNet.getClient' not in line:
                    line = line.replace('SafetyNet.getClient(', 'com.tech.ayugram.play.stub.SafetyNet.getClient(')
                elif 'SafetyNet' in line and 'com.tech.ayugram.play.stub.SafetyNet' not in line and not line.strip().startswith('//'):
                    line = line.replace('SafetyNet', 'com.tech.ayugram.play.stub.SafetyNet')
                elif 'Task<IntegrityTokenResponse>' in line and 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse>' not in line:
                    line = line.replace('Task<IntegrityTokenResponse>', 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.IntegrityManager.IntegrityTokenResponse>')
                elif 'Task<GoogleSignInAccount>' in line and 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount>' not in line:
                    line = line.replace('Task<GoogleSignInAccount>', 'com.tech.ayugram.play.stub.Task<com.tech.ayugram.play.stub.GoogleSignIn.GoogleSignInAccount>')

# Write the fixed content
with open('/home/tech/ayugram/TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'w') as f:
    f.write(content)

print("Fixed LoginActivity completely")
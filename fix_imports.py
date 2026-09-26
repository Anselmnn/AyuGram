with open('TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'r') as f:
    lines = f.readlines()

# Import replacements
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

new_lines = []
in_imports = True

for line in open('TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'r'):
    stripped = line.strip()
    
    # Check if we're still in the import section
    if stripped.startswith('import ') or stripped == '':
        # Apply import replacements
        replaced = False
        for old, new in {
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
        }.items():
            if line.strip() == old:
                line = new + '\n'
                break
    elif stripped and not stripped.startswith('//') and not stripped.startswith('/*') and in_imports:
        in_imports = False
    
    new_lines.append(line)

with open('TMessagesProj/src/main/java/com/tech/ayugram/ui/LoginActivity.java', 'w') as f:
    f.writelines(new_lines)

print("Fixed imports only")
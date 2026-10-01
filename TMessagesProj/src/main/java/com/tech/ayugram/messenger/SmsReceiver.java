package com.tech.ayugram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import androidx.annotation.Keep;

@Keep
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage message : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String sender = message.getOriginatingAddress();
                String body = message.getMessageBody();
                
                // Check if it's a Telegram login code
                if (isTelegramCode(sender, body)) {
                    // Handle login code
                    handleLoginCode(body);
                    // Abort broadcast to prevent other apps from reading
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        abortBroadcast();
                    }
                }
            }
        }
    }

    private boolean isTelegramCode(String sender, String body) {
        // Check if sender is Telegram or message contains code pattern
        return sender != null && (sender.contains("Telegram") || sender.contains("TG")) 
            && body != null && body.matches(".*\\d{5,}.*");
    }

    private void handleLoginCode(String body) {
        // Extract code and pass to login activity
        // Implementation would notify the login flow
    }
}
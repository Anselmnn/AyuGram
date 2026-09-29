package com.tech.ayugram.pay.stub;

import android.content.Context;
import com.tech.ayugram.play.stub.Task;

public class PaymentsClient {
    public static PaymentsClient getPaymentsClient(Context context, Wallet.WalletOptions walletOptions) {
        return new PaymentsClient();
    }

    public com.tech.ayugram.play.stub.Task<Boolean> isReadyToPay(IsReadyToPayRequest request) {
        return com.tech.ayugram.play.stub.Task.forResult(false);
    }

    public com.tech.ayugram.play.stub.Task<PaymentData> loadPaymentData(PaymentDataRequest request) {
        return com.tech.ayugram.play.stub.Task.forResult(new PaymentData());
    }
}

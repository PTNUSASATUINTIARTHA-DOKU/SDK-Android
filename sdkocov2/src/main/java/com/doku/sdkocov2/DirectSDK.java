package com.doku.sdkocov2;

import android.content.Context;
import android.content.Intent;
import com.doku.sdkocov2.interfaces.iPaymentCallback;
import com.doku.sdkocov2.model.LayoutItems;
import com.doku.sdkocov2.model.PaymentItems;
import com.doku.sdkocov2.model.UserDetails;
import com.doku.sdkocov2.model.WalletLoginModel;
import com.doku.sdkocov2.utils.SDKUtils;
import java.util.ArrayList;

/**
 * Created by zaki on 2/17/16.
 */
@Deprecated
public class DirectSDK {

    @Deprecated
    public static Context context;

    @Deprecated
    public static PaymentItems paymentItems;

    @Deprecated
    public static WalletLoginModel loginModel = new WalletLoginModel();

    @Deprecated
    public static iPaymentCallback callbackResponse;

    @Deprecated
    public static UserDetails userDetails = new UserDetails();

    @Deprecated
    public static LayoutItems layoutItems = new LayoutItems();

    @Deprecated
    public static int posMenu = 0;

    @Deprecated
    public static String jsonResponse;

    @Deprecated
    public boolean dataNotValid = false;

    @Deprecated
    ArrayList<String> errorList = new ArrayList<>();

    public DirectSDK() {}

    @Deprecated
    private boolean checkDataValidation() {
        boolean cancel = false;
        try {
            if (paymentItems.getDataMerchantCode() == null) {
                errorList.add("Merchant Code");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getDataAmount() == null) {
                errorList.add("Amount");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getDataBasket() == null) {
                errorList.add("Basket");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getDataCurrency() == null) {
                errorList.add("Currency");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getDataMerchantChain() == null) {
                errorList.add("Merchant Chain");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getDataSessionID() == null) {
                errorList.add("Session ID");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getDataTransactionID() == null) {
                errorList.add("Transaction ID");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getDataWords() == null) {
                errorList.add("Words");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getMobilePhone() == null) {
                errorList.add("Mobile Phone");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getPublicKey() == null) {
                errorList.add("Public Key");
                dataNotValid = true;
                cancel = true;
            } else if (paymentItems.getIsProduction() == null) {
                errorList.add("Merchant Status");
                dataNotValid = true;
                cancel = true;
            }

            if (cancel) {
                callbackResponse.onError("Data : " + errorList.toString() + " is Required!");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            callbackResponse.onException(e);
        }
        return false;
    }

    @Deprecated
    public void getResponse(iPaymentCallback callback, Context context) {
        this.context = context;
        callbackResponse = callback;
        if (callback != null && this.getPaymentItems() != null) {
            try {
                if (checkDataValidation()) {
                    Intent intent = new Intent(context, BaseSDKOCO.class);
                    intent.putExtra("payChan", posMenu);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                callback.onException(e);
            }
        } else {
            callback.onError(SDKUtils.createErrorResponse(200, "payment item required"));
        }
    }

    @Deprecated
    public void setCart_details(PaymentItems cardDetails) {
        this.paymentItems = cardDetails;
    }

    @Deprecated
    public PaymentItems getPaymentItems() {
        return this.paymentItems;
    }

    @Deprecated
    public void setPaymentChannel(int posMenu) {
        this.posMenu = posMenu;
    }

    @Deprecated
    public void setLayout(LayoutItems itemDetails) {
        this.layoutItems = itemDetails;
    }

}

package com.example.mysqldatabaseapp;

import androidx.annotation.NonNull;

public class Receipt {

    private int receiptID;
    private String receiptTitle;
    private String receiptPrice;

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public String getReceiptTitle() {
        return receiptTitle;
    }

    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    public String getReceiptPrice() {
        return receiptPrice;
    }

    public void setReceiptPrice(String receiptPrice) {
        this.receiptPrice = receiptPrice;
    }

    public Receipt(String receiptTitle, String receiptPrice) {
        this.receiptTitle = receiptTitle;
        this.receiptPrice = receiptPrice;
    }

    @NonNull
    @Override
    public String toString() {
        return receiptID + ": " + receiptTitle + ": " + receiptPrice + "\n";
    }
}

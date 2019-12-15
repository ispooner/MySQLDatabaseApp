package com.example.mysqldatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ReceiptDatabaseHelper receiptDatabase;

    @BindView(R.id.textView)
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        receiptDatabase = new ReceiptDatabaseHelper(this, "", null, 0);

        receiptDatabase.insertReceipt(new Receipt("Battery", "$300"));
        receiptDatabase.insertReceipt(new Receipt("Battery2", "$80"));
        receiptDatabase.insertReceipt(new Receipt("Battery3", "$40"));
        receiptDatabase.insertReceipt(new Receipt("Battery4", "$160"));
        receiptDatabase.insertReceipt(new Receipt("Battery5", "$4000"));
        receiptDatabase.insertReceipt(new Receipt("Battery6", "$3"));

        ArrayList<Receipt> receipts = receiptDatabase.getReceipts();

        StringBuilder builder = new StringBuilder();
        for(Receipt rec : receipts) {
            builder.append(rec.toString());
        }

        textView.setText(builder.toString());

    }


}

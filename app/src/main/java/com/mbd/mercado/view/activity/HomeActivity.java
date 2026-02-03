package com.mbd.mercado.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.mbd.mercado.R;
import com.mbd.mercado.sk.MbdActivity;

public class HomeActivity extends MbdActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.home_activity);

        this.configNavegation();

    }

    private void configNavegation() {
        this.configNavegationProducts();
        this.configNavegationSupplier();
        this.configNavegationCheckout();

    }

    private void configNavegationCheckout() {
        TextView navegationCheckout = this.findViewById(R.id.activity_home_checkout);
    }

    private void configNavegationSupplier() {
        TextView navegationCheckout = this.findViewById(R.id.activity_home_supplier);
    }

    private void configNavegationProducts() {
        this.findViewById(R.id.activity_home_products)
                .setOnClickListener(click -> super.navigateTo(ListProductActivity.class));
    }

}
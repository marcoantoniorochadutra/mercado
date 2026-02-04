package com.mbd.mercado.ui.activity;

import static java.util.Objects.nonNull;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.mbd.mercado.R;
import com.mbd.mercado.sk.MbdActivity;
import com.mbd.mercado.ui.view.ListProductView;

public class ListProductActivity extends MbdActivity {
    private final ListProductView productView;

    public ListProductActivity() {
        this.productView = new ListProductView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.list_product_activity);

        this.configFloatButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listProducts = this.findViewById(R.id.activity_list_product_list);
        this.productView.updateProductList(listProducts);
        this.registerForContextMenu(listProducts);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        this.getMenuInflater().inflate(R.menu.list_product_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();

        this.caseRemoveItem(itemId, menuInfo);
        this.caseUpdateItem(itemId, menuInfo);
        return super.onContextItemSelected(item);
    }

    private void caseUpdateItem(int itemId, AdapterContextMenuInfo menuInfo) {
        if (itemId == R.id.activity_list_product_update && nonNull(menuInfo)) {
            this.productView.goToUpdateProduct(menuInfo);
        }
    }

    private void caseRemoveItem(int itemId, AdapterContextMenuInfo menuInfo) {
        if (itemId == R.id.activity_list_product_delete && nonNull(menuInfo)) {
            this.productView.deleteProduct(menuInfo);
        }
    }

    private void configFloatButton() {
        this.findViewById(R.id.home_activity_float_button)
                .setOnClickListener(view -> super.navigateTo(CreateProductActivity.class));
    }

}

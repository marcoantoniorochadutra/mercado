package com.mbd.mercado.view.activity;

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
import com.mbd.mercado.adapter.out.ProductRepository;
import com.mbd.mercado.domain.Product;
import com.mbd.mercado.sk.MbdActivity;
import com.mbd.mercado.view.adapter.ListProductItemAdapter;

public class ListProductActivity extends MbdActivity {

    private ListProductItemAdapter productAdapter;
    private ListView listProducts;
    private final ProductRepository productRepository = new ProductRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.list_product_activity);

        this.configFloatButton();
        this.initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.atualizarListProducts();
        this.registerForContextMenu(this.listProducts);
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
        if (itemId == R.id.activity_list_product_delete && nonNull(menuInfo)) {
            Product productSelecionado = this.productAdapter.getItem(menuInfo.position);
            if (nonNull(productSelecionado)) {
                this.removeProduct(productSelecionado);
                super.notificarTela("Product deletado com sucesso!");
            }

            return true;
        }

        if (itemId == R.id.activity_list_product_update && nonNull(menuInfo)) {
            Product productSelecionado = this.productAdapter.getItem(menuInfo.position);
            if (nonNull(productSelecionado)) {
                super.navigateToWithExtra(CreateProductActivity.class, "product", productSelecionado);
            }
            return true;
        }


        return super.onContextItemSelected(item);
    }

    private void removeProduct(Product productSelecionado) {
        this.productRepository.delete(productSelecionado);
        this.productAdapter.delete(productSelecionado);

    }

    private void atualizarListProducts() {
        this.productAdapter.clear();
        this.productAdapter.addAll(this.productRepository.findAll());
        this.listProducts.setAdapter(this.productAdapter);
    }

    private void initComponents() {
        this.atualizarAdapterList();
        this.listProducts = this.findViewById(R.id.activity_list_product_list);
    }

    private void atualizarAdapterList() {
        this.productAdapter = new ListProductItemAdapter(this);
    }

    private void configFloatButton() {
        this.findViewById(R.id.home_activity_float_button)
                .setOnClickListener(view -> super.navigateTo(CreateProductActivity.class));
    }

}

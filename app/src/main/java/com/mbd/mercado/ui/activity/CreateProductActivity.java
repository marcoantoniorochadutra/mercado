package com.mbd.mercado.ui.activity;

import static java.util.Objects.nonNull;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mbd.mercado.R;
import com.mbd.mercado.adapter.out.ProductRepository;
import com.mbd.mercado.domain.Product;
import com.mbd.mercado.domain.enums.ProductCategory;
import com.mbd.mercado.sk.MbdActivity;

import java.util.UUID;

public class CreateProductActivity extends MbdActivity {

    private final ProductRepository productRepository = new ProductRepository();

    private ArrayAdapter<ProductCategory> categoriaProductAdapter;
    private Spinner categoryProductDropdown;
    private EditText descriptionProductInput;
    private EditText valueProductInput;
    private Product actualProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.create_products_activity);

        this.initComponents();
    }

    private void initComponents() {
        this.categoriaProductAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        this.categoryProductDropdown = this.findViewById(R.id.create_product_activity_category_dropdown);
        this.descriptionProductInput = this.findViewById(R.id.create_product_activity_description_input);
        this.valueProductInput = this.findViewById(R.id.create_product_activity_value_input);

        Button saveProductButton = this.findViewById(R.id.create_product_activity_save_button);
        saveProductButton.setOnClickListener(button -> this.saveProduct());

        this.updateActualProduct();
    }

    private void updateActualProduct() {
        Product product = super.retrieveObjectFromIntent("product", Product.class);
        if (nonNull(product)) {
            this.actualProduct = product;
            this.descriptionProductInput.setText(product.getDescription());
            this.valueProductInput.setText(product.getValue().toString());
            this.categoryProductDropdown.setSelection(product.getCategory().ordinal());
        }
    }

    private void saveProduct() {

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .category((ProductCategory) this.categoryProductDropdown.getSelectedItem())
                .description(super.inputValueString(this.descriptionProductInput))
                .value(super.inputValueBigDecimal(this.valueProductInput))
                .build();

        if (nonNull(this.actualProduct))
            product.setId(this.actualProduct.getId());

        this.productRepository.save(product);
        notificarTela(this, "Product salvo com sucesso!");
        super.navigateTo(ListProductActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.atualizarCategoriaProducts();
    }

    private void atualizarCategoriaProducts() {
        this.categoriaProductAdapter.clear();
        this.categoriaProductAdapter.addAll(ProductCategory.values());
        this.categoryProductDropdown.setAdapter(this.categoriaProductAdapter);
    }
}

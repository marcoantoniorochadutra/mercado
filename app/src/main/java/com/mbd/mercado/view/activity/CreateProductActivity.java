package com.mbd.mercado.view.activity;

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
    private Spinner categoriaProductDropdown;
    private EditText descricaoProductInput;
    private EditText valorProductInput;
    private Product productAtual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.create_products_activity);

        this.initComponents();
    }


    private void initComponents() {
        this.categoriaProductAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        this.categoriaProductDropdown = this.findViewById(R.id.create_product_activity_category_dropdown);
        this.descricaoProductInput = this.findViewById(R.id.create_product_activity_description_input);
        this.valorProductInput = this.findViewById(R.id.create_product_activity_value_input);

        Button salvarProductButton = this.findViewById(R.id.create_product_activity_save_button);
        salvarProductButton.setOnClickListener(button -> this.salvarProduct());

        this.atualizarProductAtual();
    }

    private void atualizarProductAtual() {
        Product product = super.buscarObjetoIntent("product", Product.class);
        if (nonNull(product)) {
            this.productAtual = product;
            this.descricaoProductInput.setText(product.getDescription());
            this.valorProductInput.setText(product.getValue().toString());
            this.categoriaProductDropdown.setSelection(product.getCategory().ordinal());
        }
    }


    private void salvarProduct() {

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .category((ProductCategory) this.categoriaProductDropdown.getSelectedItem())
                .description(super.inputValueString(this.descricaoProductInput))
                .value(super.inputValueBigDecimal(this.valorProductInput))
                .build();

        if (nonNull(this.productAtual))
            product.setId(this.productAtual.getId());

        this.productRepository.save(product);
        super.notificarTela("Product salvo com sucesso!");
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
        this.categoriaProductDropdown.setAdapter(this.categoriaProductAdapter);
    }
}

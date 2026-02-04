package com.mbd.mercado.ui.view;

import static com.mbd.mercado.sk.MbdActivity.navigateToWithExtra;
import static com.mbd.mercado.sk.MbdActivity.notificarTela;
import static java.util.Objects.nonNull;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.mbd.mercado.adapter.out.ProductRepository;
import com.mbd.mercado.domain.Product;
import com.mbd.mercado.ui.activity.CreateProductActivity;
import com.mbd.mercado.ui.adapter.ListProductItemAdapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListProductView {

    private final ProductRepository productRepository;
    private final ListProductItemAdapter productAdapter;
    private final Context context;

    public ListProductView(Context context) {
        this.productRepository = new ProductRepository();
        this.productAdapter = new ListProductItemAdapter(context);
        this.context = context;
    }

    public void updateProductList(ListView listProducts) {
        this.productAdapter.atualizarItens(this.productRepository.findAll());
        listProducts.setAdapter(this.productAdapter);
    }


    public void goToUpdateProduct(AdapterContextMenuInfo menuInfo) {
        Product productSelecionado = this.productAdapter.getItem(menuInfo.position);
        if (nonNull(productSelecionado)) {
            navigateToWithExtra(this.context, CreateProductActivity.class, "product", productSelecionado);
        }
    }

    public void deleteProduct(AdapterContextMenuInfo menuInfo) {
        Product productSelecionado = this.productAdapter.getItem(menuInfo.position);

        new AlertDialog.Builder(this.context)
                .setTitle("Você deseja mesmo deletar este produto?")
                .setMessage("Esta ação não poderá ser desfeita.")
                .setPositiveButton("Deletar", (dialog, which) -> {
                    this.removeProduct(productSelecionado);
                    notificarTela(this.context, "Product deletado com sucesso!");
                    dialog.dismiss();
                })
                .setNegativeButton("Cancelar", (dialog, which) ->
                        dialog.cancel())
                .create().show();
    }

    private void removeProduct(Product productSelecionado) {
        this.productRepository.delete(productSelecionado);
        this.productAdapter.delete(productSelecionado);
    }
}

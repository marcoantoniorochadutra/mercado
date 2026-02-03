package com.mbd.mercado.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mbd.mercado.R;
import com.mbd.mercado.domain.Product;
import com.mbd.mercado.sk.CustomAdapterBase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListProductItemAdapter extends CustomAdapterBase<Product> {

    private final List<Product> productList = new ArrayList<>();
    private final Locale locale = Locale.forLanguageTag("pt-BR");

    public ListProductItemAdapter(Context context) {
        super(context);
    }

    @Override
    public List<Product> getDataList() {
        return this.productList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View itemAdapterView = LayoutInflater.from(this.context).inflate(R.layout.list_product_item, viewGroup, false);

        Product product = this.getItem(position);
        super.updateTextView(itemAdapterView, R.id.activity_list_product_item_description, product.getDescription());
        super.updateTextView(itemAdapterView, R.id.activity_list_product_item_value, NumberFormat.getCurrencyInstance(this.locale).format(product.getValue()));
        super.updateTextView(itemAdapterView, R.id.activity_list_product_item_category, product.getCategory().getDescricao());
        return itemAdapterView;
    }

}

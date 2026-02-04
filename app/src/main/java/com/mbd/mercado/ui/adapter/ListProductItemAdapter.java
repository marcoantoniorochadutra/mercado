package com.mbd.mercado.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.list_product_item, viewGroup, false);
            holder = new ViewHolder();
            holder.description = convertView.findViewById(R.id.activity_list_product_item_description);
            holder.value = convertView.findViewById(R.id.activity_list_product_item_value);
            holder.category = convertView.findViewById(R.id.activity_list_product_item_category);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = this.getItem(position);
        holder.description.setText(product.getDescription());
        holder.value.setText(NumberFormat.getCurrencyInstance(this.locale).format(product.getValue()));
        holder.category.setText(product.getCategory().getDescricao());

        return convertView;
    }

    private static class ViewHolder {
        TextView description;
        TextView value;
        TextView category;
    }
}

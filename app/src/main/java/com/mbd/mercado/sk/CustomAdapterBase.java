package com.mbd.mercado.sk;

import static java.util.Objects.nonNull;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public abstract class CustomAdapterBase<T> extends BaseAdapter {

    protected CustomAdapterBase(Context context) {
        this.context = context;
    }

    protected final Context context;

    public abstract List<T> getDataList();

    @Override
    public int getCount() {
        return this.getDataList().size();
    }

    @Override
    public T getItem(int position) {
        return this.getDataList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private void clear() {
        this.getDataList().clear();
    }

    private void addAll(List<T> itemsToAdd) {
        this.getDataList().addAll(itemsToAdd);
    }

    public void atualizarItens(List<T> novosItems) {
        this.clear();
        this.addAll(novosItems);
        this.notifyDataSetChanged();
    }

    public void delete(T productToDelete) {
        this.getDataList().remove(productToDelete);
        this.notifyDataSetChanged();
    }

    protected void updateTextView(View view, int id, String textToSet) {
        TextView descriptionProduct = view.findViewById(id);
        if (nonNull(descriptionProduct))
            descriptionProduct.setText(textToSet);
    }
}

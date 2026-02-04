package com.mbd.mercado.sk;

import static java.util.Objects.isNull;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.math.BigDecimal;

public class MbdActivity extends AppCompatActivity {

    public static void notificarTela(Context ctx, String mensagem) {
        Toast.makeText(ctx, mensagem, Toast.LENGTH_LONG).show();
    }

    public static void navigateToWithExtra(Context ctx, Class<?> destino, String key, Serializable value) {
        Intent intent = new Intent(ctx, destino);
        intent.putExtra(key, value);
        ctx.startActivity(intent);
    }

    public static void navigateTo(Context ctx, Class<?> destino) {
        ctx.startActivity(new Intent(ctx, destino));
    }

    public void navigateTo(Class<?> destino) {
        navigateTo(this, destino);
    }


    public BigDecimal inputValueBigDecimal(EditText editText) {
        if (isNull(editText) || editText.getText().toString().isEmpty())
            return BigDecimal.ZERO;


        return new BigDecimal(editText.getText().toString());
    }

    public String inputValueString(EditText editText) {
        if (isNull(editText))
            return "";

        return editText.getText().toString();
    }

    protected <T extends Serializable> T retrieveObjectFromIntent(String key, Class<T> value) {
        Intent intent = super.getIntent();
        return intent.getSerializableExtra(key, value);
    }
}

package com.mbd.mercado.sk;

import static java.util.Objects.isNull;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.math.BigDecimal;

public class MbdActivity extends AppCompatActivity {


    public void navigateTo(Class<?> destino) {
        super.startActivity(new Intent(this, destino));
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

    public void notificarTela(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    protected void navigateToWithExtra(Class<?> destino, String key, Serializable value) {
        Intent intent = new Intent(this, destino);
        intent.putExtra(key, value);

        super.startActivity(intent);
    }

    protected <T extends Serializable> T buscarObjetoIntent(String key, Class<T> value) {
        Intent intent = super.getIntent();
        return intent.getSerializableExtra(key, value);
    }
}

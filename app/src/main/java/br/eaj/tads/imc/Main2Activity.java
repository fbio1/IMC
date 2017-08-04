package br.eaj.tads.imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    boolean controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle params = getIntent().getExtras();

        TextView tx = (TextView) findViewById(R.id.textView11);
        EditText edit = (EditText) findViewById(R.id.editText);

        controle = params.getBoolean("controle");
        if (controle == true){
            tx.setText("Peso: ");
            String valor = params.getString("valor");
            edit.setText(valor);
        }else{
            tx.setText("Altura: ");
            String valor = params.getString("valor");
            edit.setText(valor);
        }

    }

    public void alterarDados(View v){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle params = getIntent().getExtras();
        EditText edit = (EditText) findViewById(R.id.editText);
        params.putString("valor", edit.getText().toString());
        intent.putExtras(params);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelar(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
}

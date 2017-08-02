package br.eaj.tads.imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Bundle params = getIntent().getExtras();

        TextView tx = (TextView) findViewById(R.id.textView11);
        EditText edit = (EditText) findViewById(R.id.editText);

        double valor = 0;

        String controle =  params.getString("controle");

        if (controle.equals("Peso")){
            valor = params.getDouble("valor");
        }else if(controle.equals("Altura")){
            valor = params.getDouble("valor");
        }
        tx.setText(controle);
        edit.setText(""+ valor);
    }

    public void alterarDados(View v){
        Intent intent = new Intent();
        Bundle params = new Bundle();
        EditText edit = (EditText) findViewById(R.id.editText);
        params.putDouble("valor", Double.parseDouble(edit.getText().toString()));
        intent.putExtras(params);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelar(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
}

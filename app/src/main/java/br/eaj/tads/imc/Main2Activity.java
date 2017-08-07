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
    TextView texto;
    EditText editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle params = getIntent().getExtras();

        texto = (TextView) findViewById(R.id.textView11);
        editor = (EditText) findViewById(R.id.editText);


        controle = params.getBoolean("controle");
        //String editValue = params.getString("valor");

        if (controle == true){
            texto.setText("Peso: ");
            String valor = params.getString("valor");
            editor.setText(valor);
        }else{
            texto.setText("Altura: ");
            String valor = params.getString("valor");
            editor.setText(valor);
        }
        //editor.setText(editValue);
    }

    public void alterarDados(View v){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle params = new Bundle();

        params.putString("valor", editor.getText().toString());
        intent.putExtras(params);

        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelar(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
}

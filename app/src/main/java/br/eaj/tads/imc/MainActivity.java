package br.eaj.tads.imc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public  static  final int PESO = 1;
    public  static  final int ALTURA = 2;

    public static final String TV_PESO = "TV_PESO";
    public static final String TV_ALTURA = "TV_ALTURA";
    public static final String TV_RESULTADO= "TV_RESULTADO";
    private static final String TV_MENSAGEM = "TV_MENSAGEM";
    private static final String PREFS_NAME = "IMC_PREFS";

    TextView peso;
    TextView altura;
    TextView resultado;
    TextView mensagem1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peso = (TextView) findViewById(R.id.textView3);
        altura = (TextView) findViewById(R.id.textView6);
        resultado = (TextView) findViewById(R.id.textView9);
        mensagem1 = (TextView) findViewById(R.id.textView12);


//        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE );
//        String peso_text = settings.getString(TV_PESO, "0.0");
//        String altura_text = settings.getString(TV_ALTURA, "0.0");
//        String result_text = settings.getString(TV_RESULTADO, "");
//        String mensagem_text = settings.getString(TV_MENSAGEM, "");

//        peso.setText(peso_text);
//        altura.setText(altura_text);
//        resultado.setText(result_text);
//        mensagem1.setText(mensagem_text);
    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.putString(TV_PESO, peso.getText().toString());
//        editor.putString(TV_ALTURA, altura.getText().toString());
//        editor.putString(TV_RESULTADO, resultado.getText().toString());
//        editor.putString(TV_MENSAGEM, mensagem1.getText().toString());
//
//        editor.commit();
//    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TV_PESO, peso.getText().toString());
        outState.putString(TV_ALTURA, altura.getText().toString());
        outState.putString(TV_RESULTADO, resultado.getText().toString());
        outState.putString(TV_MENSAGEM, mensagem1.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        peso.setText(savedInstanceState.getString(TV_PESO));
        altura.setText(savedInstanceState.getString(TV_ALTURA));
        resultado.setText(savedInstanceState.getString(TV_RESULTADO));
        mensagem1.setText(savedInstanceState.getString(TV_MENSAGEM));
    }


    public void alterarPeso(View v){
        Intent intent = new Intent(this,Main2Activity.class);
        Bundle params = new Bundle();

        params.putString("valor", peso.getText().toString());
        params.putBoolean("controle", true);

        intent.putExtras(params);
        startActivityForResult(intent, PESO);
    }

    public void alterarAltura(View v){
        Intent intent = new Intent(this,Main2Activity.class);
        Bundle params = new Bundle();

        params.putString("valor",altura.getText().toString());
        params.putBoolean("controle", false);

        intent.putExtras(params);
        startActivityForResult(intent, ALTURA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PESO) {
            if (resultCode == RESULT_OK) {
                Bundle params = data.getExtras();
                String valor = params.getString("valor");
                peso.setText(valor);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == ALTURA){
            if(resultCode == RESULT_OK){
                Bundle params = data.getExtras();
                String valor = params.getString("valor");
                altura.setText(valor);
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void calcular(View v){
        Double num_peso = Double.parseDouble(peso.getText().toString());
        Double num_altura = Double.parseDouble(altura.getText().toString());

        Double IMC = num_peso / (num_altura * num_altura);

        String mensagem = "";
        if(IMC < 16){
            mensagem = "Magreza Grave";
        }else if(IMC > 16 && IMC <17){
            mensagem = "Magreza moderada";
        }else if(IMC > 17 && IMC <18.5){
            mensagem = "Magreza leve";
        }else if(IMC > 18.5 && IMC <25){
            mensagem = "Saudavel";
        }else if(IMC > 25 && IMC <30){
            mensagem = "Sobrepeso";
        }else if(IMC > 30 && IMC <35){
            mensagem = "Obesidade grau I";
        }else if(IMC > 35 && IMC <40){
            mensagem = "Obesidade grau II";
        }else if(IMC > 40){
            mensagem = "Obesidade grau III morbida";
        }

        resultado.setText(String.format("Seu IMC é: %.2f " , IMC));

        mensagem1.setText("Seu grau é: " + mensagem);

    }
}

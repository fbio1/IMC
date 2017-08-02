package br.eaj.tads.imc;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;

public class MainActivity extends AppCompatActivity {
    public  static  final int peso = 1;
    public  static  final int altura = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alterarPeso(View v){
        Intent intent = new Intent(this,Main2Activity.class);
        Bundle params = new Bundle();

        TextView tx = (TextView) findViewById(R.id.textView3);
        String controle = "Peso";

        params.putDouble("tipo", Double.parseDouble(tx.getText().toString()));
        params.putString("controle",controle);

        intent.putExtras(params);
        startActivityForResult(intent, peso);

    }

    public void alterarAltura(View v){
        Intent intent = new Intent(this,Main2Activity.class);
        Bundle params = new Bundle();

        TextView tx1 = (TextView) findViewById(R.id.textView6);
        String controle = "Altura";

        params.putDouble("valor",Double.parseDouble(tx1.getText().toString()));
        params.putString("controle",controle);

        intent.putExtras(params);
        startActivityForResult(intent, altura);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == peso) {
            if (resultCode == RESULT_OK) {
                Bundle params = data.getExtras();
                double valor = params.getDouble("valor");
                TextView tx1 = (TextView) findViewById(R.id.textView3);
                tx1.setText(""+valor);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == altura){
            if(resultCode == RESULT_OK){
                Bundle params = data.getExtras();
                double valor = params.getDouble("valor");
                TextView tx1 = (TextView) findViewById(R.id.textView6);
                tx1.setText(""+valor);
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void calcular(View v){
        TextView peso = (TextView) findViewById(R.id.textView3);
        TextView altura = (TextView) findViewById(R.id.textView6);
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

        TextView resultado1 = (TextView) findViewById(R.id.textView9);

        resultado1.setText(String.format("Seu IMC é: %.2f " , IMC));
        TextView resultado2 = (TextView) findViewById(R.id.textView12);
        resultado2.setText("Seu Grau é: " + mensagem);

    }
}

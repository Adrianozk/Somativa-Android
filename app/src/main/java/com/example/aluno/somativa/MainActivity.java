package com.example.aluno.somativa;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declaração de variáveis
    private TextView txtNome, txtTelefone, txtData, txtHorario;
    private RadioButton masculino, feminino;
    private RadioGroup rgSexo;
    private Switch swNotificacao;
    private Button btnGravar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vinculando os componentes do layout com as variáveis referenciadas
        txtNome = (TextView) findViewById(R.id.txtNome);
        txtTelefone = (TextView) findViewById(R.id.txtTelefone);
        txtData = (TextView) findViewById(R.id.txtData);
        txtHorario = (TextView) findViewById(R.id.txtHorario);

        masculino = (RadioButton) findViewById(R.id.rbMasculino);
        feminino = (RadioButton) findViewById(R.id.rbFeminino);

        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);

        btnGravar = (Button) findViewById(R.id.btnGravar);

        swNotificacao = (Switch) findViewById(R.id.swNotificacao);

        //Ação do botão Gravar
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Utilização do SharedPreferences para gravar o conteúdo no arquivo de configuração
                SharedPreferences.Editor gravar = getSharedPreferences("nome_config", MODE_PRIVATE).edit();

                //Informando os campos a serem gravados no SharedPreferences
                gravar.putString("nome", txtNome.getText().toString());
                gravar.putString("telefone", txtTelefone.getText().toString());
                gravar.putString("data", txtData.getText().toString());
                gravar.putString("horario", txtHorario.getText().toString());

                //Testa se o Switch de notificação está marcado e informa se deve ser verdadeiro
                if (swNotificacao.isChecked()) {
                    gravar.putBoolean("notificacao", true);
                }else
                {
                    gravar.putBoolean("notificacao", false);
                }

                //Testa se o RadioButton masculino está marcado e informa se deve ser verdadeiro
                if (masculino.isChecked()) {
                    gravar.putBoolean("masculino", true);
                }

                //Testa se o RadioButton feminino está marcado e informa se deve ser verdadeiro
                if (feminino.isChecked()) {
                    gravar.putBoolean("feminino", true);
                }

                //Try catch para informar caso ocorra erro
                try {
                    //Gravando os valores no arquivo "nome_config"
                    gravar.commit();
                } catch (Exception ex) {
                    //Informando o erro através do Toast
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                //Informando o sucesso na gravação através do Toast
                Toast.makeText(MainActivity.this, "Valores salvos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        //Try catch para informar caso ocorra erro
        try {
            //Utilização do SharedPreferences para ler o conteúdo no arquivo de configuração
            SharedPreferences leitor = getSharedPreferences("nome_config", MODE_PRIVATE);
            //Informando os campos a serem lidos do SharedPreferences
            txtNome.setText(leitor.getString("nome", txtNome.getText().toString()));
            txtTelefone.setText(leitor.getString("telefone", txtTelefone.getText().toString()));
            txtData.setText(leitor.getString("data", txtData.getText().toString()));
            txtHorario.setText(leitor.getString("horario", txtHorario.getText().toString()));

            boolean notificacao = leitor.getBoolean("notificacao", false);
            boolean masc = leitor.getBoolean("masculino", false);
            boolean fem = leitor.getBoolean("feminino", false);

            //Testa se o Switch de notificação está marcado e informa se deve ser verdadeiro
            if (notificacao) {
                swNotificacao.setChecked(true);
            } else {
                swNotificacao.setChecked(false);
            }

            //Testa se o RadioButton masculino está marcado e informa se deve ser verdadeiro
            if (masc) {
                masculino.setChecked(true);
            } else {
                masculino.setChecked(false);
            }

            //Testa se o RadioButton feminino está marcado e informa se deve ser verdadeiro
            if (fem) {
                feminino.setChecked(true);
            } else {
                feminino.setChecked(false);
            }
        } catch (Exception ex) {
            //Informando o erro através do Toast
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        //Informando o sucesso na gravação através do Toast
        Toast.makeText(MainActivity.this, "Valores salvos", Toast.LENGTH_SHORT).show();
    }
}


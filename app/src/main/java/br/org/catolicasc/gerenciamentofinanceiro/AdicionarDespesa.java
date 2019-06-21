package br.org.catolicasc.gerenciamentofinanceiro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdicionarDespesa extends AppCompatActivity {

    private Spinner spinner2;
    private Button btnAddDespesa;
    private EditText edtNomeDesp;
    private EditText edtValorDesp;
    private EditText edtDataDesp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_despesa);

        edtNomeDesp = findViewById(R.id.edtNomeDesp);
        edtValorDesp = findViewById(R.id.edtValorDesp);
        edtDataDesp = findViewById(R.id.edtDataDesp);

        Spinner spinner = findViewById(R.id.spinner2);
        List<String> categorias = new ArrayList<>(Arrays.asList("Contas","Carro","Lazer","Mercado"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorias);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

}

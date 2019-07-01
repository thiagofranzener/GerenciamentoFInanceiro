package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.Intent;
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

    private DataBaseUsuario mDatabaseUsuario;
    private DataBaseDespesa mDatabaseDespesa;
    private Spinner spinner2;
    private Button btnAddDespesa;
    private EditText edtNomeDesp;
    private EditText edtValorDesp;
    private EditText edtDataDesp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_despesa);

        mDatabaseUsuario = new DataBaseUsuario(this);
        mDatabaseDespesa = new DataBaseDespesa(this);

        edtNomeDesp = findViewById(R.id.edtNomeDesp);
        edtValorDesp = findViewById(R.id.edtValorDesp);
        edtDataDesp = findViewById(R.id.edtDataDesp);
        btnAddDespesa = findViewById(R.id.btnAddDespesa);

        final Spinner spinner = findViewById(R.id.spinner2);
        List<String> categorias = new ArrayList<>(Arrays.asList("Contas","Carro","Lazer","Mercado"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorias);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        View.OnClickListener salvar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nome = edtNomeDesp.getText().toString();
                    Double valor = Double.parseDouble(edtValorDesp.getText().toString());
                    String data = edtDataDesp.getText().toString();
                    String usuario = mDatabaseUsuario.getLoggedUser();
                    String categoria = spinner.getSelectedItem().toString();

                    if(mDatabaseDespesa.addData(usuario,nome,categoria,valor,data)) {
                        mostraMensagem("Despesa salva com sucesso!");
                    } else {
                        mostraMensagem("Erro ao salvar despesa!");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    mostraMensagem("Erro ao salvar despesa!");
                }
            }
        };

        btnAddDespesa.setOnClickListener(salvar);
    }

    private void mostraMensagem(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

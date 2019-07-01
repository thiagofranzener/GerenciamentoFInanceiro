package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarReceita extends AppCompatActivity {

    private DataBaseUsuario mDatabaseUsuario;
    private DataBaseReceita mDatabaseReceita;

    private Button btnAddReceita;
    private EditText edtDescricaoRec;
    private EditText edtValorRec;
    private EditText edtDataRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_receita);

        mDatabaseUsuario = new DataBaseUsuario(this);
        mDatabaseReceita = new DataBaseReceita(this);

        edtDescricaoRec = findViewById(R.id.edtDescricaoRec);
        edtValorRec = findViewById(R.id.edtValorRec);
        edtDataRec = findViewById(R.id.edtDataRec);
        btnAddReceita = findViewById(R.id.btnAddReceita);

        View.OnClickListener salvar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String descricao = edtDescricaoRec.getText().toString();
                    Double valor = Double.parseDouble(edtValorRec.getText().toString());
                    String data = edtDataRec.getText().toString();
                    String usuario = mDatabaseUsuario.getLoggedUser();

                  if(mDatabaseReceita.addData(usuario,descricao,valor,data)) {
                      mostraMensagem("Receita salva com sucesso!");
                  } else {
                      mostraMensagem("Erro ao salvar receita!");
                  }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    mostraMensagem("Erro ao salvar receita!");
                }

            }
        };

        btnAddReceita.setOnClickListener(salvar);
    }

    private void mostraMensagem(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

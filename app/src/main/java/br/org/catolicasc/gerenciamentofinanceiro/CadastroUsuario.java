package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity {

    private DataBaseUsuario mDatabaseUsuario;
    private Button btnAddUsuario;
    private EditText edtNome;
    private EditText edtDataNasc;
    private EditText edtCPF;
    private EditText edtEmail;
    private EditText edtSenha;
    private EditText edtConfSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuario);

        edtNome = findViewById(R.id.edtNomeDesp);
        edtDataNasc = findViewById(R.id.edtDataNasc);
        edtCPF = findViewById(R.id.edtCPF);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfSenha = findViewById(R.id.edtConfSenha);

        mDatabaseUsuario = new DataBaseUsuario(this);
        btnAddUsuario = findViewById(R.id.btnAddUsuario);
        btnAddUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String nome = edtNome.getText().toString();
                    String dataNasc = edtDataNasc.getText().toString();
                    String cpf = edtCPF.getText().toString();
                    String email = edtEmail.getText().toString();
                    String senha = edtSenha.getText().toString();
                    String confSenha = edtConfSenha.getText().toString();

                    if (!senha.equals(confSenha)){
                        mostraMensagem("Senhas não conferem!");
                        return;
                    }

                    if(mDatabaseUsuario.addData(nome,dataNasc,cpf,email,senha, false)){
                        mostraMensagem("Usuário salvo com sucesso!");
                        openMainActivity();
                    } else {
                        mostraMensagem("Erro ao salvar usuário!");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    mostraMensagem("Erro ao salvar usuário!");
                }
            }
        });
    }

    private void mostraMensagem(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void openMainActivity() {
        MainActivity ble = new MainActivity();
        ble.populateListView();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

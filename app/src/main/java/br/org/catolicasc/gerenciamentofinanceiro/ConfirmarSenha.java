package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConfirmarSenha extends AppCompatActivity {

    private DataBaseUsuario mDatabaseUsuario;
    private Button btnEntrarMenu;
    private EditText edtConfirmarSenha;
    private TextView txtNomeUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_senha);

        btnEntrarMenu = findViewById(R.id.btnEntrarMenu);
        edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);
        txtNomeUsuario = findViewById(R.id.txtNomeUsuario);

        mDatabaseUsuario = new DataBaseUsuario(this);

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        ArrayList<String> listData = new ArrayList<>();

        View.OnClickListener verificaSenha = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senha = "";

                Cursor data = mDatabaseUsuario.getData();

                while(data.moveToNext()){
                    if (txtNomeUsuario.getText().toString().equals(data.getString(0))){
                        senha = data.getString(4);
                    }
                }

                if(senha.equals("")){
                    mostraMensagem("Usuário não existe!");
                }

                if(edtConfirmarSenha.getText().toString().equals(senha)){
                    openMenu();
                }else{
                    mostraMensagem("Senha incorreta!");
                }
            }
        };
        btnEntrarMenu.setOnClickListener(verificaSenha);

    }

    private void mostraMensagem(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void openMenu() {
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }
}

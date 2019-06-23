package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdicionarReceita extends AppCompatActivity {

    private Button btnAddReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_receita);


        View.OnClickListener telaMenu = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        };

        btnAddReceita.setOnClickListener(telaMenu);
    }

    private void openMenu() {
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }
}

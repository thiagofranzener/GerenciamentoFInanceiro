package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private DataBaseUsuario mDatabaseUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.btnAddUsuario);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), CadastroUsuario.class);
                startActivityForResult(mIntent, 0);
            }
        });

        mListView = findViewById(R.id.listview);
        populateListView();
    }

    public void populateListView() {
        Cursor data = mDatabaseUsuario.getData();
        ArrayList<String> listData = new ArrayList<>();

        String item = "";

        while(data.moveToNext()){
            item = "Usuário: ".concat(data.getString(0)
                    .concat("\nCpf: ".concat(data.getString(2))));

            listData.add(item);
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        View.OnClickListener menu = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        };

        //btnCadastrar.setOnClickListener(tela2);
    }

    private void openMenu() {
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }
}

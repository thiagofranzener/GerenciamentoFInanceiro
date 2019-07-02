package br.org.catolicasc.gerenciamentofinanceiro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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

        mDatabaseUsuario = new DataBaseUsuario(this);

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

        final ArrayList<String> listData = new ArrayList<>();

        String item = "";

        Cursor data = mDatabaseUsuario.getData();
        while(data.moveToNext()){
            item = data.getString(0);
            listData.add(item);
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openConfirmaSenha(listData.get(position));
            }
        });
    }

    private void openConfirmaSenha(String usuario) {
        Intent intent = new Intent(this, ConfirmarSenha.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    private void mostraMensagem(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

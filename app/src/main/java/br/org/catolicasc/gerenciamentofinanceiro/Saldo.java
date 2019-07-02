package br.org.catolicasc.gerenciamentofinanceiro;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.Toast;

public class Saldo extends Fragment {

    private DataBaseUsuario mDatabaseUsuario;
    private DataBaseReceita mDatabaseReceita;
    private DataBaseDespesa mDatabaseDespesa;

    TextView plnRenda;
    TextView plnGastos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saldo,container,false);

        mDatabaseUsuario = new DataBaseUsuario(this.getContext());
        mDatabaseReceita = new DataBaseReceita(this.getContext());
        mDatabaseDespesa = new DataBaseDespesa(this.getContext());

        plnRenda = view.findViewById(R.id.plnRenda);
        plnGastos = view.findViewById(R.id.plnGastos);

        String usuario = mDatabaseUsuario.getLoggedUser();
        mostraMensagem("Usuário: " + usuario);
        Cursor receitas = mDatabaseReceita.getDataUser(usuario);
        Float somaReceita = Float.parseFloat("0");
        while(receitas.moveToNext()){
            somaReceita += Float.parseFloat(receitas.getString(3));
        }

        Cursor despesas = mDatabaseDespesa.getDataUser(usuario,false);
        Float somaDespesa = Float.parseFloat("0");
        while(despesas.moveToNext()){
            somaDespesa += Float.parseFloat(despesas.getString(4));
        }

        plnRenda.setText(somaReceita.toString());
        plnGastos.setText(somaDespesa.toString());

        return view;
    }

    private void mostraMensagem(String msg){
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

package br.org.catolicasc.gerenciamentofinanceiro;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Saldo extends Fragment {

    TextView plnRenda;
    TextView plnGastos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saldo,container,false);
        plnRenda = view.findViewById(R.id.plnRenda);
        plnGastos = view.findViewById(R.id.plnGastos);

        plnRenda.setText("R$ 1.526,39");
        plnGastos.setText("R$ 895,72");

        return view;
    }
}

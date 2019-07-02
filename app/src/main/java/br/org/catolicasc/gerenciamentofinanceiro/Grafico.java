package br.org.catolicasc.gerenciamentofinanceiro;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Grafico extends Fragment {

    private DataBaseUsuario mDatabaseUsuario;
    private DataBaseReceita mDatabaseReceita;
    private DataBaseDespesa mDatabaseDespesa;


    float itemGrafico[] = {}; // Definir a % de cada item do gráfico {95, 20, 40, 45}
    String descricao[] = {}; // Define o nome de cada item do gráfico {"item um", "item dois", "item tres", "item quatro"}
    PieChart grafico;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.grafico,container,false);

        mDatabaseUsuario = new DataBaseUsuario(this.getContext());
        mDatabaseReceita = new DataBaseReceita(this.getContext());
        mDatabaseDespesa = new DataBaseDespesa(this.getContext());

        String usuario = mDatabaseUsuario.getLoggedUser();

        grafico = (PieChart) view2.findViewById(R.id.grafico);
        List<PieEntry> entradaGrafico = new ArrayList<>();

        //Receitas
        Cursor receitas = mDatabaseReceita.getDataUser(usuario);
        Float somaReceita = Float.parseFloat("0");
        while(receitas.moveToNext()){
            somaReceita += Float.parseFloat(receitas.getString(3));
        }
        entradaGrafico.add(new PieEntry(somaReceita, "Receitas"));

        //Despesas
        Cursor despesas = mDatabaseDespesa.getDataUserByCategory(usuario);
        while(despesas.moveToNext()){
            entradaGrafico.add(new PieEntry(Float.parseFloat(despesas.getString(1)), despesas.getString(0)));
        }

        PieDataSet dataSet = new PieDataSet(entradaGrafico, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(dataSet);
        grafico.setData(pieData);
        grafico.invalidate();

        return view2;
    }
}

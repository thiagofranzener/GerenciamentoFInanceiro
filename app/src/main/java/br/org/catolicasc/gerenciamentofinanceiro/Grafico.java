package br.org.catolicasc.gerenciamentofinanceiro;

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

    float itemGrafico[] = {95f, 20f, 40f, 45f}; // Definir a % de cada item do gráfico
    String descricao[] = {"item um", "item dois", "item tres", "item quatro"}; // Define o nome de cada item do gráfico
    PieChart grafico;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.grafico,container,false);

        grafico = (PieChart) view2.findViewById(R.id.grafico);

        List<PieEntry> entradaGrafico = new ArrayList<>();
        for(int i = 0;i < itemGrafico.length; i++) {
            entradaGrafico.add(new PieEntry(itemGrafico[i], descricao[i]));
        }

        PieDataSet dataSet = new PieDataSet(entradaGrafico, "Legendas");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(dataSet);
        grafico.setData(pieData);
        grafico.invalidate();

        return view2;
    }
}

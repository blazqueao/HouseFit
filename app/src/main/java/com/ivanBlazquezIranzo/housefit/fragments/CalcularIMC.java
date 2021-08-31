package com.ivanBlazquezIranzo.housefit.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.ivanBlazquezIranzo.housefit.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalcularIMC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalcularIMC extends Fragment {

    private NumberPicker numberPicker1cm;
    private NumberPicker numberPicker2cm;
    private NumberPicker numberPicker3cm;
    private NumberPicker numberPicker1kg;
    private NumberPicker numberPicker2kg;
    private NumberPicker numberPicker3kg;
    private TextView textViewResultadoImc;
    private TextView textViewImc;

    public CalcularIMC() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CalcularIMC.
     */
    // TODO: Rename and change types and number of parameters
    public static CalcularIMC newInstance() {
        return new CalcularIMC();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calcular_imc, container, false);

        numberPicker1cm = view.findViewById(R.id.numberPicker1cm);
        numberPicker1cm.setMaxValue(2);
        numberPicker1cm.setMinValue(0);
        numberPicker1cm.setValue(1);

        numberPicker2cm = view.findViewById(R.id.numberPicker2cm);
        numberPicker2cm.setMaxValue(9);
        numberPicker2cm.setMinValue(0);
        numberPicker2cm.setValue(7);

        numberPicker3cm = view.findViewById(R.id.numberPicker3cm);
        numberPicker3cm.setMaxValue(9);
        numberPicker3cm.setMinValue(0);
        numberPicker3cm.setValue(0);

        numberPicker1kg = view.findViewById(R.id.numberPicker1kg);
        numberPicker1kg.setMaxValue(5);
        numberPicker1kg.setMinValue(0);
        numberPicker1kg.setValue(0);

        numberPicker2kg = view.findViewById(R.id.numberPicker2kg);
        numberPicker2kg.setMaxValue(9);
        numberPicker2kg.setMinValue(0);
        numberPicker2kg.setValue(6);

        numberPicker3kg = view.findViewById(R.id.numberPicker3kg);
        numberPicker3kg.setMaxValue(9);
        numberPicker3kg.setMinValue(0);
        numberPicker3kg.setValue(4);


        Button btnCalcular = view.findViewById(R.id.btnCalcularIMC);
        //noinspection Convert2Lambda
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarTextView(generarImc());
            }
        });

        textViewResultadoImc = view.findViewById(R.id.textViewResultadoImc);
        textViewImc = view.findViewById(R.id.textViewImc);

        return view;
    }

    public float generarImc() {
        int peso = numberPicker1kg.getValue() * 100 + numberPicker2kg.getValue() * 10 + numberPicker3kg.getValue();
        int alturaCm = numberPicker1cm.getValue() * 100 + numberPicker2cm.getValue() * 10 + numberPicker3cm.getValue();
        if (peso==0 || alturaCm==0) {
            return 0;
        } else {
            float alturaM = ((float)alturaCm)/100;
            //noinspection UnnecessaryLocalVariable
            float imc = peso/(alturaM*alturaM);
            return imc; //64 kg 178 cm = 20,2
        }
    }

    @SuppressLint("SetTextI18n")
    public void generarTextView(float imc) {
        imc = (float) (Math.round(imc*10)/10.0);
        if (imc==0) {
            textViewResultadoImc.setText("Altura o peso erroneos");
            textViewResultadoImc.setTextColor(Color.RED);
            textViewImc.setTextColor(Color.RED);
        }
        else if (imc < 18.5) {
            textViewResultadoImc.setText("Muy delgado");
            textViewResultadoImc.setTextColor(Color.rgb(4, 183, 234));
            textViewImc.setTextColor(Color.rgb(4, 183, 234));
        } else if (imc>=18.5 && imc<=24.9) {
            textViewResultadoImc.setText("Peso perfecto");
            textViewResultadoImc.setTextColor(Color.rgb(82, 207, 14));
            textViewImc.setTextColor(Color.rgb(82, 207, 14));
        } else if (imc>=25 && imc<=29.9) {
            textViewResultadoImc.setText("Sobrepeso");
            textViewResultadoImc.setTextColor(Color.rgb(221, 200, 0));
            textViewImc.setTextColor(Color.rgb(221, 200, 0));
        } else if (imc>=30 && imc<=39.9) {
            textViewResultadoImc.setText("Obeso");
            textViewResultadoImc.setTextColor(Color.rgb(242, 95, 6));
            textViewImc.setTextColor(Color.rgb(242, 95, 6));
        } else {
            textViewResultadoImc.setText("Obesidad morbida");
            textViewResultadoImc.setTextColor(Color.RED);
            textViewImc.setTextColor(Color.RED);
        }
        textViewImc.setText(imc+"");
    }
}
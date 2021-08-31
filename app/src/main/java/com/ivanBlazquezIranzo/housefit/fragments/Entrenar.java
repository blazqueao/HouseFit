package com.ivanBlazquezIranzo.housefit.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.ivanBlazquezIranzo.housefit.Ejercicio;
import com.ivanBlazquezIranzo.housefit.GenerarBDEjercicios;
import com.ivanBlazquezIranzo.housefit.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Entrenar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Entrenar extends Fragment {

    private AutoCompleteTextView desplegableMusculo;
    private AutoCompleteTextView desplegableDificultad;
    private AutoCompleteTextView desplegableDuracion;

    public Entrenar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Entrenar.
     */
    public static Entrenar newInstance() {
        return new Entrenar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entrenar, container, false);

        //Para que no de error enlazo manualmente el boton con el metodo
        View botonEmpezar = view.findViewById(R.id.btnGenerar);
        //noinspection Convert2Lambda
        botonEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonEmpezarClick();
            }
        });

        desplegableMusculo = view.findViewById(R.id.desplegableMusculoListado);
        desplegableDificultad = view.findViewById(R.id.desplegableDificultad);
        desplegableDuracion = view.findViewById(R.id.desplegableDuracion);

        iniciarDesplegables();

        return view;
    }

    public void botonEmpezarClick () {
        String musculoElegido, dificultadElegida, duracionElegida;
        int dificultad;
        musculoElegido = desplegableMusculo.getText().toString();
        dificultadElegida = desplegableDificultad.getText().toString();
        duracionElegida = desplegableDuracion.getText().toString();
        if (dificultadElegida.equalsIgnoreCase("Facil")) {
            dificultad = 1;
        } else if (dificultadElegida.equalsIgnoreCase("Intermedia")) {
            dificultad = 2;
        } else {
            dificultad = 3;
        }

        ArrayList<Ejercicio> listaEjercicios= GenerarBDEjercicios.filtrarEjercicios(musculoElegido, dificultad, getContext());

        Fragment fragment = Rutina.newInstance(listaEjercicios, duracionElegida);
        //noinspection ConstantConditions
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.menu_entrar, R.anim.menu_salir)
                .replace(R.id.home_content, fragment, "rutina_fragment")
                .addToBackStack(null)
                .commit();


    }

    public void iniciarDesplegables() {
        //Desplegable para elegir el musculo
        ArrayList<String> array_musculos = new ArrayList<>();
        array_musculos.add("Abdominales");
        array_musculos.add("Brazos y pecho");
        array_musculos.add("Gluteos y piernas");
        array_musculos.add("Espalda y hombros");

        ArrayAdapter<String> arrayAdapter_musculo = new ArrayAdapter<>(getContext(), R.layout.desplegable, array_musculos);
        desplegableMusculo.setAdapter(arrayAdapter_musculo);
        desplegableMusculo.setText(array_musculos.get(0), false);

        //Desplegable para elegir la dificultad
        ArrayList<String> array_dificultad = new ArrayList<>();
        array_dificultad.add("Facil");
        array_dificultad.add("Intermedia");
        array_dificultad.add("Dificil");

        ArrayAdapter<String> arrayAdapter_dificultad = new ArrayAdapter<>(getContext(), R.layout.desplegable, array_dificultad);
        desplegableDificultad.setAdapter(arrayAdapter_dificultad);
        desplegableDificultad.setText(array_dificultad.get(0), false);

        //Desplegable para elegir la duracion
        ArrayList<String> array_duracion = new ArrayList<>();
        array_duracion.add("Corto (7 minutos)");
        array_duracion.add("Medio (15 minutos)");
        array_duracion.add("Largo (30 minutos)");

        ArrayAdapter<String> arrayAdapter_duracion = new ArrayAdapter<>(getContext(), R.layout.desplegable, array_duracion);
        desplegableDuracion.setAdapter(arrayAdapter_duracion);
        desplegableDuracion.setText(array_duracion.get(0), false);
    }

}
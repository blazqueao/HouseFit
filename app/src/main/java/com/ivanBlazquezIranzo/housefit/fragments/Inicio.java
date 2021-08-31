package com.ivanBlazquezIranzo.housefit.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ivanBlazquezIranzo.housefit.MainActivity;
import com.ivanBlazquezIranzo.housefit.R;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inicio extends Fragment {

    public Inicio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Inicio.
     */
    public static Inicio newInstance() {
        return new Inicio();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings({"Convert2Lambda"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        //Para que no de error enlazo manualmente el boton con el metodo
        View botonInicioEntrenar = view.findViewById(R.id.btnInicioEntrenar);
        botonInicioEntrenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonInicioEntrenarClick();
            }
        });

        View botonInicioListaEjercicios = view.findViewById(R.id.btnInicioListaEjercicios);
        botonInicioListaEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonInicioListaEjerciciosClick();
            }
        });

        View botonInicioCalcularIMC = view.findViewById(R.id.btnInicioCalcularIMC);
        botonInicioCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonInicioCalcularIMCClick();
            }
        });

        View botonInicioConsejos = view.findViewById(R.id.btnInicioConsejos);
        botonInicioConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonInicioConsejosClick();
            }
        });

        return view;
    }

    public void botonInicioEntrenarClick() {
        String titulo = "Entrenar";
        Fragment fragment = Entrenar.newInstance();
        iniciarFragmento(fragment, titulo, 1);
    }

    public void botonInicioListaEjerciciosClick() {
        String titulo = "Listado de ejercicios";
        Fragment fragment = ListaEjercicios.newInstance();
        iniciarFragmento(fragment, titulo, 2);
    }

    public void botonInicioCalcularIMCClick() {
        String titulo = "Calcula tu IMC";
        Fragment fragment = CalcularIMC.newInstance();
        iniciarFragmento(fragment, titulo, 3);
    }

    public void botonInicioConsejosClick() {
        String titulo = "Consejos generales";
        Fragment fragment = ConsejosMain.newInstance();
        iniciarFragmento(fragment, titulo, 4);
    }

    @SuppressWarnings("ConstantConditions")
    public void iniciarFragmento(Fragment fragment, String titulo, int posicionLateral) {
        getActivity().setTitle(titulo);
        NavigationView navigationView = MainActivity.getNavigationView();
        MenuItem menuItem = navigationView.getMenu().getItem(posicionLateral);
        menuItem.setChecked(true);
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.menu_entrar, R.anim.menu_salir)
                .replace(R.id.home_content, fragment, "entrenar_fragment")
                .addToBackStack(null)
                .commit();
    }
}
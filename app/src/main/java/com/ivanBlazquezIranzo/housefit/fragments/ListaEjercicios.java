package com.ivanBlazquezIranzo.housefit.fragments;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.ivanBlazquezIranzo.housefit.Ejercicio;
import com.ivanBlazquezIranzo.housefit.GenerarBDEjercicios;
import com.ivanBlazquezIranzo.housefit.ListaElementosRecycler;
import com.ivanBlazquezIranzo.housefit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaEjercicios#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("Convert2Lambda")
public class ListaEjercicios extends Fragment {

    private List<Ejercicio> elementos;
    private AutoCompleteTextView desplegableMusculoListado;
    private RecyclerView recyclerView;

    public ListaEjercicios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListaEjercicios.
     */
    public static ListaEjercicios newInstance() {
        return new ListaEjercicios();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_ejercicios, container, false);

        desplegableMusculoListado = view.findViewById(R.id.desplegableMusculoListado);
        ArrayList<String> array_musculosListado = new ArrayList<>();
        array_musculosListado.add("Abdominales");
        array_musculosListado.add("Brazos y pecho");
        array_musculosListado.add("Gluteos y piernas");
        array_musculosListado.add("Espalda y hombros");

        ArrayAdapter<String> arrayAdapter_musculoListado = new ArrayAdapter<>(getContext(), R.layout.desplegable, array_musculosListado);
        desplegableMusculoListado.setAdapter(arrayAdapter_musculoListado);
        desplegableMusculoListado.setText(array_musculosListado.get(0), false);

        recyclerView = view.findViewById(R.id.recyclerView);

        generarLista();

        desplegableMusculoListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                generarLista();
            }
        });

        return view;
    }

    public void generarLista() {
        ArrayList<Ejercicio> listaEjercicios= GenerarBDEjercicios.filtrarEjerciciosMusculo(desplegableMusculoListado.getText().toString(), getContext());
        elementos = new ArrayList<>();
        for (Ejercicio ejer: listaEjercicios) {
            //noinspection UseBulkOperation
            elementos.add(ejer);
        }

        ListaElementosRecycler listaElementosRecycler = new ListaElementosRecycler(elementos, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listaElementosRecycler);

        listaElementosRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewClick(v, recyclerView);
            }
        });
    }

    public void cardViewClick(View view, RecyclerView recyclerView) {
        int selection = recyclerView.getChildAdapterPosition(view);
        Toast.makeText(getContext(), elementos.get(selection).getNombre()+"",Toast.LENGTH_SHORT).show();

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogo_video);

        VideoView videoview = dialog.findViewById(R.id.dialogoVideo);
        // Creamos el Loop del video
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        Uri uri = Uri.parse(elementos.get(selection).getnombreVideo());
        videoview.setVideoURI(uri);
        videoview.start();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
}
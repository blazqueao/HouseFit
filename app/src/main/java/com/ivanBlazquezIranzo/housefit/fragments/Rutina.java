package com.ivanBlazquezIranzo.housefit.fragments;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.VideoView;

import com.ivanBlazquezIranzo.housefit.Ejercicio;
import com.ivanBlazquezIranzo.housefit.MainActivity;
import com.ivanBlazquezIranzo.housefit.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Rutina#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings({"Convert2Lambda"})
public class Rutina extends Fragment {

    private static final String ARG_PARAM1 = "listaEjercicios";
    private static final String ARG_PARAM2 = "duracion";

    private ArrayList<Ejercicio> listaEjercicios;
    private String duracion;

    private int duracionEjercicio;
    private int duracionDescanso;
    private long tiempoParado;
    private int repeticiones;
    private int contadorRepeticiones, contadorEjercicios, tocaDescanso;

    private Button btnPausa;
    private Button btnReanudar;
    private Button btnEmpezar;
    private Button btnInicio;
    private VideoView video;
    private TextView txtNombreEjercicio;
    private Chronometer crono;
    private ContadorRegresivo contador;

    public Rutina() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param listaEjercicios Lista de los ejercicios que van a ser utilizados.
     * @param duracion La duracion de la rutina.
     * @return A new instance of fragment Rutina.
     */
    public static Rutina newInstance(ArrayList<Ejercicio> listaEjercicios, String duracion) {
        Rutina fragment = new Rutina();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, listaEjercicios);
        args.putString(ARG_PARAM2, duracion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            //noinspection unchecked
            this.listaEjercicios = (ArrayList<Ejercicio>) bundle.getSerializable(ARG_PARAM1);
            this.duracion = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rutina, container, false);
        video= view.findViewById(R.id.videoView);
        crono = view.findViewById(R.id.chronometer);
        txtNombreEjercicio = view.findViewById(R.id.txtNombreEjercicio);
        //Obtenemos el boton de la interfaz
        btnEmpezar = view.findViewById(R.id.btnEmpezar);
        View botonEmpezar = btnEmpezar;
        //Para que no de error enlazo manualmente el boton con el metodo
        botonEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonClickEmpezar();
            }
        });
        btnPausa = view.findViewById(R.id.btnPausa);
        View botonPausar = btnPausa;
        botonPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonClick();
            }
        });
        btnReanudar = view.findViewById(R.id.btnReanudar);
        View botonReanudar = btnReanudar;
        botonReanudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonClick();
            }
        });
        btnInicio = view.findViewById(R.id.btnInicio);
        View botonInicio = btnInicio;
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonInicio();
            }
        });
        btnInicio.setVisibility(View.INVISIBLE);
        btnInicio.setEnabled(false);

        txtNombreEjercicio.setText("Presiona el boton para empezar la rutina");
        btnReanudar.setVisibility(View.INVISIBLE);
        btnReanudar.setEnabled(false);
        btnPausa.setVisibility(View.INVISIBLE);
        btnPausa.setEnabled(false);
        btnEmpezar.setVisibility(View.VISIBLE);
        btnEmpezar.setEnabled(true);

        return view;
    }

    public void generarRutina() {
        //int duracionRutina;
        switch (duracion) {
            case "Corto (7 minutos)":
                //noinspection IfStatementWithIdenticalBranches
                if (listaEjercicios.size() == 3) {
                    //Si se generan 3 ejercicios la duracion de estos sera de 20 segundos cada uno
                    //En 7 minutos haremos 7 repeticiones de los ejercicios
                    duracionEjercicio=20;
                    duracionDescanso=0;
                    //duracionRutina=420; //7 minutos
                    repeticiones=7;

                } else {
                    //Si se generan 4 ejercicios la duracion de estos sera de 15 segundos cada uno
                    //En 7 minutos haremos 7 repeticiones de los ejercicio
                    duracionEjercicio=15;
                    duracionDescanso=0;
                    //duracionRutina=420; //7 minutos
                    repeticiones=7;

                }
                //Asi la duracion total de la rutina corta sera de 7 minutos, sin descansos
                break;
            case "Medio (15 minutos)":
                if (listaEjercicios.size() == 3) {
                    //Si se generan 3 ejercicios la duracion de estos sera de 20 segundos cada uno
                    //Con 10 segundos de descanso despues de cada ejercicio
                    //En 15 minutos haremos 10 repeticiones de los ejercicios
                    duracionEjercicio = 20;
                    duracionDescanso = 10;
                    //duracionRutina = 900; //15 minutos
                    repeticiones=10;

                } else {
                    //Si se generan 4 ejercicios la duracion de estos sera de 15 segundos cada uno
                    //Con 10 segundos de descanso despues de cada ejercicio
                    //En 15 minutos haremos 9 repeticiones de los ejercicios
                    duracionEjercicio=15;
                    duracionDescanso=10;
                    //duracionRutina=900; //15 minutos
                    repeticiones=9;

                }
                //Asi la duracion total de la rutina media sera de 15 minutos
                break;
            case "Largo (30 minutos)":
                if (listaEjercicios.size() == 3) {
                    //Si se generan 3 ejercicios la duracion de estos sera de 45 segundos cada uno
                    //Con 30 segundos de descanso despues de cada ejercicio
                    //En 30 minutos haremos 8 repeticiones de los ejercicios
                    duracionEjercicio = 45;
                    duracionDescanso = 30;
                    //duracionRutina = 1800; //30 minutos
                    repeticiones=8;

                } else {
                    //Si se generan 4 ejercicios la duracion de estos sera de 45 segundos cada uno
                    //Con 30 segundos de descanso despues de cada ejercicio
                    //En 30 minutos haremos 6 repeticiones de los ejercicios
                    duracionEjercicio = 45;
                    duracionDescanso = 30;
                    //duracionRutina = 1800; //30 minutos
                    repeticiones=6;

                }
                //Asi la duracion total de la rutina larga sera de 30 minutos
                break;
        }

        contadorRepeticiones=0;
        contadorEjercicios=0;
        tocaDescanso=0;
        siguienteEjercicio();
    }

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("SetTextI18n")
    public void siguienteEjercicio() {
        if (contadorRepeticiones<repeticiones) {
            if (contadorEjercicios<listaEjercicios.size()) {
                if (tocaDescanso == 1 && duracionDescanso !=0) {
                    //Toca descanso
                    txtNombreEjercicio.setText("Descanso");
                    iniciarVideo("android.resource://" + getContext().getPackageName() + "/" + R.raw.z_descanso, duracionDescanso);
                    contador = new ContadorRegresivo(duracionDescanso*1000, 1000);
                    contador.start();
                    tocaDescanso = tocaDescanso - 1;
                } else {
                    Ejercicio ejercicio = listaEjercicios.get(contadorEjercicios);
                    txtNombreEjercicio.setText(ejercicio.getNombre());
                    iniciarVideo(ejercicio.getnombreVideo(), duracionEjercicio);
                    contador = new ContadorRegresivo(duracionEjercicio*1000, 1000);
                    contador.start();

                    contadorEjercicios++;
                    tocaDescanso = tocaDescanso + 1;
                }

            }
            else if (contadorEjercicios == listaEjercicios.size()) {
                contadorEjercicios = 0;
                contadorRepeticiones++;
                siguienteEjercicio();
            }
        } else {
            txtNombreEjercicio.setText("Entrenamiento finalizado");
            iniciarVideo("android.resource://" + getContext().getPackageName() + "/" + R.raw.z_terminar, duracionDescanso);
            btnInicio.setVisibility(View.VISIBLE);
            btnInicio.setEnabled(true);
            crono.stop();
            btnPausa.setVisibility(View.INVISIBLE);
            btnPausa.setEnabled(false);
            btnReanudar.setVisibility(View.INVISIBLE);
            btnReanudar.setEnabled(false);
        }
    }

    public void iniciarVideo(String nombreVideo, int duracionVideo) {
        //Inicializamos la clase VideoView asociandole el fichero de Video
        video.setVideoURI(Uri.parse(nombreVideo));
        video.start();
        crono.setBase(SystemClock.elapsedRealtime() + duracionVideo*1000);
        crono.start();
        // Creamos el Loop del video
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    public void botonClickEmpezar() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                btnEmpezar.setVisibility(View.INVISIBLE);
                btnEmpezar.setEnabled(false);
                btnReanudar.setVisibility(View.INVISIBLE);
                btnReanudar.setEnabled(false);
                btnPausa.setVisibility(View.VISIBLE);
                btnPausa.setEnabled(true);
                generarRutina();
            }
        }, 200);
    }

    public void botonClick () {
        if (video.isPlaying()) {
            video.pause();
            crono.stop();
            tiempoParado = crono.getBase() - SystemClock.elapsedRealtime();
            //Toast.makeText(getContext(), tiempoParado*1000+"",Toast.LENGTH_SHORT).show();
            contador.cancel();
            //utilizo esto para que la animacion del boton sea visible
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // acciones que se ejecutan tras los milisegundos
                    btnReanudar.setVisibility(View.VISIBLE);
                    btnReanudar.setEnabled(true);
                    btnPausa.setVisibility(View.INVISIBLE);
                    btnPausa.setEnabled(false);
                }
            }, 200);
        } else {
            video.start();
            crono.setBase(SystemClock.elapsedRealtime() + tiempoParado);
            crono.start();
            contador = new ContadorRegresivo(tiempoParado, 1000);
            contador.start();
            //utilizo esto para que la animacion del boton sea visible
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // acciones que se ejecutan tras los milisegundos
                    btnReanudar.setVisibility(View.INVISIBLE);
                    btnReanudar.setEnabled(false);
                    btnPausa.setVisibility(View.VISIBLE);
                    btnPausa.setEnabled(true);
                }
            }, 200);
        }

    }

    @SuppressWarnings("ConstantConditions")
    public void botonInicio() {
        NavigationView navigationView = MainActivity.getNavigationView();
        MenuItem menuItem = navigationView.getMenu().getItem(0);
        menuItem.setChecked(true);
        getActivity().setTitle("Inicio");
        Fragment fragment = Inicio.newInstance();
        FragmentManager fm = getFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fm.beginTransaction()
        .setCustomAnimations(R.anim.menu_entrar, R.anim.menu_salir)
            .replace(R.id.home_content, fragment, "entrenar_fragment")
            .commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        //Pararlo_todo para que no salte error
        if (!btnEmpezar.isEnabled()) {
            video.pause();
            crono.stop();
            tiempoParado = crono.getBase() - SystemClock.elapsedRealtime();
            contador.cancel();
            btnReanudar.setVisibility(View.VISIBLE);
            btnReanudar.setEnabled(true);
            btnPausa.setVisibility(View.INVISIBLE);
            btnPausa.setEnabled(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (btnEmpezar.isEnabled()) {
            //noinspection ConstantConditions
            video.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.icono_video));
            video.start();
        } else {
            //video.start();
            video.pause();
        }
    }

    public class ContadorRegresivo extends CountDownTimer {

        public ContadorRegresivo(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            siguienteEjercicio();
        }
    }

}
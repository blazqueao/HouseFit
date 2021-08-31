package com.ivanBlazquezIranzo.housefit;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GenerarBDEjercicios {

    public static ArrayList<Ejercicio> filtrarEjercicios(String musculo, int dificultad, Context c) {
        ArrayList<Ejercicio> ejercicios;
        ArrayList<Ejercicio> ejerciciosFiltrados = new ArrayList<>();
        ejercicios = leerBDEjercicios(c);

        for (Ejercicio ejercicio : ejercicios) {
            if (ejercicio.getMusculo().equalsIgnoreCase(musculo) && ejercicio.getDificultad() == dificultad) {
                ejerciciosFiltrados.add(ejercicio);
            }
        }

        return ejerciciosFiltrados;
    }

    public static ArrayList<Ejercicio> filtrarEjerciciosMusculo(String musculo, Context c) {
        ArrayList<Ejercicio> ejercicios;
        ArrayList<Ejercicio> ejerciciosFiltrados = new ArrayList<>();
        ejercicios = leerBDEjercicios(c);

        for (Ejercicio ejercicio : ejercicios) {
            if (ejercicio.getMusculo().equalsIgnoreCase(musculo)) {
                ejerciciosFiltrados.add(ejercicio);
            }
        }

        return ejerciciosFiltrados;
    }

    public static ArrayList<Ejercicio> leerBDEjercicios(Context c) {
        Ejercicio ej;
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();

        File internalStorageDir = c.getFilesDir();
        File fichero = new File(internalStorageDir, "Ejercicios.dat");

        try {
            FileInputStream fileIS = new FileInputStream(fichero);
            ObjectInputStream objectIS = new ObjectInputStream(fileIS);
            try {

                //noinspection InfiniteLoopStatement
                while (true) { // lectura del fichero
                    ej = (Ejercicio) objectIS.readObject();
                    ejercicios.add(ej);
                }

            } catch (Exception e) {
                //Se produce EOFException al finalizar la lectura
                if(!e.toString().equals("java.io.EOFException")) {
                    System.err.println("Error al leer fichero: "+e.toString());
                }
            }
            objectIS.close();
            fileIS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ejercicios;
    }

    public static void generarBDEjercicios(Context c) {
        try {
            File internalStorageDir = c.getFilesDir();
            File file = new File(internalStorageDir, "Ejercicios.dat");
            FileOutputStream fileOS = new FileOutputStream(file);
            ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);

            //Los ejercicios se nombran segun este criterio: zonaMuscular_dificultad_nombreEjercicio

            //Abdominales
            //Facil
            Ejercicio abdominales_1_abdominales = new Ejercicio("Abdominales", "Abdominales", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_1_abdominales);
            Ejercicio abdominales_1_plancha = new Ejercicio("Plancha sencilla", "Abdominales", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_1_plancha);
            Ejercicio abdominales_1_superman = new Ejercicio("Superman", "Abdominales", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_1_superman);
            //Intermedio
            Ejercicio abdominales_2_laterales = new Ejercicio("Abdominales laterales", "Abdominales", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_2_laterales);
            Ejercicio abdominales_2_planchaBrazosSubirBajar = new Ejercicio("Plancha utilizando los brazos para subir y bajar", "Abdominales", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_2_plancha_brazos_subir_bajar);
            Ejercicio abdominales_2_planchaLateral = new Ejercicio("Plancha lateral", "Abdominales", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_2_plancha_lateral);
            Ejercicio abdominales_2_planchaPiernasLaterales = new Ejercicio("Plancha con movimiento de piernas hacia los lados", "Abdominales", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_2_plancha_piernas_laterales);
            //Dificil
            Ejercicio abdominales_3_piernasArriba = new Ejercicio("Levantamiento de piernas tumbado", "Abdominales", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_3_piernas_arriba);
            Ejercicio abdominales_3_planchaLateralRotacion = new Ejercicio("Plancha lateral con rotación", "Abdominales", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_3_plancha_lateral_rotacion);
            Ejercicio abdominales_3_planchaRodillasPecho = new Ejercicio("Plancha llevando las rodillas al pecho", "Abdominales", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.abdominales_3_plancha_rodillas_pecho);

            //Brazos y Pecho
            //Facil
            Ejercicio brazosPecho_1_flexionesRodillas = new Ejercicio("Flexiones simples apoyando las rodillas", "Brazos y pecho", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_1_flexiones_rodillas);
            Ejercicio brazosPecho_1_flexionesDiamanteRodillas = new Ejercicio("Flexiones de diamante apoyando las rodillas", "Brazos y pecho", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_1_flexiones_diamante_rodillas);
            Ejercicio brazosPecho_1_planchaBrazosSubirBajarRodillas = new Ejercicio("Plancha utilizando los brazos para subir y bajar apoyando las rodillas", "Brazos y pecho", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_1_plancha_brazos_subir_bajar_rodillas);

            //Intermedio
            Ejercicio brazosPecho_2_flexiones = new Ejercicio("Flexiones simples", "Brazos y pecho", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_2_flexiones);
            Ejercicio brazosPecho_2_flexionesBrazosElevados = new Ejercicio("Flexiones con los brazos apoyados en una superficie elevada", "Brazos y pecho", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_2_flexiones_brazos_elevados);
            Ejercicio brazosPecho_2_flexionesDiamante = new Ejercicio("Flexiones de diamante", "Brazos y pecho", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_2_flexiones_diamante);
            Ejercicio brazosPecho_2_planchaBrazosSubirBajar = new Ejercicio("Plancha utilizando los brazos para subir y bajar", "Brazos y pecho", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_2_plancha_brazos_subir_bajar);

            //Dificil
            Ejercicio brazosPecho_3_flexionesPiernasElevadas = new Ejercicio("Flexiones con las piernas apoyadas en una superficie elevada", "Brazos y pecho", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_3_flexiones_piernas_elevadas);
            Ejercicio brazosPecho_3_flexionesEspalda = new Ejercicio("Flexiones de espaldas y con los brazos apoyados en una superfice elevada", "Brazos y pecho", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_3_flexiones_espalda);
            Ejercicio brazosPecho_3_flexionesPiernasGluteosElevados = new Ejercicio("Flexiones con los gluteos y piernas elevadas", "Brazos y pecho", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_3_flexiones_piernas_gluteos_elevados);
            Ejercicio brazosPecho_3_flexionesDiamante = new Ejercicio("Flexiones de diamante", "Brazos y pecho", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.brazospecho_3_flexiones_diamante);

            //Gluteos y Piernas
            //Facil
            Ejercicio gluteosPiernas_1_gluteosArriba = new Ejercicio("Gluteos arriba", "Gluteos y piernas", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_1_gluteos_arriba);
            Ejercicio gluteosPiernas_1_coces = new Ejercicio("Coces", "Gluteos y piernas", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_1_coces);
            Ejercicio gluteosPiernas_1_sentadillas = new Ejercicio("Sentadillas", "Gluteos y piernas", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_1_sentadillas);

            //Intermedio
            Ejercicio gluteosPiernas_2_desplazamientosFrontales = new Ejercicio("Desplazamientos frontales de piernas", "Gluteos y piernas", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_2_desplazamientos_frontales);
            Ejercicio gluteosPiernas_2_desplazamientosLaterales = new Ejercicio("Desplazamientos laterales de piernas", "Gluteos y piernas", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_2_desplazamientos_laterales);
            Ejercicio gluteosPiernas_2_planchaRodillasPecho = new Ejercicio("Plancha llevando las rodillas al pecho", "Gluteos y piernas", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_2_plancha_rodillas_pecho);
            Ejercicio gluteosPiernas_2_sentadillasRapidas = new Ejercicio("Sentadillas rapidas", "Gluteos y piernas", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_2_sentadillas_rapidas);

            //Dificil
            Ejercicio gluteosPiernas_3_gluteosArribaPierna = new Ejercicio("Gluteos arriba con una pierna levantada", "Gluteos y piernas", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_3_gluteos_arriba_pierna);
            Ejercicio gluteosPiernas_3_planchaRodillasPecho = new Ejercicio("Plancha llevando las rodillas al pecho", "Gluteos y piernas", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_3_plancha_rodillas_pecho);
            Ejercicio gluteosPiernas_3_sentadillasSalto = new Ejercicio("Sentadillas con salto", "Gluteos y piernas", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.gluteospiernas_3_sentadillas_salto);

            //Espalda y Hombros
            //Facil
            Ejercicio espaldaHombros_1_estiramientoFuerzaBrazosDelante = new Ejercicio("Estiramiento con presion del brazo hacia adelante", "Espalda y hombros", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_1_estiramiento_fuerza_brazos_delante);
            Ejercicio espaldaHombros_1_estiramientoFuerzaBrazosDetras = new Ejercicio("Estiramiento con presion del brazo hacia detrás", "Espalda y hombros", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_1_estiramiento_fuerza_brazos_detras);
            Ejercicio espaldaHombros_1_contraccionFuerzaEspalda = new Ejercicio("Contraccion con fuerza la espalda", "Espalda y hombros", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_1_contraccion_fuerza_espalda);
            Ejercicio espaldaHombros_1_superman = new Ejercicio("Superman", "Espalda y hombros", 1, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_1_superman);

            //Intermedio
            Ejercicio espaldaHombros_2_flexionesBarra = new Ejercicio("Flexiones de brazos por debajo de una superficie elevada", "Espalda y hombros", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_2_flexiones_barra);
            Ejercicio espaldaHombros_2_flexionesEspalda = new Ejercicio("Flexiones de espaldas apoyando las manos en una superficie elevada", "Espalda y hombros", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_2_flexiones_espalda);
            Ejercicio espaldaHombros_2_flexionesPiernasElevadas = new Ejercicio("Flexiones con las piernas apoyadas en una superficie elevada", "Espalda y hombros", 2, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_2_flexiones_piernas_elevadas);

            //Dificil
            Ejercicio espaldaHombros_3_contraccionEspaldaPiernasGluteosElevados = new Ejercicio("Contraccion de la espalda con las piernas y los gluteos elevados", "Espalda y hombros", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_3_contraccion_espalda_piernas_gluteos_elevados);
            Ejercicio espaldaHombros_3_flexionesPiernasElevadas = new Ejercicio("Flexiones con las piernas apoyadas en una superficie elevada", "Espalda y hombros", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_3_flexiones_piernas_elevadas);
            Ejercicio espaldaHombros_3_flexionesPiernasGluteosElevados = new Ejercicio("Flexiones con los gluteos y piernas elevadas", "Espalda y hombros", 3, "android.resource://" + c.getPackageName() + "/" + R.raw.espaldahombros_3_flexiones_piernas_gluteos_elevados);

            //Escribo los Ejercicios en el fichero
            objectOS.writeObject(abdominales_1_abdominales);
            objectOS.writeObject(abdominales_1_plancha);
            objectOS.writeObject(abdominales_1_superman);
            objectOS.writeObject(abdominales_2_laterales);
            objectOS.writeObject(abdominales_2_planchaBrazosSubirBajar);
            objectOS.writeObject(abdominales_2_planchaLateral);
            objectOS.writeObject(abdominales_2_planchaPiernasLaterales);
            objectOS.writeObject(abdominales_3_piernasArriba);
            objectOS.writeObject(abdominales_3_planchaLateralRotacion);
            objectOS.writeObject(abdominales_3_planchaRodillasPecho);

            objectOS.writeObject(brazosPecho_1_flexionesRodillas);
            objectOS.writeObject(brazosPecho_1_flexionesDiamanteRodillas);
            objectOS.writeObject(brazosPecho_1_planchaBrazosSubirBajarRodillas);
            objectOS.writeObject(brazosPecho_2_flexiones);
            objectOS.writeObject(brazosPecho_2_flexionesBrazosElevados);
            objectOS.writeObject(brazosPecho_2_flexionesDiamante);
            objectOS.writeObject(brazosPecho_2_planchaBrazosSubirBajar);
            objectOS.writeObject(brazosPecho_3_flexionesPiernasElevadas);
            objectOS.writeObject(brazosPecho_3_flexionesEspalda);
            objectOS.writeObject(brazosPecho_3_flexionesPiernasGluteosElevados);
            objectOS.writeObject(brazosPecho_3_flexionesDiamante);

            objectOS.writeObject(gluteosPiernas_1_gluteosArriba);
            objectOS.writeObject(gluteosPiernas_1_coces);
            objectOS.writeObject(gluteosPiernas_1_sentadillas);
            objectOS.writeObject(gluteosPiernas_2_desplazamientosFrontales);
            objectOS.writeObject(gluteosPiernas_2_desplazamientosLaterales);
            objectOS.writeObject(gluteosPiernas_2_planchaRodillasPecho);
            objectOS.writeObject(gluteosPiernas_2_sentadillasRapidas);
            objectOS.writeObject(gluteosPiernas_3_gluteosArribaPierna);
            objectOS.writeObject(gluteosPiernas_3_planchaRodillasPecho);
            objectOS.writeObject(gluteosPiernas_3_sentadillasSalto);

            objectOS.writeObject(espaldaHombros_1_estiramientoFuerzaBrazosDelante);
            objectOS.writeObject(espaldaHombros_1_estiramientoFuerzaBrazosDetras);
            objectOS.writeObject(espaldaHombros_1_contraccionFuerzaEspalda);
            objectOS.writeObject(espaldaHombros_1_superman);
            objectOS.writeObject(espaldaHombros_2_flexionesBarra);
            objectOS.writeObject(espaldaHombros_2_flexionesEspalda);
            objectOS.writeObject(espaldaHombros_2_flexionesPiernasElevadas);
            objectOS.writeObject(espaldaHombros_3_contraccionEspaldaPiernasGluteosElevados);
            objectOS.writeObject(espaldaHombros_3_flexionesPiernasElevadas);
            objectOS.writeObject(espaldaHombros_3_flexionesPiernasGluteosElevados);

            objectOS.close();
            fileOS.close();
        } catch (FileNotFoundException error) {
            Toast.makeText(c.getApplicationContext(), "Fichero no encontrado", Toast.LENGTH_SHORT).show();
            error.printStackTrace();
        } catch (IOException error2) {
            Toast.makeText(c.getApplicationContext(), "Error en la generacion de la base de datos", Toast.LENGTH_SHORT).show();
            error2.printStackTrace();
        }
    }
    
}

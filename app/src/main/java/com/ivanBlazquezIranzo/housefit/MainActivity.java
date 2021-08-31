package com.ivanBlazquezIranzo.housefit;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.ivanBlazquezIranzo.housefit.fragments.CalcularIMC;
import com.ivanBlazquezIranzo.housefit.fragments.ConsejosMain;
import com.ivanBlazquezIranzo.housefit.fragments.Entrenar;
import com.ivanBlazquezIranzo.housefit.fragments.Inicio;
import com.ivanBlazquezIranzo.housefit.fragments.ListaEjercicios;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private DrawerLayout drawerLayout;
    private static NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            System.out.println("ERROR");
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem menuItem = navigationView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);

        drawerLayout.addDrawerListener(this);

        //Si no existe se crea el fichero con la bd de ejercicios
        File file = new File(getApplicationContext().getFilesDir().getPath() + "/Ejercicios.dat");
        if (!file.exists()) {
            GenerarBDEjercicios.generarBDEjercicios(getApplicationContext());
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if ((getTitle()+"").equalsIgnoreCase("Lista de ejercicios")) {
            super.onBackPressed();
        }
        else {
            if (fm.getBackStackEntryCount() > 1) {
                //borra la cola y echa hacia atras
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            } else {
                super.onBackPressed();
            }
            MenuItem menuItem = navigationView.getMenu().getItem(0);
            menuItem.setChecked(true);
            setTitle("Inicio");
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String titulo;
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.menu_inicio:
                titulo = "Inicio";
                fragment = Inicio.newInstance();
                //Si inicio es pulsado se borra la cola de pulsaciones del boton atras
                //para que al ser pulsado desde inicio salga de la aplicacion
                FragmentManager fm = getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                //No utilizo addToBackStack para que al pulsar atras salga de la aplicacion y no se quede una pantalla blanca
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.menu_entrar, R.anim.menu_salir)
                        .replace(R.id.home_content, fragment, "entrenar_fragment")
                        .commit();
                break;
            case R.id.menu_entrenar:
                titulo = "Entrenar";
                fragment = Entrenar.newInstance();
                iniciarFragmento(fragment);
                break;
            case R.id.menu_listaEjercicios:
                FragmentManager fm2 = getSupportFragmentManager();
                fm2.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                titulo = "Listado de ejercicios";
                fragment = ListaEjercicios.newInstance();
                iniciarFragmento(fragment);
                break;
            case R.id.menu_imc:
                titulo = "Calcula tu IMC";
                fragment = CalcularIMC.newInstance();
                iniciarFragmento(fragment);
                break;
            case R.id.menu_consejos:
                titulo = "Consejos generales";
                fragment = ConsejosMain.newInstance();
                iniciarFragmento(fragment);
                break;
            case R.id.menu_sobreHouseFit:
                titulo = "Sobre HouseFit";
                new AlertDialog.Builder(this)
                        .setTitle(titulo)
                        .setMessage(R.string.sobreHouseFit)
                        .setPositiveButton(android.R.string.yes, null)
                        .setIcon(R.drawable.ic_housefit)
                        .show();

                return true;
            default:
                throw new IllegalArgumentException("menu_lateral option not implemented!!");
        }

        setTitle(titulo);

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    public void iniciarFragmento(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.menu_entrar, R.anim.menu_salir)
                .replace(R.id.home_content, fragment, "entrenar_fragment")
                .addToBackStack(null)
                .commit();
    }

    public static NavigationView getNavigationView() {
        return navigationView;
    }

    @Override
    public void onDrawerSlide(@NonNull View view, float v) {
        //cambio en la posición del drawer
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
        //el drawer se ha abierto completamente
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        //el drawer se ha cerrado completamente
        int posicionLateral=0;
        String titulo = getTitle()+"";
        switch (titulo) {
            case "Inicio":
                posicionLateral= 0;
                break;
            case "Entrenar":
                posicionLateral=1;
                break;
            case "Listado de ejercicios":
                posicionLateral=2;
                break;
            case "Calcula tu IMC":
                posicionLateral=3;
                break;
            case "Consejos generales":
                posicionLateral=4;
                break;
        }
        MenuItem menuItem = navigationView.getMenu().getItem(posicionLateral);
        menuItem.setChecked(true);
    }

    @Override
    public void onDrawerStateChanged(int i) {
        //cambio de estado, puede ser STATE_IDLE, STATE_DRAGGING or STATE_SETTLING
    }


}
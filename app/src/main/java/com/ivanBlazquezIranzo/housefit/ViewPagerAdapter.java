package com.ivanBlazquezIranzo.housefit;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ivanBlazquezIranzo.housefit.fragments.ConsejosFragment;

import java.util.HashMap;
import java.util.Map;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public enum Tab {

        ALIMENTACION(0, "Alimentacion"),
        POSTURAS(1, "Posturas"),
        ESTIRAMIENTOS(2, "Estiramientos"),
        DESCANSO(3, "Descanso");

        public final String titulo;
        public final int posicion;

        Tab(int position, String title) {
            this.posicion = position;
            this.titulo = title;
        }

        private static final Map<Integer,Tab> map;
        static {
            map = new HashMap<>();
            for (Tab t : Tab.values()) {
                map.put(t.posicion, t);
            }
        }

        public static Tab byPosition(int position) {
            return map.get(position);
        }
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == Tab.ALIMENTACION.posicion) {
            return ConsejosFragment.newInstance(Tab.ALIMENTACION.titulo);
        } else if (position == Tab.POSTURAS.posicion) {
            return ConsejosFragment.newInstance(Tab.POSTURAS.titulo);
        } else if (position == Tab.ESTIRAMIENTOS.posicion) {
            return ConsejosFragment.newInstance(Tab.ESTIRAMIENTOS.titulo);
        } else if (position == Tab.DESCANSO.posicion) {
            return ConsejosFragment.newInstance(Tab.DESCANSO.titulo);
        } else {
            throw new IllegalArgumentException("Posicion desconocida: " + position);
        }

    }

    @Override
    public int getItemCount() {
        return Tab.values().length;
    }
}

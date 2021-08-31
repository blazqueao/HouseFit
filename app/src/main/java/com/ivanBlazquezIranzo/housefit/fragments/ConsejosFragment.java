package com.ivanBlazquezIranzo.housefit.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivanBlazquezIranzo.housefit.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsejosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsejosFragment extends Fragment {

    private static final String ARG_TAB_TITULO = "ARG_TAB_TITULO";
    private String mParam1;

    public ConsejosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param titulo Titulo de la "scrollable tab".
     * @return A new instance of fragment ConsejosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsejosFragment newInstance(String titulo) {
        ConsejosFragment fragment = new ConsejosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TAB_TITULO, titulo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_TAB_TITULO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consejos_fragment, container, false);

        String titulo = mParam1;

        TextView textViewConsejos = view.findViewById(R.id.textViewConsejos);

        if (titulo.equalsIgnoreCase("alimentacion")) {
            textViewConsejos.setText(R.string.consejos_alimentacion);
        } else if (titulo.equalsIgnoreCase("posturas")) {
            textViewConsejos.setText(R.string.consejos_posturas);
        } else if (titulo.equalsIgnoreCase("estiramientos")) {
            textViewConsejos.setText(R.string.consejos_estiramientos);
        } else if (titulo.equalsIgnoreCase("descanso")){
            textViewConsejos.setText(R.string.consejos_descansos);
        }
        return view;
    }
}
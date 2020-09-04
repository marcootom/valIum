package com.example.valium.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valium.Contatti;
import com.example.valium.ListaPrenotazioni;
import com.example.valium.NuovaPrenotazione;
import com.example.valium.R;
import com.example.valium.Ricette;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public CardView contatti, ricette, nuovaPrenotazione, prenotazioni;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //Se l'utente preme contatti
        contatti = (CardView) root.findViewById(R.id.bottoneContatti);
        contatti.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contatti.class);
                startActivity(intent);
            }
        });
        //Se l'utente preme ricette
        ricette = (CardView) root.findViewById(R.id.bottoneLeMieRicette);
        ricette.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Ricette.class);
                startActivity(intent);
            }
        });
        //Se l'utente preme nuovaPrenotazione
        nuovaPrenotazione = (CardView) root.findViewById(R.id.bottoneNuovaPrenotazione);
        nuovaPrenotazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NuovaPrenotazione.class);
                startActivity(intent);
            }
        });
        //Se l'utente preme Le mie prenotazioni
        prenotazioni = (CardView) root.findViewById(R.id.bottoneLeMiePrenotazioni);
        prenotazioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListaPrenotazioni.class);
                startActivity(intent);
            }
        });
        return root;
    }


}

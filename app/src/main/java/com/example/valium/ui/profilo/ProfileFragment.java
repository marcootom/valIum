package com.example.valium.ui.profilo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.valium.ChangePw;
import com.example.valium.Login;
import com.example.valium.MappaUtenti;
import com.example.valium.NuovaPrenotazione;
import com.example.valium.R;
import com.example.valium.User;

import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ProfileFragment extends Fragment {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    private ProfiloViewModel galleryViewModel;
    Button changePw;
    public EditText data;
    public com.google.android.material.textfield.TextInputEditText username, password, confirmPassword, name, surname, phone, email;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(ProfiloViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profilo, container, false);
        changePw = root.findViewById(R.id.changePw);


        User u = MappaUtenti.recuperaUtente(MappaUtenti.getUtenteAttuale());
        data = root.findViewById(R.id.birthText);
        data.setText(format.format(u.getDataNascita()));
        data.setKeyListener(null);
        username = root.findViewById(R.id.codiceFiscale);
        username.setText(u.getUsername());
        username.setKeyListener(null);
        name = root.findViewById(R.id.name);
        name.setText(u.getNome());
        name.setKeyListener(null);
        surname = root.findViewById(R.id.surname);
        surname.setText(u.getCognome());
        surname.setKeyListener(null);
        phone = root.findViewById(R.id.phone);
        phone.setText(u.getTelefono());
        phone.setKeyListener(null);
        email = root.findViewById(R.id.email);
        email.setText(u.getEmail());
        email.setKeyListener(null);


        changePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePw.class);
                startActivity(intent);
            }
        });

        return root;
    }

    public void Logout(){

    }
}

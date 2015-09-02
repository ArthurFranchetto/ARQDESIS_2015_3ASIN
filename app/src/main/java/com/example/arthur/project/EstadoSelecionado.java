package com.example.arthur.project;


import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import static android.app.ProgressDialog.show;

public class EstadoSelecionado implements OnItemSelectedListener {
    String s;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        s = (String) parent.getItemAtPosition(pos);
        if (s.equals("São Paulo")) {
            Toast.makeText(parent.getContext(),
                    parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){

    }
}

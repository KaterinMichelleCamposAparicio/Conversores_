package com.example.conversores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Masa_Activity extends AppCompatActivity {
    private static final String[] unidadesMasa = {"Kilogramos (kg)", "Libras (lb)", "Gramos (g)", "Onzas (Oz)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.masa_main);

        Button btnBack = findViewById(R.id.btnBackM);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Masa_Activity.this, MainActivity.class);
                startActivity(back);
            }
        });


        Spinner spinnerM1 = findViewById(R.id.SpinnerM1);
        Spinner spinnerM2 = findViewById(R.id.SpinnerM2);

        ArrayAdapter<String> adapterM1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesMasa);
        ArrayAdapter<String> adapterM2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesMasa);

        adapterM1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterM2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerM1.setAdapter(adapterM1);
        spinnerM2.setAdapter(adapterM2);

        final EditText editCantidad = findViewById(R.id.editCantidadM);
        Button btnConvert = findViewById(R.id.btnConvertM);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editCantidad.getText().toString().isEmpty()) {
                    double cantidad = Double.parseDouble(editCantidad.getText().toString());
                    int origenIndex = spinnerM1.getSelectedItemPosition();
                    int destinoIndex = spinnerM2.getSelectedItemPosition();

                    if (origenIndex == destinoIndex) {
                        Toast.makeText(Masa_Activity.this, "No se puede convertir a la misma unidad", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double resultado = convertirMasa(cantidad, origenIndex, destinoIndex);

                    Toast.makeText(Masa_Activity.this, String.format("%.2f %s son %.2f %s", cantidad,
                            unidadesMasa[origenIndex], resultado, unidadesMasa[destinoIndex]), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Masa_Activity.this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private double convertirMasa(double cantidad, int unidadOrigen, int unidadDestino) {
        switch (unidadOrigen) {
            case 0: // Kilogramos
                switch (unidadDestino) {
                    case 0:
                        return cantidad; // Kilogramos a Kilogramos
                    case 1:
                        return cantidad * 2.20462; // Kilogramos a Libras
                    case 2:
                        return cantidad * 1000.0; // Kilogramos a Gramos
                    case 3:
                        return cantidad * 35.274; // Kilogramos a Onzas
                }
                break;
            case 1: // Libras
                switch (unidadDestino) {
                    case 0:
                        return cantidad / 2.20462; // Libras a Kilogramos
                    case 1:
                        return cantidad; // Libras a Libras
                    case 2:
                        return cantidad * 453.592; // Libras a Gramos
                    case 3:
                        return cantidad * 16.0; // Libras a Onzas
                }
                break;
            case 2: // Gramos
                switch (unidadDestino) {
                    case 0:
                        return cantidad / 1000.0; // Gramos a Kilogramos
                    case 1:
                        return cantidad / 453.592; // Gramos a Libras
                    case 2:
                        return cantidad; // Gramos a Gramos
                    case 3:
                        return cantidad / 28.3495; // Gramos a Onzas
                }
                break;
            case 3: // Onzas
                switch (unidadDestino) {
                    case 0:
                        return cantidad / 35.274; // Onzas a Kilogramos
                    case 1:
                        return cantidad / 16.0; // Onzas a Libras
                    case 2:
                        return cantidad * 28.3495; // Onzas a Gramos
                    case 3:
                        return cantidad; // Onzas a Onzas
                }
                break;
        }
        return 1.0;
    }
}

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

public class VolumenActivity extends AppCompatActivity {

    private static final String[] unidadesVolumen = {"Litros (L)", "Metros cúbicos (m³)", "Mililitros (mL)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volumen_main);

        Button btnBack = (Button) findViewById(R.id.btnBackV);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back = new Intent(VolumenActivity.this, MainActivity.class);
                startActivity(back);
            }
        });

        Spinner spinnerV1 = findViewById(R.id.SpinnerV1);
        Spinner spinnerV2 = findViewById(R.id.SpinnerV2);

        ArrayAdapter<String> adapterV1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesVolumen);
        ArrayAdapter<String> adapterV2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesVolumen);

        adapterV1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterV2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerV1.setAdapter(adapterV1);
        spinnerV2.setAdapter(adapterV2);

        final EditText editCantidad = findViewById(R.id.editCantidadV);
        Button btnConvert = findViewById(R.id.btnConvertV);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editCantidad.getText().toString().isEmpty()) {
                    double cantidad = Double.parseDouble(editCantidad.getText().toString());
                    int origenIndex = spinnerV1.getSelectedItemPosition();
                    int destinoIndex = spinnerV2.getSelectedItemPosition();

                    if (origenIndex == destinoIndex) {
                        Toast.makeText(VolumenActivity.this, "No se puede convertir a la misma unidad", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double resultado = convertirVolumen(cantidad, origenIndex, destinoIndex);

                    Toast.makeText(VolumenActivity.this, String.format("%.2f %s son %.2f %s", cantidad,
                            unidadesVolumen[origenIndex], resultado, unidadesVolumen[destinoIndex]), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VolumenActivity.this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                }
            }
            private double convertirVolumen(double cantidad, int unidadOrigen, int unidadDestino) {
                switch (unidadOrigen) {
                    case 0: // Litros
                        switch (unidadDestino) {
                            case 0:
                                return cantidad; // Litros a Litros
                            case 1:
                                return cantidad * 0.001; // Litros a Metros cúbicos
                            case 2:
                                return cantidad * 1000.0; // Litros a Mililitros
                        }
                        break;
                    case 1: // Metros cúbicos
                        switch (unidadDestino) {
                            case 0:
                                return cantidad * 1000.0; // Metros cúbicos a Litros
                            case 1:
                                return cantidad; // Metros cúbicos a Metros cúbicos
                            case 2:
                                return cantidad * 1_000_000.0; // Metros cúbicos a Mililitros
                        }
                        break;
                    case 2: // Mililitros
                        switch (unidadDestino) {
                            case 0:
                                return cantidad * 0.001; // Mililitros a Litros
                            case 1:
                                return cantidad * 0.000001; // Mililitros a Metros cúbicos
                            case 2:
                                return cantidad; // Mililitros a Mililitros
                        }
                        break;
                }
                return 1.0;
            }
        });
    }
}

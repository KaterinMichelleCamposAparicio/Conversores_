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

public class Tiempo_Activity extends AppCompatActivity {

    private static final String[] unidadesTiempo = {"Horas (h)", "Minutos (min)", "Segundos (s)"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiempo_main);

        Button btnBack = (Button) findViewById(R.id.btnBackT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back = new Intent(Tiempo_Activity.this, MainActivity.class);
                startActivity(back);
            }
        });

        Spinner spinnerT1 = findViewById(R.id.SpinnerT1);
        Spinner spinnerT2 = findViewById(R.id.SpinnerT2);

        ArrayAdapter<String> adapterT1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesTiempo);
        ArrayAdapter<String> adapterT2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesTiempo);

        adapterT1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterT2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerT1.setAdapter(adapterT1);
        spinnerT2.setAdapter(adapterT2);

        final EditText editCantidad = findViewById(R.id.editCantidadT);
        Button btnConvert = findViewById(R.id.btnConvertT);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editCantidad.getText().toString().isEmpty()) {
                    double cantidad = Double.parseDouble(editCantidad.getText().toString());
                    int origenIndex = spinnerT1.getSelectedItemPosition();
                    int destinoIndex = spinnerT2.getSelectedItemPosition();

                    if (origenIndex == destinoIndex) {
                        Toast.makeText(Tiempo_Activity.this, "No se puede convertir a la misma unidad", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double resultado = convertirTiempo(cantidad, origenIndex, destinoIndex);

                    Toast.makeText(Tiempo_Activity.this, String.format("%.2f %s son %.2f %s", cantidad,
                            unidadesTiempo[origenIndex], resultado, unidadesTiempo[destinoIndex]), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Tiempo_Activity.this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                }
            }

            private double convertirTiempo(double cantidad, int unidadOrigen, int unidadDestino) {
                switch (unidadOrigen) {
                    case 0: // Horas
                        switch (unidadDestino) {
                            case 0:
                                return cantidad; // Horas a Horas
                            case 1:
                                return cantidad * 60.0; // Horas a Minutos
                            case 2:
                                return cantidad * 3600.0; // Horas a Segundos
                        }
                        break;
                    case 1: // Minutos
                        switch (unidadDestino) {
                            case 0:
                                return cantidad / 60.0; // Minutos a Horas
                            case 1:
                                return cantidad; // Minutos a Minutos
                            case 2:
                                return cantidad * 60.0; // Minutos a Segundos
                        }
                        break;
                    case 2: // Segundos
                        switch (unidadDestino) {
                            case 0:
                                return cantidad / 3600.0; // Segundos a Horas
                            case 1:
                                return cantidad / 60.0; // Segundos a Minutos
                            case 2:
                                return cantidad; // Segundos a Segundos
                        }
                        break;
                }
                return 1.0;
            }
        });
    }
}

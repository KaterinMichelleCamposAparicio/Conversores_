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

public class Longitud_Activity extends AppCompatActivity {

    private static final String[] unidadesLongitud = {"Centímetros (cm)", "Decímetros (dm)", "Metros (m)", "Kilómetros (km)", "Pulgadas (in)", "Pies (ft)", "Yardas (yd)", "Millas (mi)"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longitud_main);

        Button btnBack = (Button) findViewById(R.id.btnBackL);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back = new Intent(Longitud_Activity.this, MainActivity.class);
                startActivity(back);
            }
        });

        Spinner spinnerL1 = findViewById(R.id.SpinnerL1);
        Spinner spinnerL2 = findViewById(R.id.SpinnerL2);

        ArrayAdapter<String> adapterL1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesLongitud);
        ArrayAdapter<String> adapterL2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesLongitud);

        adapterL1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterL2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerL1.setAdapter(adapterL1);
        spinnerL2.setAdapter(adapterL2);

        final EditText editCantidad = findViewById(R.id.editCantidadL);
        Button btnConvert = findViewById(R.id.btnConvertL);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editCantidad.getText().toString().isEmpty()) {
                    double cantidad = Double.parseDouble(editCantidad.getText().toString());
                    int origenIndex = spinnerL1.getSelectedItemPosition();
                    int destinoIndex = spinnerL2.getSelectedItemPosition();

                    if (origenIndex == destinoIndex) {
                        Toast.makeText(Longitud_Activity.this, "No se puede convertir a la misma unidad", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double resultado = convertirLongitud(cantidad, origenIndex, destinoIndex);

                    Toast.makeText(Longitud_Activity.this, String.format("%.2f %s son %.2f %s", cantidad,
                            unidadesLongitud[origenIndex], resultado, unidadesLongitud[destinoIndex]), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Longitud_Activity.this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                }
            }

            private double convertirLongitud(double cantidad, int unidadOrigen, int unidadDestino) {
                switch (unidadOrigen) {
                    case 0: // Centímetros
                        switch (unidadDestino) {
                            case 0:
                                return cantidad; // Centímetros a Centímetros
                            case 1:
                                return cantidad / 10.0; // Centímetros a Decímetros
                            case 2:
                                return cantidad / 100.0; // Centímetros a Metros
                            case 3:
                                return cantidad / 1000.0; // Centímetros a Kilómetros
                            case 4:
                                return cantidad * 0.393701; // Centímetros a Pulgadas
                            case 5:
                                return cantidad * 0.0328084; // Centímetros a Pies
                            case 6:
                                return cantidad * 0.0109361; // Centímetros a Yardas
                            case 7:
                                return cantidad * 0.00000621371; // Centímetros a Millas
                        }
                        break;
                    case 1: // Decímetros
                        switch (unidadDestino) {
                            case 0:
                                return cantidad * 10.0; // Decímetros a Centímetros
                            case 1:
                                return cantidad; // Decímetros a Decímetros
                            case 2:
                                return cantidad / 10.0; // Decímetros a Metros
                            case 3:
                                return cantidad / 100.0; // Decímetros a Kilómetros
                            case 4:
                                return cantidad * 3.93701; // Decímetros a Pulgadas
                            case 5:
                                return cantidad * 0.328084; // Decímetros a Pies
                            case 6:
                                return cantidad * 0.109361; // Decímetros a Yardas
                            case 7:
                                return cantidad * 0.0000621371; // Decímetros a Millas
                        }
                        break;
                    case 2: // Metros
                        switch (unidadDestino) {
                            case 0:
                                return cantidad * 100.0; // Metros a Centímetros
                            case 1:
                                return cantidad * 10.0; // Metros a Decímetros
                            case 2:
                                return cantidad; // Metros a Metros
                            case 3:
                                return cantidad / 1000.0; // Metros a Kilómetros
                            case 4:
                                return cantidad * 39.3701; // Metros a Pulgadas
                            case 5:
                                return cantidad * 3.28084; // Metros a Pies
                            case 6:
                                return cantidad * 1.09361; // Metros a Yardas
                            case 7:
                                return cantidad * 0.000621371; // Metros a Millas
                        }
                        break;
                    case 3: // Kilómetros
                        switch (unidadDestino) {
                            case 0:
                                return cantidad * 100000.0; // Kilómetros a Centímetros
                            case 1:
                                return cantidad * 10000.0; // Kilómetros a Decímetros
                            case 2:
                                return cantidad * 1000.0; // Kilómetros a Metros
                            case 3:
                                return cantidad; // Kilómetros a Kilómetros
                            case 4:
                                return cantidad * 39370.1; // Kilómetros a Pulgadas
                            case 5:
                                return cantidad * 3280.84; // Kilómetros a Pies
                            case 6:
                                return cantidad * 1093.61; // Kilómetros a Yardas
                            case 7:
                                return cantidad * 0.621371; // Kilómetros a Millas
                        }
                        break;
                    case 4: // Pulgadas
                        switch (unidadDestino) {
                            case 0:
                                return cantidad * 2.54; // Pulgadas a Centímetros
                            case 1:
                                return cantidad * 0.254; // Pulgadas a Decímetros
                            case 2:
                                return cantidad * 0.0254; // Pulgadas a Metros
                            case 3:
                                return cantidad * 0.0000254; // Pulgadas a Kilómetros
                            case 4:
                                return cantidad; // Pulgadas a Pulgadas
                            case 5:
                                return cantidad * 0.0833333; // Pulgadas a Pies
                            case 6:
                                return cantidad * 0.0277778; // Pulgadas a Yardas
                            case 7:
                                return cantidad * 0.000015783; // Pulgadas a Millas
                        }
                        break;
                    case 5: // Pies
                        switch (unidadDestino) {
                            case 0:
                                return cantidad * 30.48; // Pies a Centímetros
                            case 1:
                                return cantidad * 3.048; // Pies a Decímetros
                            case 2:
                                return cantidad * 0.3048; // Pies a Metros
                            case 3:
                                return cantidad * 0.0003048; // Pies a Kilómetros
                            case 4:
                                return cantidad * 12.0; // Pies a Pulgadas
                            case 5:
                                return cantidad; // Pies a Pies
                            case 6:
                                return cantidad * 0.333333; // Pies a Yardas
                            case 7:
                                return cantidad * 0.000189394; // Pies a Millas
                        }
                        break;
                    case 6: // Yardas
                        switch (unidadDestino) {
                            case 0:
                                return cantidad * 91.44; // Yardas a Centímetros
                            case 1:
                                return cantidad * 9.144; // Yardas a Decímetros
                            case 2:
                                return cantidad * 0.9144; // Yardas a Metros
                            case 3:
                                return cantidad * 0.0009144; // Yardas a Kilómetros
                            case 4:
                                return cantidad * 36.0; // Yardas a Pulgadas
                            case 5:
                                return cantidad * 3.0; // Yardas a Pies
                            case 6:
                                return cantidad; // Yardas a Yardas
                            case 7:
                                return cantidad * 0.000568182; // Yardas a Millas
                        }
                        break;
                    case 7: // Millas
                        switch (unidadDestino) {
                            case 0:
                                return cantidad * 160934.0; // Millas a Centímetros
                            case 1:
                                return cantidad * 16093.4; // Millas a Decímetros
                            case 2:
                                return cantidad * 1609.34; // Millas a Metros
                            case 3:
                                return cantidad * 1.60934; // Millas a Kilómetros
                            case 4:
                                return cantidad * 63360.0; // Millas a Pulgadas
                            case 5:
                                return cantidad * 5280.0; // Millas a Pies
                            case 6:
                                return cantidad * 1760.0; // Millas a Yardas
                            case 7:
                                return cantidad; // Millas a Millas
                        }
                        break;
                }
                return 1.0;
            }
        });
    }
}

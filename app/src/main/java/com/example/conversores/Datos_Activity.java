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

public class Datos_Activity extends AppCompatActivity {

    private static final String[] unidadesT_Datos = {"Bytes/s", "Kilobytes/s", "Megabytes/s", "Gigabytes/s", "Terabytes/s"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_dts_main);

        Button btnBack = (Button) findViewById(R.id.btnBackD);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back = new Intent(Datos_Activity.this, MainActivity.class);
                startActivity(back);
            }
        });

        Spinner spinnerD1 = findViewById(R.id.SpinnerD1);
        Spinner spinnerD2 = findViewById(R.id.SpinnerD2);

        ArrayAdapter<String> adapterD1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesT_Datos);
        ArrayAdapter<String> adapterD2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesT_Datos);

        adapterD1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterD2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerD1.setAdapter(adapterD1);
        spinnerD2.setAdapter(adapterD2);

        final EditText editCantidad = findViewById(R.id.editCantidadD);
        Button btnConvert = findViewById(R.id.btnCalcular);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editCantidad.getText().toString().isEmpty()) {
                    double cantidad = Double.parseDouble(editCantidad.getText().toString());
                    int origenIndex = spinnerD1.getSelectedItemPosition();
                    int destinoIndex = spinnerD2.getSelectedItemPosition();

                    if (origenIndex == destinoIndex) {
                        Toast.makeText(Datos_Activity.this, "No se puede convertir a la misma unidad", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double resultado = calcularT_Datos(cantidad, origenIndex, destinoIndex);

                    Toast.makeText(Datos_Activity.this, String.format("%.2f %s son %.2f %s", cantidad,
                            unidadesT_Datos[origenIndex], resultado, unidadesT_Datos[destinoIndex]), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Datos_Activity.this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                }
            }

            private double calcularT_Datos(double cantidad, int unidadOrigen, int unidadDestino) {
                // Conversiones base a Bytes
                double T_Bytes = 0.0;

                switch (unidadOrigen) {
                    case 0: // Bytes
                        T_Bytes = cantidad;
                        break;
                    case 1: // Kilobytes
                        T_Bytes = cantidad * 1024.0;
                        break;
                    case 2: // Megabytes
                        T_Bytes = cantidad * 1024.0 * 1024.0;
                        break;
                    case 3: // Gigabytes
                        T_Bytes = cantidad * 1024.0 * 1024.0 * 1024.0;
                        break;
                    case 4: // Terabytes
                        T_Bytes = cantidad * 1024.0 * 1024.0 * 1024.0 * 1024.0;
                        break;
                }

                // Velocidad de transferencia
                double T_Segundos = 1.0;  // Tiempo en segundos (puedes ajustar según sea necesario)
                switch (unidadDestino) {
                    case 0: // Bytes por segundo
                        return T_Bytes / T_Segundos;
                    case 1: // Kilobytes por segundo
                        return (T_Bytes / 1024.0) / T_Segundos;
                    case 2: // Megabytes por segundo
                        return (T_Bytes / (1024.0 * 1024.0)) / T_Segundos;
                    case 3: // Gigabytes por segundo
                        return (T_Bytes / (1024.0 * 1024.0 * 1024.0)) / T_Segundos;
                    case 4: // Terabytes por segundo
                        return (T_Bytes / (1024.0 * 1024.0 * 1024.0 * 1024.0)) / T_Segundos;
                    default:
                        return 0.0;
                }
            }
        });
    }
}
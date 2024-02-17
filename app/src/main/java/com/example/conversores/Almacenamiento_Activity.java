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

public class Almacenamiento_Activity extends AppCompatActivity {

    private static final String[] unidadesAlmacenamiento = {"Gigabytes (GB)", "Megabytes (MB)", "Kilobytes (KB)", "Bytes", "Bits", "Gigabits (Gb)", "Megabits (Mb)"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.almacenamiento_main);

        Button btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back = new Intent(Almacenamiento_Activity.this, MainActivity.class);
                startActivity(back);
            }
        });

        Spinner spinnerA1 = findViewById(R.id.SpinnerA1);
        Spinner spinnerA2 = findViewById(R.id.SpinnerA2);

        ArrayAdapter<String> adapterA1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesAlmacenamiento);
        ArrayAdapter<String> adapterA2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesAlmacenamiento);

        adapterA1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterA2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerA1.setAdapter(adapterA1);
        spinnerA2.setAdapter(adapterA2);

        final EditText editCantidad = findViewById(R.id.editCantidadA);
        Button btnConvert = findViewById(R.id.btnConvertA);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editCantidad.getText().toString().isEmpty()) {
                    double cantidad = Double.parseDouble(editCantidad.getText().toString());
                    int origenIndex = spinnerA1.getSelectedItemPosition();
                    int destinoIndex = spinnerA2.getSelectedItemPosition();

                    if (origenIndex == destinoIndex) {
                        Toast.makeText(Almacenamiento_Activity.this, "No se puede convertir a la misma unidad", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double resultado = convertirAlmacenamiento(cantidad, origenIndex, destinoIndex);

                    Toast.makeText(Almacenamiento_Activity.this, String.format("%.2f %s son %.2f %s", cantidad,
                            unidadesAlmacenamiento[origenIndex], resultado, unidadesAlmacenamiento[destinoIndex]), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Almacenamiento_Activity.this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
                }
            }

            private double convertirAlmacenamiento(double cantidad, int unidadOrigen, int unidadDestino) {
                switch (unidadOrigen) {
                    case 0: // Gigabytes
                        switch (unidadDestino) {
                            case 0:
                                return cantidad; // Gigabytes a Gigabytes
                            case 1:
                                return cantidad * 1024.0; // Gigabytes a Megabytes
                            case 2:
                                return cantidad * 1024 * 1024.0; // Gigabytes a Kilobytes
                            case 3:
                                return cantidad * 1024 * 1024 * 1024.0; // Gigabytes a Bytes
                            case 4:
                                return cantidad * 1024 * 1024 * 1024 * 8.0; // Gigabytes a Bits
                            case 5:
                                return cantidad * 8.0; // Gigabytes a Gigabits
                            case 6:
                                return cantidad * 1024 * 8.0; // Gigabytes a Megabits
                        }
                        break;
                    case 1: // Megabytes
                        switch (unidadDestino) {
                            case 0:
                                return cantidad / 1024.0; // Megabytes a Gigabytes
                            case 1:
                                return cantidad; // Megabytes a Megabytes
                            case 2:
                                return cantidad * 1024.0; // Megabytes a Kilobytes
                            case 3:
                                return cantidad * 1024 * 1024.0; // Megabytes a Bytes
                            case 4:
                                return cantidad * 1024 * 1024 * 8.0; // Megabytes a Bits
                            case 5:
                                return cantidad * 8.0 / 1024.0; // Megabytes a Gigabits
                            case 6:
                                return cantidad * 8.0; // Megabytes a Megabits
                        }
                        break;
                    case 2: // Kilobytes
                        switch (unidadDestino) {
                            case 0:
                                return cantidad / (1024.0 * 1024.0); // Kilobytes a Gigabytes
                            case 1:
                                return cantidad / 1024.0; // Kilobytes a Megabytes
                            case 2:
                                return cantidad; // Kilobytes a Kilobytes
                            case 3:
                                return cantidad * 1024.0; // Kilobytes a Bytes
                            case 4:
                                return cantidad * 1024 * 8.0; // Kilobytes a Bits
                            case 5:
                                return cantidad * 8.0 / (1024.0 * 1024.0); // Kilobytes a Gigabits
                            case 6:
                                return cantidad * 8.0 / 1024.0; // Kilobytes a Megabits
                        }
                        break;
                    case 3: // Bytes
                        switch (unidadDestino) {
                            case 0:
                                return cantidad / (1024.0 * 1024.0 * 1024.0); // Bytes a Gigabytes
                            case 1:
                                return cantidad / (1024.0 * 1024.0); // Bytes a Megabytes
                            case 2:
                                return cantidad / 1024.0; // Bytes a Kilobytes
                            case 3:
                                return cantidad; // Bytes a Bytes
                            case 4:
                                return cantidad * 8.0; // Bytes a Bits
                            case 5:
                                return cantidad * 8.0 / (1024.0 * 1024.0 * 1024.0); // Bytes a Gigabits
                            case 6:
                                return cantidad * 8.0 / (1024.0 * 1024.0); // Bytes a Megabits
                        }
                        break;
                    case 4: // Bits
                        switch (unidadDestino) {
                            case 0:
                                return cantidad / (1024.0 * 1024.0 * 1024.0 * 8.0); // Bits a Gigabytes
                            case 1:
                                return cantidad / (1024.0 * 1024.0 * 8.0); // Bits a Megabytes
                            case 2:
                                return cantidad / (1024.0 * 8.0); // Bits a Kilobytes
                            case 3:
                                return cantidad / 8.0; // Bits a Bytes
                            case 4:
                                return cantidad; // Bits a Bits
                            case 5:
                                return cantidad / (1024.0 * 1024.0 * 1024.0); // Bits a Gigabits
                            case 6:
                                return cantidad / (1024.0 * 1024.0); // Bits a Megabits
                        }
                        break;
                    case 5: // Gigabits
                        switch (unidadDestino) {
                            case 0:
                                return cantidad / 8.0 / 1024.0 / 1024.0 / 1024.0; // Gigabits a Gigabytes
                            case 1:
                                return cantidad / 8.0 / 1024.0 / 1024.0; // Gigabits a Megabytes
                            case 2:
                                return cantidad / 8.0 / 1024.0; // Gigabits a Kilobytes
                            case 3:
                                return cantidad / 8.0; // Gigabits a Bytes
                            case 4:
                                return cantidad * (1024.0 * 1024.0 * 8.0); // Gigabits a Bits
                            case 5:
                                return cantidad; // Gigabits a Gigabits
                            case 6:
                                return cantidad * 1024.0; // Gigabits a Megabits
                        }
                        break;
                    case 6: // Megabits
                        switch (unidadDestino) {
                            case 0:
                                return cantidad / 8.0 / 1024.0 / 1024.0 / 1024.0; // Megabits a Gigabytes
                            case 1:
                                return cantidad / 8.0 / 1024.0 / 1024.0; // Megabits a Megabytes
                            case 2:
                                return cantidad / 8.0 / 1024.0; // Megabits a Kilobytes
                            case 3:
                                return cantidad / 8.0; // Megabits a Bytes
                            case 4:
                                return cantidad * (1024.0 * 8.0); // Megabits a Bits
                            case 5:
                                return cantidad / 1024.0; // Megabits a Gigabits
                            case 6:
                                return cantidad; // Megabits a Megabits
                        }
                        break;
                }
                return 1.0;
            }


        });
    }
}

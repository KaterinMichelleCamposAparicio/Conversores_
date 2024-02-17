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

public class moneda_activity extends AppCompatActivity {
        private static final String[] Convertir_De = {"Dólares (USD)", "Euros (EUR)", "Mexicanos (MXN)", "Yenes (JPY)"};
        private static final String[] Convertir_A = {"Dólares (USD)", "Euros (EUR)", "Mexicanos (MXN)", "Yenes (JPY)"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.moneda_main);

                Button btnBack = (Button) findViewById(R.id.btnBack);

                btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent back = new Intent(moneda_activity.this, MainActivity.class);
                                startActivity(back);
                        }
                });

                Spinner Spinner1 = (Spinner) findViewById(R.id.Spinner1);
                Spinner Spinner2 = (Spinner) findViewById(R.id.Spinner2);

                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Convertir_De);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Convertir_A);

                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Spinner1.setAdapter(adapter1);
                Spinner2.setAdapter(adapter2);

                final EditText editCantidad = findViewById(R.id.editCantidad);
                final Button btnConvert = findViewById(R.id.btnConvert);

                btnConvert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                if (!editCantidad.getText().toString().isEmpty()) {
                                        double amount = Double.parseDouble(editCantidad.getText().toString());

                                        int origenIndex = Spinner1.getSelectedItemPosition();
                                        int destinoIndex = Spinner2.getSelectedItemPosition();



                                        if (origenIndex == destinoIndex) {
                                                CharSequence text = "No se puede convertir a la misma moneda";
                                                int duration = Toast.LENGTH_SHORT;

                                                Toast toast = Toast.makeText(moneda_activity.this, text, duration);
                                                toast.show();
                                                return;
                                        }

                                        double conversionRate = getConversionRate(origenIndex, destinoIndex);

                                        double result = amount * conversionRate;

                                        CharSequence text = String.format("%.2f %s son %.2f %s", amount, Convertir_De[origenIndex], result, Convertir_A[destinoIndex]);
                                        int duration = Toast.LENGTH_SHORT;

                                        Toast toast = Toast.makeText(moneda_activity.this, text, duration);
                                        toast.show();
                                } else {
                                        CharSequence text = "Ingrese una cantidad";
                                        int duration = Toast.LENGTH_SHORT;

                                        Toast toast = Toast.makeText(moneda_activity.this, text, duration);
                                        toast.show();
                                }
                        }
                });
        }

        private double getConversionRate(int origenIndex, int destinoIndex) {

                switch (origenIndex) {
                        case 0: // Dólares
                                switch (destinoIndex) {
                                        case 0:
                                                return 1.0; // Dólares a Dólares
                                        case 1:
                                                return 0.93; // Dólares a Euros
                                        case 2:
                                                return 20.43; // Dólares a Pesos Mexicanos
                                        case 3:
                                                return 132.43; // Dólares a Yenes
                                        default:
                                                return 1.0;
                                }
                        case 1: // Euros
                                switch (destinoIndex) {
                                        case 0:
                                                return 1.08; // Euros a Dólares
                                        case 1:
                                                return 1.0; // Euros a Euros
                                        case 2:
                                                return 22.07; // Euros a Pesos Mexicanos
                                        case 3:
                                                return 143.21; // Euros a Yenes
                                        default:
                                                return 1.0;
                                }
                        case 2: // Pesos Mexicanos
                                switch (destinoIndex) {
                                        case 0:
                                                return 0.049; // Pesos Mexicanos a Dólares
                                        case 1:
                                                return 0.045; // Pesos Mexicanos a Euros
                                        case 2:
                                                return 1.0; // Pesos Mexicanos a Pesos Mexicanos
                                        case 3:
                                                return 6.49; // Pesos Mexicanos a Yenes
                                        default:
                                                return 1.0;
                                }
                        case 3: // Yenes
                                switch (destinoIndex) {
                                        case 0:
                                                return 0.0075; // Yenes a Dólares
                                        case 1:
                                                return 0.0069; // Yenes a Euros
                                        case 2:
                                                return 0.154; // Yenes a Pesos Mexicanos
                                        case 3:
                                                return 1.0; // Yenes a Yenes
                                        default:
                                                return 1.0;
                                }
                        default:
                                return 1.0;
                }
        }
}

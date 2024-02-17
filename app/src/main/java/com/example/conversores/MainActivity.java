package com.example.conversores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Next = (Button) findViewById(R.id.btnConversion);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other = new Intent(MainActivity.this, moneda_activity.class);
                startActivity(other);
            }
        });

        Button Next1 = (Button) findViewById(R.id.btnMasa);

        Next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other1 = new Intent(MainActivity.this, Masa_Activity.class);
                startActivity(other1);
            }
        });

        Button Next2 = (Button) findViewById(R.id.btnVolumen);

        Next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other2 = new Intent(MainActivity.this, VolumenActivity.class);
                startActivity(other2);
            }
        });

        Button Next3 = (Button) findViewById(R.id.btn_Almacenamiento);

        Next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other3 = new Intent(MainActivity.this, Almacenamiento_Activity.class);
                startActivity(other3);
            }
        });

        Button Next4 = (Button) findViewById(R.id.btnLongitud);

        Next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other4 = new Intent(MainActivity.this, Longitud_Activity.class);
                startActivity(other4);
            }
        });

        Button Next5 = (Button) findViewById(R.id.btnTiempo);

        Next5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other5 = new Intent(MainActivity.this, Tiempo_Activity.class);
                startActivity(other5);
            }
        });

        Button Next6 = (Button) findViewById(R.id.btnDatos);

        Next6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other6 = new Intent(MainActivity.this, Datos_Activity.class);
                startActivity(other6);
            }
        });

    }
}
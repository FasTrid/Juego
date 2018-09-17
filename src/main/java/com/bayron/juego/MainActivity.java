package com.bayron.juego;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CheckBox> lstChechBox;

    private RadioButton Player1;
    private RadioButton Player2;
    private Button boton;
    private Button reinicia;
    private  Boolean ganador = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inicializamos el Arreglo de CheckBox
        lstChechBox = new ArrayList<>();
        lstChechBox.add((CheckBox) findViewById(R.id.posicion0));
        lstChechBox.add((CheckBox) findViewById(R.id.posicion1));
        lstChechBox.add((CheckBox) findViewById(R.id.posicion2));
        lstChechBox.add((CheckBox) findViewById(R.id.posicion3));
        lstChechBox.add((CheckBox) findViewById(R.id.posicion4));
        lstChechBox.add((CheckBox) findViewById(R.id.posicion5));
        lstChechBox.add((CheckBox) findViewById(R.id.posicion6));
        lstChechBox.add((CheckBox) findViewById(R.id.posicion7));
        lstChechBox.add((CheckBox) findViewById(R.id.posicion8));

        reinicia = findViewById(R.id.Reinicio);
        boton = findViewById(R.id.iniciar);
        Player1 = findViewById(R.id.btnplayer1);
        Player2 = findViewById(R.id.btnplayer2);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iniciar();
                boton.setEnabled(false);
                IniciarLetra();
            }
        });

        reinicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reiniciar();
                reinicia.setVisibility(View.INVISIBLE);
                boton.setEnabled(true);
            }
        });
    }

    private void IniciarLetra(){
        for(final CheckBox check:lstChechBox){
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                            if (Player1.isChecked()) {
                                buttonView.setText("X");
                                Player1.setEnabled(false);
                                Player2.setChecked(true);
                                Player2.setEnabled(true);
                                Ganador("X");
                            } else {
                                int id = Resources.getSystem().getIdentifier("btn_check_holo_light", "drawable", "android");
                                buttonView.setButtonDrawable(id);
                                buttonView.setText("0");
                                Player2.setEnabled(false);
                                Player1.setChecked(true);
                                Player1.setEnabled(true);
                                Ganador("0");
                            }
                    }
                }
            });
        }
    }
    private void   Iniciar(){
        for(int i =0;i<lstChechBox.size();i++){
            lstChechBox.get(i).setEnabled(true);
            lstChechBox.get(i).setText("");
        }

        Player1.setEnabled(true);
        Player2.setEnabled(true);
    }

    private void   Reiniciar(){
        for(int i =0;i<lstChechBox.size();i++){
            if(ganador){
                lstChechBox.get(i).setChecked(true);
            }else{
                lstChechBox.get(i).setChecked(false);
                lstChechBox.get(i).setText("");
            }
            lstChechBox.get(i).setEnabled(false);

        }
        Player1.setEnabled(false);
        Player2.setEnabled(false);
    }
    private void Ganador(String letra){
        if(
                (lstChechBox.get(0).getText().toString().equals(letra) && lstChechBox.get(1).getText().toString().equals(letra)  &lstChechBox.get(2).getText().toString().equals(letra)||
                        lstChechBox.get(3).getText().toString().equals(letra)&& lstChechBox.get(4).getText().toString().equals(letra) && lstChechBox.get(5).getText().toString().equals(letra) ||
                        lstChechBox.get(6).getText().toString().equals(letra) && lstChechBox.get(7).getText().toString().equals(letra)  && lstChechBox.get(8).getText().toString().equals(letra) ||
                        lstChechBox.get(0).getText().toString().equals(letra) && lstChechBox.get(3).getText().toString().equals(letra)  && lstChechBox.get(6).getText().toString().equals(letra) ||
                        lstChechBox.get(1).getText().toString().equals(letra)&& lstChechBox.get(4).getText().toString().equals(letra)&& lstChechBox.get(7).getText().toString().equals(letra) ||
                        lstChechBox.get(2).getText().toString().equals(letra) && lstChechBox.get(5).getText().toString().equals(letra)  && lstChechBox.get(8).getText().toString().equals(letra) ||
                        lstChechBox.get(0).getText().toString().equals(letra)&& lstChechBox.get(4).getText().toString().equals(letra) && lstChechBox.get(8).getText().toString().equals(letra) ||
                        lstChechBox.get(2).getText().toString().equals(letra) && lstChechBox.get(4).getText().toString().equals(letra) && lstChechBox.get(6).getText().toString().equals(letra)
                )){
            if(letra.equals("X")) {
                Toast.makeText(this, "Jugador Numero 1 Gana", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Jugador Numero 2 Gana", Toast.LENGTH_SHORT).show();
            }
            reinicia.setVisibility(View.VISIBLE);
            ganador = true;
            Reiniciar();
        }
    }
}

package com.example.jogodaforca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Home extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private boolean isDarkModeActivated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startService(new Intent(this, musica.class));
        // Inicialize o SensorManager
        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);

        // Obtenha uma referência ao sensor de luz ambiente
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void jogar(View v){
        Intent intent = new Intent(this, CriacaoUsuario.class);
        startActivity(intent);
    }

    public void placar(View v){
        Intent intent = new Intent(this, Placar.class);
        startActivity(intent);
    }

    public void creditos(View v){
        Intent intent = new Intent(this, Creditos.class);
        startActivity(intent);
    }

    public void sair(View v){
        finishAffinity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, musica.class));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float luminosity = event.values[0]; // Valor de luminosidade atual

        // Defina um limite de luminosidade para ativar o modo escuro (ajuste conforme necessário)
        float luminosityThreshold = 8.0f;

        if (luminosity < luminosityThreshold && !isDarkModeActivated) {
            // Ative o modo escuro
            activateDarkMode();
        } else if (luminosity >= luminosityThreshold && isDarkModeActivated) {
            // Desative o modo escuro
            deactivateDarkMode();
        }
    }

    private void activateDarkMode() {
        // Implemente as alterações necessárias para ativar o modo escuro
        // Por exemplo, você pode alterar o tema da atividade, as cores dos elementos da UI, etc.

        // Exemplo de alteração do tema para o modo escuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        isDarkModeActivated = true;
    }

    private void deactivateDarkMode() {
        //reverter o tema para o modo claro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        isDarkModeActivated = false;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Não é necessário implementar este método, mas ele é obrigatório para o SensorEventListener
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Registre o SensorEventListener para o sensor de luz ambiente
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Remova o registro do SensorEventListener para economizar recursos
        sensorManager.unregisterListener(this);
    }

}
package com.example.iot1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.CharacterData;

import java.util.ArrayList;

public class SensorAccelrationActivity extends Activity implements SensorEventListener {
    private static final String APP_NAME = "chock";
    SensorManager sensorManager;
    // L'accéléromètre
    Sensor accelerometer;
    float x, y, z;
    private static final float SHAKE_THRESHOLD = 0.00335f; // m/S**2
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;
    TextView textView1;
    private boolean isShaked = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Instancier le gestionnaire des capteurs, le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ArrayList<Sensor> liste = (ArrayList<Sensor>) sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);

// Instancier l'accéléromètre
        accelerometer =
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
textView1=findViewById(R.id.textView1);
    }

    public void onSensorChanged(SensorEvent event) {


        long curTime = System.currentTimeMillis();
// Récupérer les valeurs du capteur float x, y, z;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {


                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                textView1.setText("x: "+event.values[0]+"\ny: "+event.values[1]+"\nz: "+event.values[2]);
                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                Log.d(APP_NAME, "Acceleration is " + acceleration + "m/s^2");

                if (acceleration > SHAKE_THRESHOLD) {
                    isShaked = true;

                    mLastShakeTime = curTime;
                    Log.d(APP_NAME, "Shake, Rattle, and Roll");
                }
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
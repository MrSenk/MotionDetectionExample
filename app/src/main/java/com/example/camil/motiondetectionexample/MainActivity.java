package com.example.camil.motiondetectionexample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    SensorManager sm;
    TextView tv;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView1);
        rl = (RelativeLayout) findViewById(R.id.activity_main);
        // Obtener servicio de sensor
        sm = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        // Indicar el sensor que vamos a usar
        // Declarar el delay para el sensor
        // Registrar cambios para ser usados por el objeto sensor

        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            // Obtener valores x, y, z
            float value[] = event.values;
            float x = value[0];
            float y = value[1];
            float z = value[2];
            // Usar la siguiente formula
            // Determinar la gravedad dependiendo del lugar donde estes
            float asr = (x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*
            SensorManager.GRAVITY_EARTH);
            // Si el celular se mueve en cualquier direccion
            // entonces la siguiente condicion se cumple
            if(asr>=2){
                // Generar un número aleatorio y mostrarlo en pantalla
                Random r = new Random();
                int i = r.nextInt(10);
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                rl.setBackgroundColor(color);
                tv.setText(""+i);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

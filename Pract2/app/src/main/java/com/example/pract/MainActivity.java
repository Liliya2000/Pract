package com.example.pract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    private Sensor accelerometer, nGyro;

    TextView xValue, yValue, zValue, xGyroValue, yGyroValue, zGyroValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        xGyroValue = (TextView) findViewById(R.id.xGyroValue);
        yGyroValue = (TextView) findViewById(R.id.yGyroValue);
        zGyroValue = (TextView) findViewById(R.id.zGyroValue);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer != null){
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");
        } else {
            xValue.setText("Accelerometre desteklenmedi");
            yValue.setText("Accelerometre desteklenmedi");
            zValue.setText("Accelerometre desteklenmedi");
        }

        nGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(nGyro != null){
            sensorManager.registerListener(MainActivity.this, nGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyro listener");
        } else {
            xGyroValue.setText("Gyroscope desteklenmedi");
            yGyroValue.setText("Gyroscope desteklenmedi");
            zGyroValue.setText("Gyroscope desteklenmedi");
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i){

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG, "onSensorChanged: X: "+ sensorEvent.values[0]+ " Y: "+sensorEvent.values[1]+ " Z: "+sensorEvent.values[2]);

            xValue.setText("X Accelerator: "+sensorEvent.values[0]);
            yValue.setText("Y Accelerator: "+sensorEvent.values[1]);
            zValue.setText("Z Accelerator: "+sensorEvent.values[2]);
        } else if (sensor.getType() ==Sensor.TYPE_GYROSCOPE){
            xGyroValue.setText("X Gyroscope: "+sensorEvent.values[0]);
            yGyroValue.setText("Y Gyroscope: "+sensorEvent.values[0]);
            zGyroValue.setText("Z Gyroscope: "+sensorEvent.values[0]);
        }

    }
}

package com.example.smartstep;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccelerometerData extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "AccelerometerData";

    private SensorManager sensorManager;
    private Sensor accelerometer,mGyro, mPressure;
    private SensorEvent event;
    private Sensor sensor;
    TextView xValue, yValue, zValue, xGyroValue, yGyroValue, zGyroValue, pressure;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_data);


        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);
        pressure = (TextView) findViewById(R.id.pressure);

        xGyroValue = (TextView) findViewById(R.id.xGyroValue);
        yGyroValue = (TextView) findViewById(R.id.yGyroValue);
        zGyroValue = (TextView) findViewById(R.id.zGyroValue);


        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        if (accelerometer != null) {
            sensorManager.registerListener(AccelerometerData.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer Listener");

        } else {
            xValue.setText("Accelerometer not supported");
            yValue.setText("Accelerometer not supported");
            zValue.setText("Accelerometer not supported");
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        if (mGyro != null) {
            sensorManager.registerListener(AccelerometerData.this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyro Listener");

        } else {
            xGyroValue.setText("Gyro not supported");
            yGyroValue.setText("Gyro not supported");
            zGyroValue.setText("Gyro not supported");
        }
        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (mPressure != null) {
            sensorManager.registerListener(AccelerometerData.this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered pressure Listener");
        } else {
            pressure.setText("Pressure not supported");
        }
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityLocation();

            }
        });
    }

    public void openActivityLocation() {
        Intent intent = new Intent(this, Location.class);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: X: " + event.values[0] + "Y: " + event.values[1] + "Z: " + event.values[2]);

            xValue.setText("xValue: " + event.values[0]);
            yValue.setText("yValue: " + event.values[1]);
            zValue.setText("zValue: " + event.values[2]);
        }else if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
            xGyroValue.setText("xValue: " + event.values[0]);
            yGyroValue.setText("yValue: " + event.values[1]);
            zGyroValue.setText("zValue: " + event.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            pressure.setText("pressure: " + event.values[0]);
        }


    }

}

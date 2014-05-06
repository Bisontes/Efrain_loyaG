package com.example.tomarfoto.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    int TAKE_PHOTO_CODE = 0;
    public static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Aqui creamos una carpeta llamada picFolfer donde se van a guardar las fotos tomadas en la aplicacion
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();
        Button capture = (Button) findViewById(R.id.btnCapture);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //Aqui, se van a ir contando las fotos por cada incremento, la foto tomada por la camara se va ir guardando como 1.jpg,2.jpg, etc.
                count++;
                String file = dir+count+".jpg";
                File newfile = new File(file);
                try {
                    newfile.createNewFile();
                } catch (IOException e) {}

                Uri outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        //Aqui se agregan items que haran la accion de la barra si esta esta presente
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //cambia la barra de accion de los elementos al hacer click aqui.la barra de accion actomatiamente
        //intercambia click en el home/up boton, tanto como tu especifiques una actividad padre en el androidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

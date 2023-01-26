package fr.enseeiht.simeghri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = "Browser Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"OnCreate() called");

        TextView searchField = findViewById(R.id.editTextTextPersonName);
        Button gotBtn = findViewById(R.id.button);

        gotBtn.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            String url = searchField.getText().toString();
            if ( url != null && checkUrlValidity(url) ) {
                i.setData(Uri.parse(url));
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), "Invalid URL !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkUrlValidity(String url) {
        return Pattern
                .matches("^(https?)://(www\\.)?[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", url);
    }
}
package fr.enseeiht.simeghri.player;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class PlayerActivity extends AppCompatActivity {

    private final int REQ_CODE = 1;

    private TextView selectedURI;
    private Button playBtn;
    private Uri vidUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == RESULT_OK && data != null) {
            vidUri = data.getData();
            selectedURI.setText(vidUri.toString());
            playBtn.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button uploadBtn = findViewById(R.id.uploadBtn);
        selectedURI = findViewById(R.id.textView);
        playBtn = findViewById(R.id.playBtn);
        
        uploadBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);


            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);

            /*ActivityResultLauncher<String> mGetContent = registerForActivityResult(null,
                    (ActivityResultCallback<Uri>) uri ->
                            Toast.makeText(PlayerActivity.this, uri.getPath(), Toast.LENGTH_SHORT).show());
            mGetContent.launch(intent);*/

            startActivityForResult(intent, REQ_CODE);
        });

        playBtn.setOnClickListener(view -> {
            Intent playIntent = new Intent(Intent.ACTION_VIEW);
            Toast.makeText(this, vidUri.getPath(), Toast.LENGTH_SHORT).show();
            playIntent.setDataAndType(vidUri, "video/*");
            startActivity(playIntent);
        });
    }
}
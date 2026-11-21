package com.example.egzamin3;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    public TextView size2;
    public TextView thick2;
    public TextView count2;
    public TextView Total;
    public Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            int mult = 0;
            int size = 0;
            int add = 0;

            size2 = findViewById(R.id.size2);
            thick2 = findViewById(R.id.thick2);
            count2 = findViewById(R.id.count2);
            Total = findViewById(R.id.Total);
            Back = findViewById(R.id.Back);

            size2.setText(getIntent().getStringExtra("Size"));
            Log.d("asd",size2.getText().toString());
            if(size2.getText().equals("Mała")){
                size = 25;
            } else if (size2.getText().equals("Średnia")) {
                size = 35;
            } else if (size2.getText().equals("Duża")) {
                size = 45;
            }
            if(getIntent().getBooleanExtra("Thick", false) == true){
                thick2.setText("Grube");
                add = 5;
            }else{
                thick2.setText("Cienkie");
                add = 0;
            }

            count2.setText(getIntent().getStringExtra("Count"));
            mult = Integer.parseInt(getIntent().getStringExtra("Count"));

            Total.setText("Razem: " + ((size + add) * mult) + ",00 PLN");
            Back.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    finish();
                }
            });
            return insets;
        });
    }
}
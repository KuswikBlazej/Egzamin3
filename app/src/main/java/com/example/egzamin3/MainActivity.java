package com.example.egzamin3;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public Button Next;
    public CheckBox Thick;
    public EditText Count;
    public Spinner dropdown;
    public RadioButton s1, s2, s3;
    public String selectedSize = "Mała";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            selectedSize = savedInstanceState.getString("SelectedSize", "Mała");
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                dropdown = findViewById(R.id.spinner1);

                String[] items = new String[]{"Mała 25zł", "Średnia 35zł", "Duża 45zł"};

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
                dropdown.setAdapter(adapter);
                if (selectedSize.equals("Mała")) {
                    dropdown.setSelection(0);
                } else if (selectedSize.equals("Średnia")) {
                    dropdown.setSelection(1);
                } else if (selectedSize.equals("Duża")) {
                    dropdown.setSelection(2);
                }
            }
            else {
                s1 = (RadioButton) findViewById(R.id.s1);
                s2 = (RadioButton) findViewById(R.id.s2);
                s3 = (RadioButton) findViewById(R.id.s3);
                if (selectedSize.equals("Mała")) {
                    s1.setChecked(true);
                } else if (selectedSize.equals("Średnia")) {
                    s2.setChecked(true);
                } else if (selectedSize.equals("Duża")) {
                    s3.setChecked(true);
                }
            }
            Next = (Button) findViewById(R.id.Next);

            Thick = (CheckBox) findViewById(R.id.thick);

            Count = (EditText) findViewById(R.id.count);


            Next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Integer.parseInt(String.valueOf(Count.getText())) > 0){
                        String size = "";
                        String count = Count.getText().toString();
                        boolean thick = Thick.isChecked();
                        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                            int x = dropdown.getSelectedItemPosition();
                            if(x == 0){
                                size = "Mała";
                            } else if (x == 1) {
                                size = "Średnia";
                            } else {
                                size = "Duża";
                            }
                        }else{
                            if(s1.isChecked()){
                                size = "Mała";
                            } else if (s2.isChecked()) {
                                size = "Średnia";
                            } else if (s3.isChecked()) {
                                size = "Duża";
                            }
                        }

                        Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("Count", count);
                        intent.putExtra("Thick", thick);
                        intent.putExtra("Size", size);
                        startActivity(intent);
                    }
                }
            });
            return insets;
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int position = dropdown.getSelectedItemPosition();

            if (position == 0) selectedSize = "Mała";
            else if (position == 1) selectedSize = "Średnia";
            else if (position == 2) selectedSize = "Duża";

        } else {
            if (s1.isChecked()) selectedSize = "Mała";
            else if (s2.isChecked()) selectedSize = "Średnia";
            else if (s3.isChecked()) selectedSize = "Duża";
        }

        outState.putString("SelectedSize", selectedSize);
    }
}
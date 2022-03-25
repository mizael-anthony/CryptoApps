package com.mizael.cryptoapps;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout home = findViewById(R.id.linearLayoutHome);

        String[] buttons = {"CESAR", "KAA", "TRANSPOSITION", "VIGENERE", "XOR"};

        for(String button: buttons){
            Button optionButton = new Button(this);
            optionButton.setText(button);
            home.addView(optionButton);
            // TODO: Mila esorina ilay switch bedebe(manao classe vaovao na méthode hafa)
            optionButton.setOnClickListener(v -> {
                String chosenButton = String.valueOf(optionButton.getText());

                switch (chosenButton){
                    case "CESAR":
                        CryptPopup cesarPopup = new CryptPopup(MainActivity.this, chosenButton);
                        cesarPopup.setTitle("Mampidira isa fanalahidy");
                        cesarPopup.build();
                        break;

                    case "KAA":
                        CryptPopup kaaPopup = new CryptPopup(MainActivity.this, chosenButton);
                        kaaPopup.getKeyEdit().setVisibility(View.GONE);
                        kaaPopup.build();
                        break;

                    case "TRANSPOSITION" :
                        CryptPopup transpositionPopup = new CryptPopup(MainActivity.this, chosenButton);
                        transpositionPopup.setTitle("Mampidira isa fandrafetana");
                        transpositionPopup.build();
                        break;

                    case "VIGENERE":
                        CryptPopup vigenerePopup = new CryptPopup(MainActivity.this, chosenButton);
                        vigenerePopup.setTitle("Mampidira soratra fanalahidy");
                        vigenerePopup.getKeyEdit().setInputType(InputType.TYPE_CLASS_TEXT);
                        vigenerePopup.build();
                        break;

                    case "XOR":
                        CryptPopup xorPopup = new CryptPopup(MainActivity.this, chosenButton);
                        xorPopup.setTitle("Mampidira teny fanalahidy");
                        xorPopup.getKeyEdit().setInputType(InputType.TYPE_CLASS_TEXT);
                        xorPopup.build();
                        break;

                    default:
                        Toast.makeText(MainActivity.this, "Mbola tsy vita", Toast.LENGTH_SHORT).show();
                        break;
                }
            });

        }



    }
}
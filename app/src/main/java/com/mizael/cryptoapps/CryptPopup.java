package com.mizael.cryptoapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mizael.tools.Startable;

public class CryptPopup extends Dialog implements Startable {

    private final TextView titleView;
    private final EditText keyEdit;
    private final Button encryption, decryption;
    private String title;
    private static String key, choice;
    public static boolean toCrypt;


    public CryptPopup(Activity activity, String choice) {
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.crypt_popup);

        this.titleView = findViewById(R.id.textViewTitle);
        this.keyEdit = findViewById(R.id.editTextKey);
        this.encryption = findViewById(R.id.buttonEncryption);
        this.decryption = findViewById(R.id.buttonDecryption);

        this.title = "Safidio izay tinao atao";

        CryptPopup.key = "";
        CryptPopup.choice = choice;

        // TODO: Mila esorina ilay switch bedebe(manao classe vaovao na méthode hafa)
        this.encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CryptPopup.toCrypt = true;

                switch (CryptPopup.getChoice()) {

                    case "CESAR":
                        CryptPopup.setKey(String.valueOf(keyEdit.getText()));
                        if (CryptPopup.getKey().isEmpty())
                            Toast.makeText(activity, "Hampidiro ilay isa hanidina !", Toast.LENGTH_SHORT).show();
                        else
                            startOtherActivity(activity, CryptActivity.class);
                        break;

                    case "TRANSPOSITION":
                        CryptPopup.setKey(String.valueOf(keyEdit.getText()));
                        if (CryptPopup.getKey().isEmpty())
                            Toast.makeText(activity, "Hampidiro ilay isa handrafetana !", Toast.LENGTH_SHORT).show();
                        else
                            startOtherActivity(activity, CryptActivity.class);
                        break;

                    case "VIGENERE":
                        CryptPopup.setKey(String.valueOf(keyEdit.getText()));
                        if (CryptPopup.getKey().isEmpty())
                            Toast.makeText(activity, "Hampidiro ilay soratra hanidina !", Toast.LENGTH_SHORT).show();
                        else
                            startOtherActivity(activity, CryptActivity.class);
                        break;

                    case "XOR":
                        CryptPopup.setKey(String.valueOf(keyEdit.getText()));
                        if (CryptPopup.getKey().isEmpty())
                            Toast.makeText(activity, "Hampidiro ilay teny hanidina !", Toast.LENGTH_SHORT).show();
                        else
                            startOtherActivity(activity, CryptActivity.class);
                        break;

                    default:
                        startOtherActivity(activity, CryptActivity.class);
                        break;


                }


            }
        });


        this.decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CryptPopup.toCrypt = false;

                switch (CryptPopup.getChoice()) {

                    case "CESAR":
                        CryptPopup.setKey(String.valueOf(keyEdit.getText()));
                        if (CryptPopup.getKey().isEmpty())
                            Toast.makeText(activity, "Hampidiro ilay isa hamohana !", Toast.LENGTH_SHORT).show();
                        else
                            startOtherActivity(activity, CryptActivity.class);
                        break;

                    case "TRANSPOSITION":
                        CryptPopup.setKey(String.valueOf(keyEdit.getText()));
                        if (CryptPopup.getKey().isEmpty())
                            Toast.makeText(activity, "Hampidiro ilay isa handrodanana !", Toast.LENGTH_SHORT).show();
                        else
                            startOtherActivity(activity, CryptActivity.class);
                        break;

                    case "VIGENERE":
                        CryptPopup.setKey(String.valueOf(keyEdit.getText()));
                        if (CryptPopup.getKey().isEmpty())
                            Toast.makeText(activity, "Hampidiro ilay soratra hamohana !", Toast.LENGTH_SHORT).show();
                        else
                            startOtherActivity(activity, CryptActivity.class);
                        break;

                    case "XOR":
                        CryptPopup.setKey(String.valueOf(keyEdit.getText()));
                        if (CryptPopup.getKey().isEmpty())
                            Toast.makeText(activity, "Hampidiro ilay teny hamohana !", Toast.LENGTH_SHORT).show();
                        else
                            startOtherActivity(activity, CryptActivity.class);
                        break;

                    default:
                        startOtherActivity(activity, CryptActivity.class);
                        break;


                }


            }
        });


    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static String getChoice() {
        return CryptPopup.choice;
    }
    public static void setChoice(String choice) { CryptPopup.choice = choice; }

    public static String getKey() {
        return CryptPopup.key;
    }
    public static void setKey(String key) {
        CryptPopup.key = key;
    }

    public EditText getKeyEdit() {
        return this.keyEdit;
    }

    public Button getEncryption() { return this.encryption; }
    public Button getDecryption() { return this.decryption; }


    public void startOtherActivity(Activity activity, Class<?> cls) {
        Intent otherActivity = new Intent(activity.getApplicationContext(), cls);
        activity.startActivityForResult(otherActivity, 0);
        this.dismiss();
    }

    public void build() {
        show();
        this.titleView.setText(this.title);
    }


}

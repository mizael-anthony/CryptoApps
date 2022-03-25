package com.mizael.cryptoapps;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mizael.tools.Conversion;

public class CryptActivity extends AppCompatActivity {

    private EditText plainText;
    private TextView cipherText;
    private String key, choice;
    private boolean toCrypt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypt);

        TextView technic = findViewById(R.id.textViewTechnic);
        TextView secretKey = findViewById(R.id.textViewSecretKey);
        this.plainText = findViewById(R.id.editTextPlainText);
        this.cipherText = findViewById(R.id.textViewCipherText);

        this.key = CryptPopup.getKey();
        this.toCrypt = CryptPopup.toCrypt;
        this.choice = CryptPopup.getChoice();

        // Variable temporaire hitehirizana String
        String indication;


        // Manova loko text anle cipher arakaraka ilay bouton voatsindry
        this.cipherText.setTextColor(getResources().getColor((this.toCrypt) ? R.color.red:R.color.green));

        indication = "FIKA NOSAFIDIANA: " + this.choice;
        technic.setText(indication);
        technic.setTextColor(getResources().getColor(R.color.white));
        technic.setBackgroundColor(getResources().getColor(R.color.violet));

        indication = (this.key.isEmpty()) ? "": "FANALAHIDY: " + this.key;
        secretKey.setText(indication);
        secretKey.setTextColor(getResources().getColor(R.color.white));
        // Manova loko background arakaraka ilay clé nampidirina
        secretKey.setBackgroundColor(getResources().getColor((this.key.isEmpty()) ? R.color.white:R.color.blue));


        //TODO: Mila esorina ilay switch bedebe(manao classe vaovao na méthode hafa)
        this.plainText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String message = plainText.getText().toString();

                switch (choice){
                    case "CESAR":
                        cipherText.setText(cesar(message, Integer.parseInt(key), toCrypt));
                        break;

                    case "KAA":
                        cipherText.setText(kaa(message, toCrypt));
                        break;

                    case "TRANSPOSITION":
                        cipherText.setText(transposition(message, Integer.parseInt(key), toCrypt));
                        break;

                    case "VIGENERE":
                        cipherText.setText(vigenere(message, key, toCrypt));
                        break;

                    case "XOR":
                        String tmp = xor(message, key, toCrypt);
                        Toast.makeText(CryptActivity.this, tmp, Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.crypt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuCryptCopy) {
            // Mamorona popup isafidianana ilay teny tiana copiena
            CryptPopup copyPopup = new CryptPopup(this, "");
            copyPopup.getKeyEdit().setVisibility(View.GONE);
            copyPopup.getEncryption().setText(R.string.text_up); // plainText
            copyPopup.getDecryption().setText(R.string.text_down); // cipherText
            copyPopup.build();

            copyPopup.getEncryption().setOnClickListener(v -> {
                this.copyTextFrom(this.plainText.getText().toString());
                copyPopup.dismiss();

            });
            copyPopup.getDecryption().setOnClickListener(v -> {
                this.copyTextFrom(this.cipherText.getText().toString());
                copyPopup.dismiss();
            });

            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void copyTextFrom(String text){
        ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("", text);
        clipboard.setPrimaryClip(data);
        // Maka ilay texte anaty clipboard
        //ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
    }

    private char moveLetter(char letter, int translation, boolean toCrypt){
        char movedLetter;

        if(toCrypt)
            movedLetter = (char)(((((int)letter - 32) + translation) % 223) + 32);
        else
            movedLetter = (char)(((223 + (((int)letter - 32)) - translation) % 223) + 32);

        return movedLetter;
    }

    //TODO: Avadika class tools
    private char[][] createMatrix(String message, int line, int column){

        char[][] matrix = new char[line][column];
        int index = 0;
        for(int i = 0; i < line; i++){
            for(int j = 0; j < column; j++){
                matrix[i][j] = message.charAt(index);
                index++;
            }
        }

        return matrix;
    }

    public String transposition(String message, int key, boolean toCrypt){
        String cryptMessage = "";

        int column, line;

        // Atao izay maha modulo an'ilay nombre de caractère sy colonne
        while(message.length() % key != 0)
            message += ' ';

        if(toCrypt){
            line = (message.length() / key);
            column = key;
        }

        else{
            line = key;
            column = (message.length() / key);
        }

        char [][] matrix = createMatrix(message, line, column);

        for(int j = 0; j < column; j++)
            for(int i = 0; i < line; i++)
                cryptMessage += matrix[i][j];

        return cryptMessage;

    }

    public String cesar(String message, int key, boolean toCrypt){
        String cryptMessage = "";

        for(int i = 0; i < message.length(); i++)
            cryptMessage += moveLetter(message.charAt(i), key, toCrypt);

        return cryptMessage;

    }

    public String kaa(String message, boolean toCrypt){
        String cryptMessage = "";

        String[] tab = message.split(" ");

        for(String word: tab)
            cryptMessage += cesar(word, word.length(), toCrypt) + " ";

        return cryptMessage;
    }

    public String vigenere(String message, String key, boolean toCrypt){
        String cryptMessage = "";

        while(key.length() < message.length())
            key += key;

        key = key.substring(0, message.length());

        for (int i = 0; i < message.length(); i++)
            cryptMessage += moveLetter(message.charAt(i), key.charAt(i), toCrypt);

        return cryptMessage;

    }

    public String xor(String message, String key, boolean toCrypt){
        String cryptMessage = "";

        if(message.equals("") || (message.length() != key.length())){
            cryptMessage = "Tsy mety satria tsy mitovy ny halavan'le fanalahidy sy le nosoratana";
        }

        else{
            int[] tabIntMessage = Conversion.toNumber(message);
            int[] tabIntKey = Conversion.toNumber(key);

            String[] tabStringMessage = new String[tabIntMessage.length];
            String[] tabStringKey = new String[tabIntKey.length];
            // Eto mitovy ny halavany dia mety doly na le tabIntMessage na le tabIntKey
            for(int k = 0; k < tabIntMessage.length; k++){
                tabStringMessage[k] = Conversion.toBinary(tabIntMessage[k]);
                tabStringKey[k] = Conversion.toBinary(tabIntKey[k]);
            }
            //cryptMessage = tabStringMessage[0] + " " + tabStringKey[0];

            String[] tabCryptedMessage = new String[tabStringMessage.length];
            String tmpCryptedMessage = "";
            for(int i = 0; i < tabStringMessage.length; i++){
                // Alaina tsirairay zay chaine anatinle tableau
                String messageLetter = tabStringMessage[i];
                String keyLetter = tabStringKey[i];
                for(int j = 0; j < messageLetter.length(); j++){
                    if(messageLetter.charAt(j) == keyLetter.charAt(j)){
                        tmpCryptedMessage += "0";
                    }
                    else{
                        tmpCryptedMessage += "1";
                    }
                }
                tabCryptedMessage[i] = tmpCryptedMessage;

            }
            tmpCryptedMessage = "";
            // Avadika nombre aveo ASCII indray ny caractère tsirairay anaty tableau
             for(int k = 0; k < tabCryptedMessage.length; k++){
                 tmpCryptedMessage += (char)(Conversion.toDecimal(tabCryptedMessage[k]));
                 tabCryptedMessage[k] = tmpCryptedMessage;
             }

             cryptMessage = tabCryptedMessage[0];

             /*
             * akisaka anaty caractere imprimable le message et key izay vo atao xor
             * */

            

            






        }

        return cryptMessage;
    }


}
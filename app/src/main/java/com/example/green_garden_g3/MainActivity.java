package com.example.green_garden_g3;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.green_garden_g3.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button menu;
    TextView registro;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = findViewById(R.id.btn_inicio);
        registro = findViewById(R.id.tv_registro);
        email = findViewById(R.id.et_usuario);
        password = findViewById(R.id.et_contrasena);


        File fileReader = new File(getFilesDir(), "user.txt");
        ArrayList<User> usersList = listUsers(fileReader);

        Intent resgistra = new Intent(getApplicationContext(), RegistrarUsuario.class);
        Intent ingresa = new Intent(getApplicationContext(), Menu.class);

        menu.setOnClickListener(view -> {
            boolean state = false;
            if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Los campos deben diligenciarse", Toast.LENGTH_LONG).show();
            }else {
                for (User i:usersList){
                    if (i.getEmail().equals(email.getText().toString())){
                        state = true;
                        if (i.getPassword().equals(password.getText().toString())){
                            ingresa.putExtra("idUser", i.getIdUser());
                            Toast.makeText(getApplicationContext(), "Haz iniciado sesión", Toast.LENGTH_LONG).show();
                            try {
                                sleep(500);
                            }catch (InterruptedException e){
                                throw new RuntimeException();
                            }
                            startActivity(ingresa);
                            break;
                        }else {
                            Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                if (!state){
                    Toast.makeText(getApplicationContext(), "No se encuentra el usuario", Toast.LENGTH_LONG).show();
                }
            }
        });
        registro.setOnClickListener(view -> startActivity(resgistra));
    }

    public ArrayList<User> listUsers (File fileUser){
        ArrayList<User> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileUser);
            BufferedReader reader = new BufferedReader(fileReader);
            String user;

            while ((user=reader.readLine())!=null){
                String[] userArray = user.split(",");
                String id = userArray[0];
                String name = userArray[1];
                String lastName = userArray[2];
                String email = userArray[3];
                String password = userArray[4];

                User userObj = new User(id, name, lastName, email, password);
                list.add(userObj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
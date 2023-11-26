package com.example.green_garden_g3;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.green_garden_g3.models.User;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.Objects;


public class RegistrarUsuario extends AppCompatActivity {

    Button register;

    TextInputLayout name, lastName, email, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        TextView iniciar = findViewById(R.id.tv_iniciar);
        register = findViewById(R.id.btn_registrarse);
        name = findViewById(R.id.et_nombre);
        lastName = findViewById(R.id.et_apellidos);
        email = findViewById(R.id.et_correo);
        password = findViewById(R.id.et_password);
        password2 = findViewById(R.id.et_password1);
        Intent sesion = new Intent(getApplicationContext(), Menu.class);

        iniciar.setOnClickListener(view -> finish());

        register.setOnClickListener(v -> {
            if (validateUser()) {
                User user = createUser();
                storageUser(user);
                Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                try {
                    sleep(500);
                    startActivity(sesion);
                    finish();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Asegurese que la información este correcta", Toast.LENGTH_LONG).show();
            }
        });


    }

    public boolean validateUser() {
        boolean state = true;

        if (Objects.requireNonNull(name.getEditText()).getText().toString().isEmpty()) {
            name.setError("Ingresa tu nombre");
            state = false;
        } else {
            name.setErrorEnabled(false);
        }
        if (Objects.requireNonNull(lastName.getEditText()).getText().toString().isEmpty()) {
            lastName.setError("Ingresa tus apellidos");
            state = false;
        } else {
            lastName.setEnabled(false);
        }
        if (Objects.requireNonNull(email.getEditText()).getText().toString().isEmpty()) {
            email.setError("Ingrese un correo");
            state = false;
        } else {
            email.setErrorEnabled(false);
        }
        if (Objects.requireNonNull(password.getEditText()).getText().toString().isEmpty()) {
            password.setError("Ingrese contraseña");
            state = false;
        } else {
            password.setErrorEnabled(false);
        }
        if (Objects.requireNonNull(password2.getEditText()).getText().toString().isEmpty()) {
            password.setError("Ingrese contraseña");
            state = false;
        }
        if (!password.getEditText().getText().toString().equals(password2.getEditText().getText().toString())) {
            password.setError("Las contraseñas deben coincidir");
            password2.setError("Las contraseñas deben coincidir");
            state = false;
        } else {
            password.setErrorEnabled(false);
            password2.setErrorEnabled(false);
        }
        return state;
    }

    public User createUser() {
        String name_user = Objects.requireNonNull(name.getEditText()).getText().toString();
        String id = idGenerator(name_user);
        String lastName_user = Objects.requireNonNull(lastName.getEditText()).getText().toString();
        String email_user = Objects.requireNonNull(email.getEditText()).getText().toString();
        String password_user = Objects.requireNonNull(password.getEditText()).getText().toString();
        return new User(id, name_user, lastName_user, email_user, password_user);
    }

    public String idGenerator(String name) {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int random1 = (int) (Math.random() * name.length());
            int random2 = (int) (Math.random() * 10000);
            id.append(name.charAt(random1));
            id.append(random2);
        }
        return id.toString();
    }

    public void storageUser(User user) {
        File fileUser = new File(getFilesDir(), "user.txt");
        try {
            FileWriter writer = new FileWriter(fileUser, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(user.getIdUser() + "," +
                    user.getName() + "," +
                    user.getLastName() + "," +
                    user.getEmail() + "," +
                    user.getPassword()
            );
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
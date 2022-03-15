package team.no.tech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import team.no.tech.models.Auth;

public class MainActivity extends AppCompatActivity {

    private Auth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = new Auth();

        TextView txtRegister = findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(view -> onClickRegister());

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> onClickLogin());
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.isLoggedIn()) {
            onClickLogin();
        }
    }

    private void onClickLogin() {
        String email = ((EditText)findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length() > 0) {
            mAuth.logIn(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    ShowToast("Login successfully!");
                    Intent intent = new Intent(getApplicationContext(), InforActivity.class);
                    startActivity(intent);
                    this.finish();
                } else {
                    ShowToast("Login failed!");
                }
            });
        }
    }

    private void onClickRegister() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    private void ShowToast(String message) {
        Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_LONG)
                .show();
    }
}
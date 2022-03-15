package team.no.tech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import team.no.tech.models.Account;
import team.no.tech.models.Auth;
import team.no.tech.models.DAOAccount;

public class RegisterActivity extends AppCompatActivity {
    private Auth mAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextFullName;
    private EditText editTextAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       mAuth = new Auth();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextAge = findViewById(R.id.editTextAge);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view -> registerAccount());
    }

    private void registerAccount() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        Integer age = Integer.parseInt(editTextAge.getText().toString());
        System.out.println(age);

        mAuth.registerAccountByEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Account account = new Account(email, fullName, age);
                    DAOAccount daoAccount = new DAOAccount();
                    daoAccount.addAccount(account, mAuth.getUid())
                        .addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                ShowToast("Register successfully!.");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                this.finish();
                            } else {
                                ShowToast("Register failed! Please try again.");
                            }
                        });
                } else {
                    ShowToast("Register failed! Please try again.");
                }
        });
    }

    private void ShowToast(String message) {
        Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_LONG)
                .show();
    }
}

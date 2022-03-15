package team.no.tech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import team.no.tech.models.Account;
import team.no.tech.models.Auth;
import team.no.tech.models.DAOAccount;

public class InforActivity extends AppCompatActivity {
    Auth mAuth;
    DAOAccount daoAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        TextView txtAccountEmail = findViewById(R.id.txtAccountEmail);
        TextView txtFullName = findViewById(R.id.txtFullName);
        TextView txtAge = findViewById(R.id.txtAge);

        mAuth = new Auth();
        daoAccount = new DAOAccount();

        daoAccount.getAccountInfor(mAuth.getUid())
                .addOnSuccessListener(dataSnapshot -> {
                    Account account = dataSnapshot.getValue(Account.class);
                    assert account != null;
                    txtAccountEmail.setText(account.getEmail());
                    txtFullName.setText(account.getFullName());
                    txtAge.setText(String.valueOf(account.getAge()));
                });

        Button btnLogout = findViewById(R.id.btnLogin);
        btnLogout.setOnClickListener(view -> onClickLogout());
    }

    private void onClickLogout() {
        mAuth.logOut();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}

package team.no.tech.models;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOAccount {
    private final DatabaseReference databaseReference;
    private static final String TABLE_NAME = "Accounts";
    private static final String DATABASE_URL = "https://authwithfirebase-48fc9-default-rtdb.asia-southeast1.firebasedatabase.app";

    public DAOAccount() {
        databaseReference = FirebaseDatabase.getInstance(DATABASE_URL).getReference(TABLE_NAME);
    }

    public Task<Void> addAccount(Account account) {
        return databaseReference.push().setValue(account);
    }

    public Task<Void> addAccount(Account account, String uid) {
        return databaseReference.child(uid).setValue(account);
    }

    public Task<DataSnapshot> getAccountInfor(String uid) {
        return databaseReference.child(uid).get();
    }
}

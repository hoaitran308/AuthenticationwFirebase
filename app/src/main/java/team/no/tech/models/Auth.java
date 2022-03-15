package team.no.tech.models;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Auth {
    private final FirebaseAuth firebaseAuth;

    public Auth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public String getUid() {
        return firebaseAuth.getUid();
    }

    public boolean isLoggedIn() {
        return firebaseAuth.getCurrentUser() != null;
    }

    public Task<AuthResult> registerAccountByEmailAndPassword(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }

    public void logOut() {
        firebaseAuth.signOut();
    }

    public Task<AuthResult> logIn(String email, String password) {
        return firebaseAuth.signInWithEmailAndPassword(email, password);
    }
}

package enda.go_warmindo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private String email;
    private String password;
    private static final String TAG = "Main Menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();

        Log.d(TAG, "onCreate(Bundle) Called");

        firebaseAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth fireBaseAuth){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null){
                    Intent intent =new Intent(SigninActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

    public void regisClick(View view){
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

    public void loginClick(View view) {
        EditText emailEdit = (EditText) findViewById(R.id.emailLogin);
        email = emailEdit.getText().toString();
        EditText passEdit = (EditText) findViewById(R.id.passwordLogin);
        password = passEdit.getText().toString();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(SigninActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SigninActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else{
            Toast.makeText(SigninActivity.this, "Please input your email and password", Toast.LENGTH_SHORT).show();
        }
    }
}

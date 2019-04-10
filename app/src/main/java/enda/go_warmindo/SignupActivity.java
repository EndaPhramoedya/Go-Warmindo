package enda.go_warmindo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import android.support.annotation.NonNull;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private static final String TAG = "Main Menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Log.d(TAG, "onCreate(Bundle) Called");

        mAuth = FirebaseAuth.getInstance();
    }

    public void loginClick(View view){
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
        finish();
    }

    public void regClick(View view){
        EditText emailEdit = (EditText)findViewById(R.id.emailReg);
        email = emailEdit.getText().toString();
        EditText passEdit = (EditText)findViewById(R.id.passwordReg);
        password = passEdit.getText().toString();
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignupActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        } else {
            Toast.makeText(SignupActivity.this, "Please input your email and password", Toast.LENGTH_SHORT).show();
        }
    }

}

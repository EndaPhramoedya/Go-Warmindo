package enda.go_warmindo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class OrderActivity extends AppCompatActivity {
    private TextView value;
    private FirebaseAuth mAuth;
    private static final String TAG = "Order Activity";

    int num1, num2, num3, num4 = 0;
    int prc1, prc2, prc3, prc4;
    String p1, p2, p3, p4, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Log.d(TAG, "onCreate(Bundle) Called");

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        address = getIntent().getExtras().getString("address");
        value = (TextView)findViewById(R.id.addressTxt);
        value.setText("" + address);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = mRef.child("Users/" + user.getUid());
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users currentUser = dataSnapshot.getValue(Users.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void backClick(View view){
        Intent intent = new Intent(OrderActivity.this, MainMenu.class);
        startActivity(intent);
        finish();
    }

    public void plusClick(View view){
        if (view.getId() == R.id.plusBtn1){
            TextView qty1 = (TextView)findViewById(R.id.cart_product_quantity_tv);
            TextView rp1 = (TextView)findViewById(R.id.price1);
            num1 += 1;
            prc1 = 5000 * num1;
            qty1.setText("" + num1);
            rp1.setText("Rp. " + prc1);
            if (num1 > 0){
                p1 = "Mie Goreng";
            }else if(num1 <= 0){
                p1 = "";
            }
        }else if (view.getId() == R.id.plusBtn2){
            TextView qty2 = (TextView)findViewById(R.id.cart_product_quantity_tv2);
            TextView rp2 = (TextView)findViewById(R.id.price2);
            num2 += 1;
            prc2 = 6000 * num2;
            qty2.setText("" + num2);
            rp2.setText("Rp. " + prc2);
            if (num2 > 0){
                p2 = "Mie Kuah Kari Ayam";
            }else if(num2 <= 0){
                p2 = "";
            }
        }else if (view.getId() == R.id.plusBtn3) {
            TextView qty3 = (TextView) findViewById(R.id.cart_product_quantity_tv3);
            TextView rp3 = (TextView)findViewById(R.id.price3);
            num3 += 1;
            prc3 = 6000 * num3;
            qty3.setText("" + num3);
            rp3.setText("Rp. " + prc3);
            if (num3 > 0) {
                p3 = "Mie Kuah Soto";
            }else if(num3 <= 0){
                p3 = "";
            }
        }else if (view.getId() == R.id.plusBtn4) {
            TextView qty4 = (TextView) findViewById(R.id.cart_product_quantity_tv4);
            TextView rp4 = (TextView)findViewById(R.id.price4);
            num4 += 1;
            prc4 = 10000 * num4;
            qty4.setText("" + num4);
            rp4.setText("Rp. " + prc4);
            if (num4 > 0){
                p4 = "Ramen Pedas";
            }else if(num2 <= 0){
                p4 = "";
            }
        }
    }

    public void minClick(View view){
        if (view.getId() == R.id.minBtn1){
            TextView qty1 = (TextView)findViewById(R.id.cart_product_quantity_tv);
            TextView rp1 = (TextView)findViewById(R.id.price1);
            prc1 = prc1 - 5000;
            num1 -= 1;
            qty1.setText("" + num1);
            rp1.setText("Rp. " + prc1);
            if (num1 <= 0){
                p1 = "";
                num1 = 0;
                prc1 = 5000;
                qty1.setText("" + num1);
                rp1.setText("Rp. " + prc1);
            }
        }else if (view.getId() == R.id.minBtn2){
            TextView qty2 = (TextView)findViewById(R.id.cart_product_quantity_tv2);
            TextView rp2 = (TextView)findViewById(R.id.price2);
            prc2 = prc2 - 6000;
            num2 -= 1;
            qty2.setText("" + num2);
            rp2.setText("Rp. " + prc2);
            if (num2 <= 0){
                p2 = "";
                num2 = 0;
                prc2 = 6000;
                qty2.setText("" + num2);
                rp2.setText("Rp. " + prc2);
            }
        }else if (view.getId() == R.id.minBtn3) {
            TextView qty3 = (TextView)findViewById(R.id.cart_product_quantity_tv3);
            TextView rp3 = (TextView)findViewById(R.id.price3);
            prc3 = prc3 - 6000;
            num3 -= 1;
            qty3.setText("" + num3);
            rp3.setText("Rp. " + prc3);
            if (num3 <= 0){
                p3 = "";
                num3 = 0;
                prc3 = 6000;
                qty3.setText("" + num3);
                rp3.setText("Rp. " + prc3);
            }
        }else if (view.getId() == R.id.minBtn4) {
            TextView qty4 = (TextView)findViewById(R.id.cart_product_quantity_tv4);
            TextView rp4 = (TextView)findViewById(R.id.price4);
            prc4 = prc4 - 10000;
            num4 -= 1;
            qty4.setText("" + num4);
            rp4.setText("Rp. " + prc4);
            if (num4 <= 0){
                p4 = "";
                num4 = 0;
                prc4 = 10000;
                qty4.setText("" + num4);
                rp4.setText("Rp. " + prc4);
            }
        }
    }

    public void orderClick(View view){

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = mRef.child("Users/" + mAuth.getCurrentUser().getUid());

        userRef.child("product1").setValue(p1);
        userRef.child("product2").setValue(p2);
        userRef.child("product3").setValue(p3);
        userRef.child("product4").setValue(p4);

        userRef.child("price1").setValue(prc1);
        userRef.child("price2").setValue(prc2);
        userRef.child("price3").setValue(prc3);
        userRef.child("price4").setValue(prc4);

        userRef.child("address").setValue(address);

        Toast.makeText(OrderActivity.this, "Order Successful", Toast.LENGTH_SHORT).show();
    }
}

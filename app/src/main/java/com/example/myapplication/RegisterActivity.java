package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //firebase init
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }

        });
        binding.regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }
     private String name="",email="",password="",cpassword="";
    private void validateData() {
      name=binding.username.getText().toString().trim();
      email=binding.mail.getText().toString().trim();
      password=binding.pass.getText().toString().trim();
      cpassword=binding.pass2.getText().toString().trim();
      if(TextUtils.isEmpty(name)){
          Toast.makeText(this,"Enter name please",Toast.LENGTH_SHORT).show();
      } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
          Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show();
      } else if (TextUtils.isEmpty(password)) {
          Toast.makeText(this,"Enter password please",Toast.LENGTH_SHORT).show();
      }else if (TextUtils.isEmpty(cpassword)) {
          Toast.makeText(this,"Enter password Confirmation please",Toast.LENGTH_SHORT).show();
      }else if (!password.equals(cpassword)) {
          Toast.makeText(this,"password not matching!!",Toast.LENGTH_SHORT).show();
      }else{
         createUserAccount();
      }
    }

    private void createUserAccount() {
        progressDialog.setMessage("Creating Account");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                          updateUserInfo();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(RegisterActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

    }

    private void updateUserInfo() {
        progressDialog.setMessage("Saving Infos");
        progressDialog.show();
        String uid=firebaseAuth.getUid();
        HashMap<String,Object> hashmap=new HashMap<>();
        hashmap.put("uid",uid);
        hashmap.put("name",name);
        hashmap.put("email",email);
        hashmap.put("password",password);

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid).setValue(hashmap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this,"Account Created",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(RegisterActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
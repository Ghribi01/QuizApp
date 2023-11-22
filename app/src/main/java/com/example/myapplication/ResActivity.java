package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityResBinding;

public class ResActivity extends AppCompatActivity {
    private ActivityResBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        int score=intent.getIntExtra("score",0);
        if(score==15){
            binding.remarque.setText("Great!!");
            binding.res.setText("You Win! Score:"+score+"/15");
        }else{
            binding.remarque.setText("Oh No!");
            binding.res.setText("You Lost! Score:"+score+"/15");
        }
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResActivity.this,LoginActivity.class));
            }
        });
        binding.tryA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResActivity.this,QuizActivity.class));
            }
        });
    }
}
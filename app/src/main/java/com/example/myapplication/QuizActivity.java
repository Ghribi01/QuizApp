package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityQuizBinding;

import java.util.ArrayList;
import java.util.*;

public class QuizActivity extends AppCompatActivity {
    private ActivityQuizBinding binding;
    protected List<QuestionModel> list=new ArrayList<QuestionModel>();
    private int count=0;
    protected  int score;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        QuestionModel q1=new QuestionModel("Combien de rayures y a-t-il sur le drapeau américain ?","14","13","12","10","13");
        QuestionModel q2=new QuestionModel("Quel est l’animal national de l’Australie ?","Lion","Singe","Chien","kangourou rouge","kangourou rouge");
        QuestionModel q3=new  QuestionModel("Quelle est la capitale du Canada ?","Quebec","Ottawa","Paris","Casablanca","Ottawa");
        QuestionModel q4=new QuestionModel("En quelle année le métro de Londres a-t-il été ouvert ?","2000","1800","1863","1920","1863");
        list.add(new QuestionModel("Quel verbe est utilisé pour désigner le cri des dauphins ?","Gazouiller","Glapir","Siffler","Chanter","Siffler"));
        list.add(new QuestionModel("De quelle ville Billie Eilish est-elle originaire ?","Los Angelos","Beja","Marseille","Beijin","Los Angelos"));
        list.add(new QuestionModel("Quelle est la date de l'abolition de l'esclavage aux États-Unis ?","1820","1865","1900","1923","1865"));
        list.add(new QuestionModel("Quel est le prénom de la fiancée de Popeye ?","Barabra","Nicole","Olive","Rose","Olive"));
        list.add(new QuestionModel("De quel pays Tirana est-elle la capitale ?","Thailande","Mexique","Albanie","Madagascar","Albanie"));
        list.add(new QuestionModel("Combien de côtés possède un dodécagone ?","10","12","11","14","12"));
        list.add(new QuestionModel("À quelle couleur, l'adjectif \"isabelle\" se rapporte-t-il ?","Jaune","Vert","Rouge","Violet","Jaune"));
        list.add(new QuestionModel("Quel était le nom du premier prototype d'avion créé par Clément Ader et testé en 1890 ?","Hellen","éole","Ulysse","Atlas","éole"));
        list.add(new QuestionModel("Quel est le nom de la souris compagnon de Dumbo dans le grand classique Disney ?","Jérémie","Timothé","Lucien","Gus","Timothé"));
        list.add(new QuestionModel("Quelles sont les couleurs du drapeau de la Thaïlande ?","Rouge,Blanc et Jaune","Rouge et Blanc","Rouge et Bleu","Rouge,Bleu et Blanc","Rouge,Bleu et Blanc"));
        list.add(new QuestionModel("Que permet de mesurer l'échelle de Scoville ?","La douleur","L'intensité des piments","La force des vagues","La persistence des Odeurs","L'intensité des piments"));
        list.add(q1);
        list.add(q2);
        list.add(q3);
        list.add(q4);
        binding.question.setText(list.get(count).question);
        binding.option1.setText(list.get(count).option1);
        binding.option2.setText(list.get(count).option2);
        binding.option3.setText(list.get(count).option3);
        binding.option4.setText(list.get(count).option4);
        binding.option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextData(binding.option1.getText().toString().trim());
            }
        });
        binding.option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextData(binding.option2.getText().toString().trim());
            }
        });
        binding.option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextData(binding.option3.getText().toString().trim());
            }
        });
        binding.option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextData(binding.option4.getText().toString().trim());

            }
        });

    }
    private void nextData(String option) {
        if (list.get(count).ans.equals(option)) {
            score++;

        }
        count++;
        if (count>=list.size()) {
            Intent intent=new Intent(QuizActivity.this,ResActivity.class);
            intent.putExtra("score",score);
            startActivity(intent);
            finish();
            return;
        }

        if (count<list.size()) { // Check if there are more questions
            binding.question.setText(list.get(count).question);
            binding.option1.setText(list.get(count).option1);
            binding.option2.setText(list.get(count).option2);
            binding.option3.setText(list.get(count).option3);
            binding.option4.setText(list.get(count).option4);
        }
    }

}
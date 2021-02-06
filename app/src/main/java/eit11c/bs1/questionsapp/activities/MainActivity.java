package eit11c.bs1.questionsapp.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import eit11c.bs1.questionsapp.http.HttpService;
import eit11c.bs1.questionsapp.R;
import eit11c.bs1.questionsapp.pojos.Categories;
import eit11c.bs1.questionsapp.pojos.Quiz;

public class MainActivity extends AppCompatActivity {

    HttpService _httpService;
    List<Categories.Category> kategorien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _httpService = new HttpService();
        _httpService.getCategories();
        initDifficultyDropdown();
        EventBus.getDefault().register(this);
    }

    public void startQuiz(View v) {
        String difficulty = ((Spinner) findViewById(R.id.difficulitySelect)).getSelectedItem().toString();
        String category = ((Spinner) findViewById(R.id.categorieSelect)).getSelectedItem().toString();
        for (Categories.Category kategorie : kategorien) {
            if (kategorie.getName().equals(category)) {
                _httpService.getQuiz(difficulty, kategorie.getId());
            }
        }
    }

    public void initDifficultyDropdown() {
        Spinner difficultyDropdown = findViewById(R.id.difficulitySelect);

        String[] difficulties = new String[3];
        difficulties[0] = "easy";
        difficulties[1] = "medium";
        difficulties[2] = "hard";
        ArrayAdapter<String> dropdownManager = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficulties);
        dropdownManager.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        difficultyDropdown.setAdapter(dropdownManager);
    }

    @Subscribe
    public void initCategorieDropdown(Categories categories) {
        Spinner categorieDropdown = findViewById(R.id.categorieSelect);
        List<String> names = new ArrayList<>();
        kategorien = new ArrayList<>();
        for (Categories.Category category : categories.getCategories()) {
            names.add(category.getName());
            kategorien.add(category);
        }
        ArrayAdapter<String> dropdownManager = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        dropdownManager.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorieDropdown.setAdapter(dropdownManager);
    }

    @Subscribe
    public void goToActivity(Quiz quiz) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("quiz", quiz);
        intent.putExtra("position", 1);
        startActivity(intent);
    }
}

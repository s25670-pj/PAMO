package com.example.kalkulatorbmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {

    private ListView listViewRecipes;
    private TextView textViewRecipeTitle;
    private TextView textViewRecipeIngredients;
    private TextView textViewRecipeInstructions;
    private TextView textViewRecipeCalories;
    private Button btnShowShoppingList;

    private List<String> recipeNames = new ArrayList<>();
    private List<String> recipeIngredients = new ArrayList<>();
    private List<String> recipeInstructions = new ArrayList<>();
    private List<String> recipeCalories = new ArrayList<>();

    private int currentRecipePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        listViewRecipes = findViewById(R.id.listViewRecipes);
        textViewRecipeTitle = findViewById(R.id.textViewRecipeTitle);
        textViewRecipeIngredients = findViewById(R.id.textViewRecipeIngredients);
        textViewRecipeInstructions = findViewById(R.id.textViewRecipeInstructions);
        textViewRecipeCalories = findViewById(R.id.textViewRecipeCalories);
        btnShowShoppingList = findViewById(R.id.btnShowShoppingList);

        addRecipes();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipeNames);
        listViewRecipes.setAdapter(adapter);

        listViewRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentRecipePosition = position;
                displayRecipe(position);
            }
        });

        btnShowShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipesActivity.this, ShoppingListActivity.class);
                intent.putExtra("RECIPE_NAME", recipeNames.get(currentRecipePosition));
                startActivity(intent);
            }
        });

        if (!recipeNames.isEmpty()) {
            displayRecipe(0);
        }
    }

    private void addRecipes() {
        // Przepis 1: Sałatka z quinoa
        recipeNames.add("Sałatka z quinoa i warzywami");
        recipeIngredients.add("• 1 szklanka quinoa\n" +
                "• 2 szklanki wody\n" +
                "• 1 ogórek pokrojony w kostkę\n" +
                "• 1 czerwona papryka pokrojona w kostkę\n" +
                "• 1 żółta papryka pokrojona w kostkę\n" +
                "• 1 puszka ciecierzycy, odsączona i przepłukana\n" +
                "• 1/2 czerwonej cebuli drobno posiekanej\n" +
                "• garść świeżej posiekanej pietruszki\n" +
                "• sok z 1 cytryny\n" +
                "• 3 łyżki oliwy z oliwek\n" +
                "• sól i pieprz do smaku");
        recipeInstructions.add("1. Quinoa przepłukać dokładnie pod zimną wodą.\n" +
                "2. Gotować quinoa w wodzie przez około 15 minut, aż woda zostanie wchłonięta.\n" +
                "3. Odstawić do ostygnięcia.\n" +
                "4. W dużej misce połączyć ostudzoną quinoa z ogórkiem, papryką, ciecierzycą i cebulą.\n" +
                "5. Dodać posiekaną pietruszkę.\n" +
                "6. Polać sokiem z cytryny i oliwą, doprawić solą i pieprzem.\n" +
                "7. Delikatnie wymieszać i schłodzić przed podaniem.");
        recipeCalories.add("Około 320 kcal na porcję");

        // Przepis 2: Pieczony łosoń
        recipeNames.add("Pieczony łosoś z warzywami");
        recipeIngredients.add("• 4 filety łososia (po około 150g każdy)\n" +
                "• 2 cukinie pokrojone w plasterki\n" +
                "• 2 papryki pokrojone w paski\n" +
                "• 1 czerwona cebula pokrojona w piórka\n" +
                "• 4 łyżki oliwy z oliwek\n" +
                "• 2 ząbki czosnku, posiekane\n" +
                "• 1 łyżka suszonego oregano\n" +
                "• sok z połowy cytryny\n" +
                "• sól i pieprz do smaku\n" +
                "• świeże zioła do podania (opcjonalnie)");
        recipeInstructions.add("1. Rozgrzać piekarnik do 200°C.\n" +
                "2. W misce wymieszać oliwę, czosnek, oregano, sok z cytryny, sól i pieprz.\n" +
                "3. Ułożyć warzywa na blasze do pieczenia, polać połową marynaty.\n" +
                "4. Piec warzywa przez 10 minut.\n" +
                "5. Filety łososia natrzeć pozostałą marynatą.\n" +
                "6. Ułożyć łososia na warzywach i piec przez kolejne 12-15 minut.\n" +
                "7. Podawać posypane świeżymi ziołami.");
        recipeCalories.add("Około 380 kcal na porcję");

        // Przepis 3: Koktajl proteinowy
        recipeNames.add("Koktajl proteinowy z owocami");
        recipeIngredients.add("• 1 banan\n" +
                "• 1 szklanka truskawek lub innych owoców jagodowych\n" +
                "• 1 łyżka masła orzechowego (opcjonalnie)\n" +
                "• 1 łyżka nasion chia\n" +
                "• 1 miarka (około 30g) białka w proszku (opcjonalnie)\n" +
                "• 250ml mleka migdałowego lub innego według preferencji\n" +
                "• 1 łyżeczka miodu lub syropu klonowego (opcjonalnie)\n" +
                "• kostki lodu");
        recipeInstructions.add("1. Wszystkie składniki umieścić w blenderze.\n" +
                "2. Miksować na wysokich obrotach przez około 1 minutę, aż koktajl będzie gładki.\n" +
                "3. W razie potrzeby dostosować konsystencję dodając więcej płynu.\n" +
                "4. Podawać natychmiast, udekorowany dodatkowymi owocami.");
        recipeCalories.add("Około 250-350 kcal na porcję, zależnie od dodatków");
    }

    private void displayRecipe(int position) {
        if (position >= 0 && position < recipeNames.size()) {
            textViewRecipeTitle.setText(recipeNames.get(position));
            textViewRecipeIngredients.setText(recipeIngredients.get(position));
            textViewRecipeInstructions.setText(recipeInstructions.get(position));
            textViewRecipeCalories.setText(recipeCalories.get(position));
        }
    }
}
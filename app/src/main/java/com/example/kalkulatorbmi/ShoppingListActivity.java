package com.example.kalkulatorbmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalkulatorbmi.adapter.ShoppingListAdapter;
import com.example.kalkulatorbmi.model.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity implements ShoppingListAdapter.OnItemCheckListener {

    private RecyclerView recyclerViewShoppingList;
    private ShoppingListAdapter adapter;
    private List<ShoppingItem> shoppingItems;
    private TextView tvProgressInfo;
    private TextView tvRecipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        recyclerViewShoppingList = findViewById(R.id.recyclerViewShoppingList);
        tvProgressInfo = findViewById(R.id.tvProgressInfo);
        tvRecipeName = findViewById(R.id.tvRecipeName);
        Button btnBackToMenu = findViewById(R.id.btnBackToMenu);

        // Ustaw nazwę przepisu, dla którego tworzymy listę zakupów
        String recipeName = getIntent().getStringExtra("RECIPE_NAME");
        if (recipeName != null && !recipeName.isEmpty()) {
            tvRecipeName.setText("Składniki do przepisu: " + recipeName);
        }

        // Inicjalizacja listy zakupów
        initShoppingList();

        // Konfiguracja RecyclerView
        recyclerViewShoppingList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingListAdapter(shoppingItems, this);
        recyclerViewShoppingList.setAdapter(adapter);

        updateProgressInfo();

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initShoppingList() {
        shoppingItems = new ArrayList<>();

        // Dodaj przykładowe produkty dla przepisu "Sałatka z quinoa i warzywami"
        shoppingItems.add(new ShoppingItem(1, "Quinoa", "1 szklanka"));
        shoppingItems.add(new ShoppingItem(2, "Ogórek", "1 sztuka"));
        shoppingItems.add(new ShoppingItem(3, "Czerwona papryka", "1 sztuka"));
        shoppingItems.add(new ShoppingItem(4, "Żółta papryka", "1 sztuka"));
        shoppingItems.add(new ShoppingItem(5, "Ciecierzyca w puszce", "1 puszka"));
        shoppingItems.add(new ShoppingItem(6, "Czerwona cebula", "1/2 sztuki"));
        shoppingItems.add(new ShoppingItem(7, "Pietruszka", "1 pęczek"));
        shoppingItems.add(new ShoppingItem(8, "Cytryna", "1 sztuka"));
        shoppingItems.add(new ShoppingItem(9, "Oliwa z oliwek", "3 łyżki"));
        shoppingItems.add(new ShoppingItem(10, "Sól i pieprz", "do smaku"));
    }

    @Override
    public void onItemCheck(int position, boolean isChecked) {
        if (position >= 0 && position < shoppingItems.size()) {
            shoppingItems.get(position).setChecked(isChecked);
            adapter.notifyItemChanged(position);
            updateProgressInfo();
        }
    }

    private void updateProgressInfo() {
        int checkedCount = 0;
        for (ShoppingItem item : shoppingItems) {
            if (item.isChecked()) {
                checkedCount++;
            }
        }

        tvProgressInfo.setText("Kupiono produktów: " + checkedCount + "/" + shoppingItems.size());
    }
}
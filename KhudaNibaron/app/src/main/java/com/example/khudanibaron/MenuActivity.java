package com.example.khudanibaron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.khudanibaron.Adapters.MainAdapter;
import com.example.khudanibaron.Models.MainModel;
import com.example.khudanibaron.databinding.ActivityMenuBinding;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.setTitle("Menu");

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger, "Burger", "339", "Beef Burger with Extra cheese"));
        list.add(new MainModel(R.drawable.pizza, "Pizza", "139", "Chicken Pizza with Extra cheese"));
        list.add(new MainModel(R.drawable.chicken, "Chicken", "150", "Spicy Chicken"));
        list.add(new MainModel(R.drawable.portbello, "Portobello Mushroom", "300", "Baked Portobello Mushroom with cheese"));
        list.add(new MainModel(R.drawable.pizzabargur, "Pizza Burger", "200", "Pizza Burger with Extra cheese"));

        list.add(new MainModel(R.drawable.subsandwich, "Sub Sandwich", "180", "Sub Sandwich with Extra cheese"));


        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerViewId.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewId.setLayoutManager(linearLayoutManager);

    }
}
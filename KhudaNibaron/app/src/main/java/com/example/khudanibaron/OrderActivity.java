package com.example.khudanibaron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.khudanibaron.Adapters.OrdersAdapter;
import com.example.khudanibaron.Models.OrdersModel;
import com.example.khudanibaron.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<OrdersModel> list = new ArrayList<>();
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));
        list.add(new OrdersModel(R.drawable.burger, "Cheese Burger", "320", "451234556"));

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);
    }
}
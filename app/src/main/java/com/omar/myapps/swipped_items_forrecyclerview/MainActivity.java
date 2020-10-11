package com.omar.myapps.swipped_items_forrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycleView;
    MyRecyclerAdapter recyclerAdapter;
    List<Car> mList;

    private void initRecyclerView() {
        mRecycleView = findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
        mList.add(new Car("BMW", 2000));
        mList.add(new Car("jeep", 2010));
        mList.add(new Car("sony", 2018));
        mList.add(new Car("ford", 2020));
        recyclerAdapter = new MyRecyclerAdapter(MainActivity.this, mList);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecycleView.setAdapter(recyclerAdapter);
    }


    ItemTouchHelper.Callback ithCallback = new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                    ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            // get the viewHolder's and target's positions in your adapter data, swap them
            Collections.swap(mList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
            // and notify the adapter that its data set has changed
            recyclerAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();


        // Create an `ItemTouchHelper` and attach it to the `RecyclerView`
        ItemTouchHelper ith = new ItemTouchHelper(ithCallback);
        ith.attachToRecyclerView(mRecycleView);
    }
}
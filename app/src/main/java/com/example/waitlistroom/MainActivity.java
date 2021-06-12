package com.example.waitlistroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.waitlistroom.database.AppDatabase;
import com.example.waitlistroom.database.Guest;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GuestAdapter.ItemClickListener {


    private GuestAdapter guestAdapter;
    private AppDatabase Db;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
        Button addNewGuestButton=findViewById(R.id.addNewGuestbutton);
        addNewGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,AddNewGuestActivity.class),100);
            }
        });




        initRecyclerView();
        loadGuestList();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipedir) {

                int position=viewHolder.getAdapterPosition();
                List<Guest> guestList=db.guestDao().getAllUsers();
                db.guestDao().delete(guestList.get(position));
                loadGuestList();
            }
        }).attachToRecyclerView(recyclerView);;
    }

    private void initRecyclerView(){
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        guestAdapter=new GuestAdapter(this, this);
        recyclerView.setAdapter(guestAdapter);
    }

    private void loadGuestList(){
        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
        List<Guest> guestList=db.guestDao().getAllUsers();
        guestAdapter.setGuestList(guestList);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100){
            loadGuestList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClickListener(int itemId) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadGuestList();
    }
}
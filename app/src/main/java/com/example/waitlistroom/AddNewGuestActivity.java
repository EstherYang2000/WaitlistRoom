package com.example.waitlistroom;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.waitlistroom.database.AppDatabase;
import com.example.waitlistroom.database.Guest;

public class AddNewGuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_guest);
        EditText GuestNameInput=findViewById(R.id.person_name_edit_text);
        EditText PartySizeInput=findViewById(R.id.party_count_edit_text);
        Button saveButton=findViewById(R.id.add_to_waitlist_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewGuest(GuestNameInput.getText().toString(),PartySizeInput.getText().toString());
            }
        });

    }

    private void saveNewGuest(String guestname, String partysize){

        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
        Guest guest=new Guest();
        guest.GuestName=guestname;
        guest.Partysize=partysize;
        db.guestDao().insertGuest(guest);

        finish();
    }


}
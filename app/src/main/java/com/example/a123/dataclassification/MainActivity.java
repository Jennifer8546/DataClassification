package com.example.a123.dataclassification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        final String j =i.getStringExtra("selected");

        final ListView listView = (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item,
                android.R.id.text1);

        listView.setAdapter(adapter);

        DatabaseReference reference_contacts = FirebaseDatabase.getInstance().getReference("Upload").child("Public");
        reference_contacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    adapter.add(ds.child("PPT'sName").getValue().toString());
                }
            }
            @Override
            public void onCancelled (DatabaseError databaseError){

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             /* String selected = listView.getItemAtPosition(position).toString();

                Intent intent =   new Intent(getApplicationContext(), TextView.class);
                intent.putExtra("selected",selected);
                intent.putExtra("menu",j);
                startActivity(intent);*/
            }
        });
    }
}
package com.example.contact;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ContactAdapter adapter;
    private MyDatabase db;
    ArrayList<Contact> listObjectContacts;
    private Button mBtnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDatabase(this);
        mBtnAdd = (Button)findViewById(R.id.btn_add_contact);
        listObjectContacts = new ArrayList<Contact>();


//        listObjectContacts.add(new Contact("a","b","b","d","d",1));


        setRecyclerView();
//        getData();

//        setOnClickOnItem();
        onClickAddBtn();
    }

    public void setRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_contacts);
        recyclerView.setHasFixedSize(true);

        adapter = new ContactAdapter(listObjectContacts , MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecycleTouchListener(this, recyclerView, new RecycleTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getBaseContext() , EditContactActivity.class);
                Contact getContact = listObjectContacts.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Contact" , getContact);
                intent.putExtra("My package" , bundle);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Contact getContact = listObjectContacts.get(position);
                db.deleteContact(getContact);
                listObjectContacts.remove(position);
                Toast.makeText(MainActivity.this , "Deleted" , Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        }));

    }

    public Contact findContact(String name){
        for (Contact contact:listObjectContacts){
            if(name == contact.getFullname())
                return contact;
        }
        return null;
    }

//    public void setOnClickOnItem(){
//        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(MainActivity.this , EditContactActivity.class);
//                String item = lvContacts.getItemAtPosition(position).toString();
//                Contact getContact = findContact(item);
//                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("Contact" , getContact);
//                intent.putExtra("My package" , bundle);
//                startActivity(intent);
//            }
//        });
//    }
    public  void onClickAddBtn(){
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AddNewContact.class);
                startActivityForResult(intent , 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode==RESULT_OK){
            Contact contact =(Contact)data.getExtras().getSerializable("Return");
            listObjectContacts.add(contact);
            adapter.notifyDataSetChanged();
            Log.d("debug1","" + contact);
        }
    }
    public void getData(){
        listObjectContacts.clear();
        listObjectContacts = db.getAllContacts();
        db.close();
        adapter.notifyDataSetChanged();
    }

}





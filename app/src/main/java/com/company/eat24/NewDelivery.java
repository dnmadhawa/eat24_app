package com.company.eat24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

//import android.widget.EditText;
//import android.widget.ListAdapter;
//import android.widget.Toast;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.ValueEventListener;
//import com.example.crud_firebase.DBHelper.DBHelper;
//import java.util.ArrayList;

public class NewDelivery extends AppCompatActivity {
//    EditText name,address,delivery_fee, mobile_number,note,order_num;
//    private RecyclerView list;
//    private DatabaseReference databaseReference;
//    private static ArrayList<Delivery> arrayList  = new ArrayList<Delivery>();

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_delivery);
//
//        name=(EditText)findViewById(R.id.add_name);
//        mobile_number=(EditText)findViewById(R.id.add_email);
//        address=(EditText)findViewById(R.id.add_course);
//        note=(EditText)findViewById(R.id.add_purl);
//        delivery_fee=(EditText)findViewById(R.id.add_purl);
//=(EditText)findViewById(R.id.add_purl);
    }
//
//    private void processinsert()
//    {
//        Map<String,Object> map=new HashMap<>();
//        map.put("name",name.getText().toString());
//        map.put("mobile_number",mobile_number.getText().toString());
//        map.put("address",address.getText().toString());
//        map.put("note",note.getText().toString());
//        map.put("delivery_fee",delivery_fee.getText().toString());
//        map.put("order_num",order_num.getText().toString());
//        FirebaseDatabase.getInstance().getReference().child("Deliverys").push()
//                .setValue(map)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        name.setText("");
//                        mobile_number.setText("");
//                        address.setText("");
//                        note.setText("");
//                        delivery_fee.setText("");
//                        order_num.setText("");
//                        Toast.makeText(getApplicationContext(),"Ordered Successfully",Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure( Exception e)
//                    {
//                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
//                    }
//                });
//
//    }
//
//    public void getData(){
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            String userName, email, number;
//            @Override
//            public void onDataChange( DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    userName =dataSnapshot.child("userName").getValue().toString();
//                    email = dataSnapshot.child("email").getValue().toString();
//                    number = dataSnapshot.child("number").getValue().toString();
//                    arrayList.add(new Delivery(name,mobile_number,address,note,delivery_fee,order_num));;
//                }
//                list.setAdapter(new ListAdapter(arrayList,NewDelivery.this));
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }
}
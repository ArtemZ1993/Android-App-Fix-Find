package com.example.user_password_application_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.accounts.AccountManagerFuture;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth FirebaseAuthentication;
    private FirebaseFirestore FirebaseUsers;
    private FirebaseDatabase Database = FirebaseDatabase.getInstance();
    private DatabaseReference RealTimeDatabase = Database.getReference();

    FusedLocationProviderClient fusedLocationProviderClient;
    public static double latitude = 32.012826;
    public static double longitude = 34.776821;

    public User user;
    public static String UserName = "";

    public static boolean IsLogin = false;
    public static boolean IsRegister = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        GetMyLocation();

        FirebaseUsers = FirebaseFirestore.getInstance();
        FirebaseAuthentication = FirebaseAuth.getInstance();
    }

    public void GetMyLocation() {

        if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if(location != null){
                        try {
                            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                            latitude = addresses.get(0).getLatitude();
                            longitude = addresses.get(0).getLongitude();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
    }

    public void loginAccount(){

        String email = ((EditText)findViewById(R.id.LoginTextEmail)).getText().toString();
        String password = ((EditText)findViewById(R.id.LoginTextPassword)).getText().toString();

        FirebaseAuthentication.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            getData();
                            IsLogin = true;
                            Toast.makeText(MainActivity.this,"Login Ok",Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            IsLogin = false;
                            Toast.makeText(MainActivity.this,"Login Fail",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void RegisterAccount(){

        String email = ((EditText)findViewById(R.id.RegisterTextEmail)).getText().toString();
        String password = ((EditText)findViewById(R.id.RegisterTextPassword)).getText().toString();

        FirebaseAuthentication.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            addData();
                            IsRegister = true;
                            Toast.makeText(MainActivity.this,"Register Ok",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            IsRegister = false;
                            Toast.makeText(MainActivity.this,"Register Fail",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void addData(){

        Map<String, Object> user = new HashMap<>();
        user.put("User_Name",((EditText)findViewById(R.id.RegisterTextUserName)).getText().toString());
        user.put("Email", ((EditText)findViewById(R.id.RegisterTextEmail)).getText().toString());
        user.put("Phone_Number",((EditText)findViewById(R.id.RegisterTextPhone)).getText().toString());
        user.put("Send_Message","Message");

        FirebaseUsers.collection("Users").add(user);
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void getData(){

        db.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String keyEmail = ((EditText)findViewById(R.id.LoginTextEmail)).getText().toString();
                        if (task.isSuccessful()) {
                            try {
//                                System.out.println("===========================");
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    String email = (String)document.getData().get("Email");

                                    if(email != null && email.equals(keyEmail))
                                    {
                                        user.setMail(email);
                                        user.setName((String) document.getData().get("User_Name"));
                                        user.setPhone((String) document.getData().get("Phone_Number"));
//                                        System.out.println(" ******** " + user.getId() + user.getMail() + user.getName() + user.getPhone());
                                        UserName = (String) document.getData().get("User_Name");
                                    }
                                }
//                                System.out.println("===========================");
                            }
                            catch (Exception e){
                                System.out.println("Error getting documents. " + e.toString());
                            }
                        } else {
                            System.out.println("Error getting documents. " + task.getException());
                        }
                    }
                });
    }
    List<String> dateArray = new ArrayList<String>();

    public void GetForumMainWindow() {

        // Read from the database
        RealTimeDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                StringBuffer stringBuffer = new StringBuffer();

                if(dataSnapshot.getValue() != null ) {

                    HashMap<Object,Object> value = (HashMap<Object, Object>) dataSnapshot.getValue();
                    Object[] Main_Key_Set = value.keySet().toArray();

                    HashMap<Object,Object> value1 = (HashMap<Object, Object>) value.get(Main_Key_Set[0]);

                    for (Object key : value1.keySet()) {

                        HashMap<Object, Object> value2 = (HashMap<Object, Object>) value1.get(key);
                        Main_Key_Set = value2.keySet().toArray();

                        if(Main_Key_Set.length > 1){

                            List<String> dateArray = new ArrayList<String>();

                            for (Object date : Main_Key_Set) {
                                dateArray.add(date.toString());
                            }
                            dateArray.sort((d1, d2) -> d1.compareTo(d2));

                            stringBuffer.append("\n\n" + "נכתב בתאריך " + dateArray.get(0).toString());
                            stringBuffer.append("\n" + "על ידי " + key.toString());

                            HashMap<Object, Object> value3 = (HashMap<Object, Object>) value2.get(dateArray.get(0));
                            Object[] objects1 = value3.keySet().toArray();

                            stringBuffer.append("\n" + "כותרת  " + objects1[0].toString() + "\n" + "הודעה  " + value3.get(objects1[0]).toString());
                            stringBuffer.append("\n\n" + " * תגובות * ");

                            for (int i = 1 ; i < dateArray.size() ;i++) {

                                value3 = (HashMap<Object, Object>) value2.get(dateArray.get(i));
                                objects1 = value3.keySet().toArray();
                                stringBuffer.append("\n" + " נכתב על ידי " + objects1[0].toString() + "\n"  + value3.get(objects1[0]).toString());
                            }
                        }
                        else{
                            stringBuffer.append("\n" + "נכתב בתאריך " + Main_Key_Set[0].toString());
                            stringBuffer.append("\n" + "על ידי " + key.toString());

                            HashMap<Object, Object> value3 = (HashMap<Object, Object>) value2.get(Main_Key_Set[0]);
                            Main_Key_Set = value3.keySet().toArray();
                            stringBuffer.append("\n" + "כותרת  " + Main_Key_Set[0].toString() + "\n" + "הודעה  " + value3.get(Main_Key_Set[0]).toString());
                        }
                    }

                    TextView textView = findViewById(R.id.TextViewForumMainWindow);
                    textView.setText("");
                    textView.setText(stringBuffer);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void addNewMessage() {

        if(IsLogin){

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SS");
            String Title = ((EditText)findViewById(R.id.TextViewForumTitleOfTheMessage)).getText().toString();
            String Message = ((EditText)findViewById(R.id.TextViewForumMessage)).getText().toString();
            String sendTo = ((EditText)findViewById(R.id.TextViewRespondToUser)).getText().toString();

            if(sendTo.equals("אם אתה רוצה להשיב למשהו ,תרשום את שמו כאן :)")){
                RealTimeDatabase.child("RealTimeMessage").child(user.getName()).child(formatter.format(date)).child(Title).setValue(Message);
            }
            else{
                RealTimeDatabase.child("RealTimeMessage").child(sendTo).child(formatter.format(date)).child(user.getName()).setValue(Message);
            }

        }
        else {
            Toast.makeText(MainActivity.this,"Please login to the User",Toast.LENGTH_LONG).show();

        }
    }
}
package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.StudentAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity implements ExampeDialog.ExampleDialogListener/*,PopupMenu.OnMenuItemClickListener*/{
    CircularImageView ajouter;
    ImageView rech;
    EditText manee;
    ListView studentListView;
    List<Student> studentList;
    DatabaseReference fref = FirebaseDatabase.getInstance().getReference().child("les etudiants");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentListView =(ListView) findViewById(R.id.student_list_view);
        manee =(EditText)findViewById(R.id.text_de_rechrech);
        rech =(ImageView) findViewById(R.id.rechrr_image);
        fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Student student = new Student();
                    student = snapshot.getValue(Student.class);
                    studentList.add(student);
                }

                StudentAdapter  adapter = new StudentAdapter(MainActivity.this,studentList);
                studentListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ajouter =  findViewById(R.id.fab);

        ajouter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openDialoge();

            }
        });

  rech.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          String nam =manee.getText().toString();
          if(nam.length() ==0){
              manee.setError("Entrer le nom compler de etudiant");
          }
          else if (Rechercher(nam)==null){
              Toast.makeText(MainActivity.this,"Ne existe aucune etudiant de ce nom",Toast.LENGTH_LONG).show();
          }
          else{
              StudentAdapter  adapterr = new StudentAdapter(MainActivity.this,Rechercher(nam));
              studentListView.setAdapter(adapterr);
          }

      }
  });
    }
    public void openDialoge (){
        ExampeDialog exampDialog =new ExampeDialog() ;
        exampDialog.show(getSupportFragmentManager(),"Example Dialoge");
    }
    @Override
    public void applyTexts(String name,String emaill,String wilayaa) {
        Student student = new Student(name,"younes",0,0,0,0);
        student.setEmail(emaill);
        student.setWilaya(wilayaa);
        String Key = fref.push().getKey();
        student.setId(Key);
       fref.child(Key).setValue(student);
    }


  public  List<Student> Rechercher(String name){
      List<Student> listee = new ArrayList<>();
      for (Student e:studentList
           ) {
          if(e.getName().equals(name)){
              listee.add(e);
          }

      }

      return listee;
  }

}

package com.example.myapplication.adapter;


import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MoyenneActivity;
import com.example.myapplication.ProfilActivity;
import com.example.myapplication.R;
import com.example.myapplication.StatActivity;
import com.example.myapplication.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter implements Filterable  {

    private Context context;
    private List<Student> studentList;
    private List<Student> studentListfilter;
    private LayoutInflater inflater;
  Dialog mydialog;
  TextView close;
  ImageView profill,stati,calcul,delet;
    DatabaseReference fref = FirebaseDatabase.getInstance().getReference().child("les etudiants");
    public StudentAdapter(Context context, List <Student> studentList){
        this.context=context;
        this.studentList=studentList;
        this.studentListfilter=studentList;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return studentListfilter.size();
    }

    @Override
    public Student getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @TargetApi(Build.VERSION_CODES.Q)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.adapter_item, null);

        Student currentStudent = getItem(position);
        final String studentName = currentStudent.getName();
        final String studentemaill = currentStudent.getEmail();
        final String studentwilaya = currentStudent.getWilaya();
        String mnemonic = currentStudent.getMnemonic();
        int studentTest1 = currentStudent.getTest1();
        int studentTest2 = currentStudent.getTest2();
        int studentAbsence = currentStudent.getAbsence();
        int studentAssiduite = currentStudent.getAssiduite();
        final String studentid = currentStudent.getId();

        ImageView studentIconView = convertView.findViewById(R.id.item_icon);
        String ressourceName = "item_"+ mnemonic +"_icon";
        int resId=context.getResources().getIdentifier(ressourceName,"drawable",context.getOpPackageName());
        studentIconView.setImageResource(resId);

        TextView studentNameView = convertView.findViewById(R.id.item_name);
        studentNameView.setText(studentName);

        TextView studentTest1View = convertView.findViewById(R.id.test1);
        studentTest1View.setText(""+studentTest1);

        TextView studentTest2View = convertView.findViewById(R.id.test2);
        studentTest2View.setText(""+studentTest2);

        TextView studentAbsenceView = convertView.findViewById(R.id.absence);
        studentAbsenceView.setText(""+studentAbsence);

        TextView studentAssiduiteView = convertView.findViewById(R.id.assiduite);
        studentAssiduiteView.setText(""+studentAssiduite);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog = new Dialog(context);
                mydialog.setContentView(R.layout.popup);


                close =(TextView) mydialog.findViewById(R.id.exit);
                profill = (ImageView) mydialog.findViewById(R.id.profill);
                stati = (ImageView) mydialog.findViewById(R.id.stat);
                calcul = (ImageView) mydialog.findViewById(R.id.moy);
                delet =(ImageView) mydialog.findViewById(R.id.delete);
                close.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        mydialog.dismiss();
                    }
                });
                profill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ProfilActivity.class);
                        intent.putExtra("name",studentName);
                        intent.putExtra("email",studentemaill);
                        intent.putExtra("wilaya",studentwilaya);
                        context.startActivity(intent);


                    }
                });
                stati.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent otherActivity2 = new Intent(context, StatActivity.class);
                        context.startActivity(otherActivity2);

                    }
                });
                calcul.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent otherActivity3 = new Intent(context, MoyenneActivity.class);
                       context.startActivity(otherActivity3);
                    }
                });
                delet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      fref.child(studentid).removeValue();
                        mydialog.dismiss();
                    }
                });

                mydialog.show();

                Toast.makeText(context,"Vous venez de cliquer sur"+studentName,Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;

    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();

                if(constraint == null||constraint.length() == 0){
                    filterResults.count= studentList.size();
                    filterResults.values=studentList;
                }
                else{
                    String searchStr = constraint.toString().toLowerCase();
                            List<Student>resultData = new ArrayList<>();
                    for(Student itemsModel:studentList){
                        if(itemsModel.getName().contains(searchStr)){
                            resultData.add(itemsModel);
                        }

                        filterResults.count = resultData.size();
                        filterResults.values = resultData;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                studentListfilter = (List<Student>)results.values;
                notifyDataSetChanged();


            }
        };
        return filter;
    }

}

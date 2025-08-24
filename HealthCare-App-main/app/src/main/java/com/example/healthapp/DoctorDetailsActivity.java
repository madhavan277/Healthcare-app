package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 = {
            {"Doctor Name : Ajit Saste", "Hospital Address : Pimpiri", "Exp : 5yrs", "Mobile No : 9898989898", "600"},
            {"Doctor Name : Prasad Pawar", "Hospital Address : Noida", "Exp : 15yrs", "Mobile No : 7898989898", "900"},
            {"Doctor Name : Swapnil Kale", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No : 8898989898", "300"},
            {"Doctor Name : Deepak Deshmukh", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No : 9898000000", "500"},
            {"Doctor Name : Ashok Panda", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 7798989898", "800"}
    };

    private String[][] doctor_details2 = {
            {"Doctor Name : Ajit Saste", "Hospital Address : Pimpiri", "Exp : 5yrs", "Mobile No : 9898989898", "600"},
            {"Doctor Name : Prasad Pawar", "Hospital Address : Noida", "Exp : 15yrs", "Mobile No : 7898989898", "900"},
            {"Doctor Name : Swapnil Kale", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No : 8898989898", "300"},
            {"Doctor Name : Deepak Deshmukh", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No : 9898000000", "500"},
            {"Doctor Name : Ashok Panda", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 7798989898", "800"}
    };

    private String[][] doctor_details3 = {
            {"Doctor Name : Seema Patil", "Hospital Address : Pimpiri", "Exp : 4yrs", "Mobile No : 9898989898", "200"},
            {"Doctor Name : Pinkaj Pirab", "Hospital Address : Noida", "Exp : 5yrs", "Mobile No : 7898989898", "300"},
            {"Doctor Name : Monish Jain", "Hospital Address : Pune", "Exp : 7yrs", "Mobile No : 8898989898", "300"},
            {"Doctor Name : Vishal Deshmukh", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No : 9898000000", "500"},
            {"Doctor Name : Shrikant Panda", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 7798989898", "600"}
    };

    private String[][] doctor_details4 = {
            {"Doctor Name : Ajit Saste", "Hospital Address : Pimpiri", "Exp : 5yrs", "Mobile No : 9898989898", "600"},
            {"Doctor Name : Prasad Pawar", "Hospital Address : Noida", "Exp : 15yrs", "Mobile No : 7898989898", "900"},
            {"Doctor Name : Swapnil Kale", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No : 8898989898", "300"},
            {"Doctor Name : Deepak Deshmukh", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No : 9898000000", "500"},
            {"Doctor Name : Ashok Panda", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 7798989898", "800"}
    };

    private String[][] doctor_details5 = {
            {"Doctor Name : Ajit Saste", "Hospital Address : Pimpiri", "Exp : 5yrs", "Mobile No : 9898989898", "600"},
            {"Doctor Name : Prasad Pawar", "Hospital Address : Noida", "Exp : 15yrs", "Mobile No : 7898989898", "900"},
            {"Doctor Name : Swapnil Kale", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No : 8898989898", "300"},
            {"Doctor Name : Deepak Deshmukh", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No : 9898000000", "500"},
            {"Doctor Name : Ashok Panda", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 7798989898", "800"}
    };

    String[][] doctor_details = {};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;
    TextView tv;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDtitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians") == 0){
            doctor_details = doctor_details1;
        } else if(title.compareTo("Dieticians") == 0){
            doctor_details = doctor_details2;
        } else if(title.compareTo("Dentists") == 0){
            doctor_details = doctor_details3;
        } else if(title.compareTo("Surgeons") == 0){
            doctor_details = doctor_details4;
        } else if(title.compareTo("Cardeologists") == 0){
            doctor_details = doctor_details5;
        }

        btn = findViewById(R.id.buttonDDback);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0; i<doctor_details.length; i++){
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees : " + doctor_details[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        ListView lst = findViewById(R.id.ListViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}
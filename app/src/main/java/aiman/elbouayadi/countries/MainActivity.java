package aiman.elbouayadi.countries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText2, editText3;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<MainData> dataList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText=findViewById(R.id.edit_text);
        editText2=findViewById(R.id.edit_text2);
        editText3 =findViewById(R.id.edit_text3);

        btAdd=findViewById(R.id.bt_add);
        btReset=findViewById(R.id.bt_reset);
        recyclerView=findViewById(R.id.recycler_view);

        database=RoomDB.getInstance(this);
        dataList=database.mainDao().getAll();

        //layout manager
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //adapter
        adapter=new MainAdapter(MainActivity.this,dataList);
        recyclerView.setAdapter(adapter);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText=editText.getText().toString().trim();
                String cText=editText2.getText().toString().trim();
                String nText=editText3.getText().toString().trim();

                if(!sText.equals(""))
                {
                    MainData data=new MainData();
                    data.setText(sText);
                    data.setCapital(cText);
                    data.setHabitants(Float.parseFloat(nText));

                    database.mainDao().insert(data);

                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");

                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();

                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.mainDao().reset();
                dataList=database.mainDao().getAll();
                adapter.notifyDataSetChanged();
            }
        });
    }
}

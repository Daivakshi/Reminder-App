package ca.yorku.eecs.mack.ReminderApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ca.yorku.eecs.mack.ReminderApp.Model.Pill;

public class ViewPillsActivity extends AppCompatActivity {

    /*
     * onBackPressed >> if coming from AddPillsActivity, don't go to main page instead of back to AddPillsActivity
     *
     * */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pills);

        listView =(ListView)findViewById(R.id.viewPillsListView);
        String arrayListAdderName, arrayListAdderAmount, arrayListAdderDosage, arrayListAdderDays = null, arrayListAdderFinal;
        ArrayList<String> arrayListAdderDaysList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();


        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i = 0; i < MainActivity.getPillObjectArray().size(); i++) {
            arrayListAdderName = (MainActivity.getPillObjectArray().get(i).getPillName());
            arrayListAdderAmount =(Double.toString(MainActivity.getPillObjectArray().get(i).getPillAmount()));
            arrayListAdderDosage =(Double.toString(MainActivity.getPillObjectArray().get(i).getPillDosage()));
            arrayListAdderDaysList = (MainActivity.getPillObjectArray().get(i).getDaysForPill());
            for(int x = 0; x < arrayListAdderDaysList.size(); x++) {
                arrayListAdderDays = sb.append(" ").append(arrayListAdderDaysList.get(x)).append(".").toString();
            }

            System.out.println("VIEW ACTIVITY...");
            System.out.println(MainActivity.getPillObjectArray().get(i).getPillName());
            System.out.println(MainActivity.getPillObjectArray().get(i).getPillAmount());


            arrayListAdderFinal = sb2.append(arrayListAdderName).append("\n Amount: ").append(arrayListAdderAmount).append("\n Dosage: ").append(arrayListAdderDosage).append("\n Days in the week to take PiLL:   \n").append(arrayListAdderDays).toString();
            arrayList.add(arrayListAdderFinal);
            sb = new StringBuilder();
            sb2 = new StringBuilder();

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter((arrayAdapter));
    }

    public void editPills(View view){
        openAddPillsActivity();
    }

    public void openAddPillsActivity() {

        Intent intent = new Intent(this, AddPillActivity.class);
        startActivity(intent);

    }
}
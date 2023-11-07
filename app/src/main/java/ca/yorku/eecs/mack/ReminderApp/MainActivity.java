package ca.yorku.eecs.mack.ReminderApp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.yorku.eecs.mack.ReminderApp.Adapter.RecyclerViewAdapter;
import ca.yorku.eecs.mack.ReminderApp.Model.Pill;

public class MainActivity extends AppCompatActivity {
    private final static String MYDEBUG = "MYDEBUG"; // for Log.i messages
    private final static String MEDICINE_LIST_ALL = "MEDICINE_LIST_ALL";
    private final static String MEDICINE_LIST_TODAY = "MEDICINE_LIST_TODAY";

    static Context context;
    public static RecyclerView recyclerView;
    public static List<Pill> todayList = new ArrayList<>();
    public static RecyclerViewAdapter recyclerViewAdapter;
    public static SwipeController swipeController = null;

    ImageButton calendarButton, viewPillsButton, addPillsButton, settingsButton;

    private static ArrayList<Pill> pillObjectArray = new ArrayList<Pill>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        // Dashboard current day medicine view
        setupRecyclerView();

        // Dashboard calendar button
        calendarButton = (ImageButton) findViewById(R.id.calendarBtn);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendarActivity();
            }
        });

        // Dashboard view all pills button
        viewPillsButton = (ImageButton) findViewById(R.id.viewPillsBtn);
        viewPillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewPillsActivity();
            }
        });

        // Dashboard add pills button
        addPillsButton = (ImageButton) findViewById(R.id.addPillBtn);
        addPillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddPillsActivity();
            }
        });

        // Dashboard settings button
        settingsButton = (ImageButton) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Not Implemented!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // SAVE STATE ----------------------------------------------------------------------------------
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(MYDEBUG, "SAVING STATE..");

        outState.putParcelableArrayList(MEDICINE_LIST_ALL, (ArrayList<? extends Parcelable>) getPillObjectArray());
        outState.putParcelableArrayList(MEDICINE_LIST_TODAY, (ArrayList<? extends Parcelable>) todayList);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i(MYDEBUG, "RESTORING STATE...");

        ArrayList<Pill> all = savedInstanceState.getParcelableArrayList(MEDICINE_LIST_ALL);
        todayList = savedInstanceState.getParcelableArrayList(MEDICINE_LIST_TODAY);

        setPillObjectArray(all);

        recyclerViewAdapter = new RecyclerViewAdapter(this, todayList);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    // ON CREATE SET UP ----------------------------------------------------------------------------
    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.todayRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (Pill pill: pillObjectArray) {

            List<String> daysForPill = pill.getDaysForPill();
            String todayDayOfWeek = LocalDate.now().getDayOfWeek().toString().toLowerCase();
            for (String day: daysForPill) {
                System.out.println(day.toLowerCase());
                System.out.println(todayDayOfWeek.toLowerCase());


                if (day.toLowerCase().equals(todayDayOfWeek) )
                    if (todayList.contains(pill)) {
                        // don't add
                    }
                    else {
                        if (pill.getLastDateTaken() == null || pill.getLastDateTaken().toString().equals(LocalDate.now().toString())) {
                            todayList.add(pill);
                            System.out.println("ADDING TO TODAY LIST: " + pill.getPillName());
                        }
                    }}
        }
        recyclerViewAdapter = new RecyclerViewAdapter(this, todayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        swipeController = new SwipeController(new SwipeControllerActions() {
            /*
             * Handle SKIP button clicked >> removes from today list and updates recycler view
             */
            @Override
            public void onRightClicked(int position) {
                MainActivity.todayList.get(position).setLastDateTaken(LocalDate.now());
                recyclerViewAdapter.listToday.remove(position);
                recyclerViewAdapter.notifyItemRemoved(position);
                recyclerViewAdapter.notifyItemRangeChanged(position, recyclerViewAdapter.getItemCount());
            }

            /*
             * Handle DONE button clicked >> remove from today list and update recycler view
             */
            @Override
            public void onLeftClicked(int position) {
                MainActivity.todayList.get(position).setLastDateTaken(LocalDate.now());
                recyclerViewAdapter.listToday.remove(position);
                recyclerViewAdapter.notifyItemRemoved(position);
                recyclerViewAdapter.notifyItemRangeChanged(position, recyclerViewAdapter.getItemCount());
            }

        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);


        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c, context);
            }
        });

    }

    public static void updateRecyclerView() {
        recyclerViewAdapter = new RecyclerViewAdapter(context, todayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    // OPEN NEW ACTIVITIES -------------------------------------------------------------------------
    public void openCalendarActivity() {

        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);

    }

    public void openViewPillsActivity() {

        Intent intent = new Intent(this, ViewPillsActivity.class);
        startActivity(intent);

    }

    public void openAddPillsActivity() {

        Intent intent = new Intent(this, AddPillActivity.class);
        //intent.putExtra("adapter", recyclerViewAdapter);
        startActivity(intent);

    }
    public static ArrayList<Pill> getPillObjectArray() {
        return pillObjectArray;
    }
    public static void setPillObjectArray(ArrayList<Pill> allPillList) {
        pillObjectArray = allPillList;
    }

    public static void addPillInObjectArray(Pill pill) {
        pillObjectArray.add(pill);
    }
}
package ca.yorku.eecs.mack.ReminderApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import ca.yorku.eecs.mack.ReminderApp.Model.Pill;

public class AddPillActivity extends AppCompatActivity {
    private static final int notificationID = 1;
    private static final String channelID = "channel1";
    NotificationManagerCompat notificationManager;
    int pillNumber = 0;
    boolean editChecker = false;

    EditText inputTextName;
    EditText inputTextAmount;
    EditText inputTextDosage;
    Button timeButton;
    CheckBox monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    Button addButton;

    int hour = 25;
    int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pill);

        inputTextName = (EditText) findViewById(R.id.pillName);
        inputTextAmount = (EditText) findViewById(R.id.pillAmount);
        inputTextDosage = (EditText) findViewById(R.id.pillDosage);
        monday = (CheckBox) findViewById(R.id.pillMonday);
        tuesday = (CheckBox) findViewById(R.id.pillTuesday);
        wednesday = (CheckBox) findViewById(R.id.pillWednesday);
        thursday = (CheckBox) findViewById(R.id.pillThursday);
        friday = (CheckBox) findViewById(R.id.pillFriday);
        saturday = (CheckBox) findViewById(R.id.pillSaturday);
        sunday = (CheckBox) findViewById(R.id.pillSunday);
        timeButton = findViewById(R.id.pillTime);

        addButton = (Button) findViewById(R.id.createPillButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPill();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelID, "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public void createPill() {

        pillNumber = pillNumber + 1;
        ArrayList<String> daysForPills = new ArrayList<String>();
        if (inputTextName.getText().toString().matches("") || inputTextName.getText() == null) {
            Toast.makeText(AddPillActivity.this, "Please enter a name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (inputTextAmount.getText().toString().matches("")) {
            Toast.makeText(AddPillActivity.this, "Please enter an amount!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (inputTextDosage.getText().toString().matches("")) {
            Toast.makeText(AddPillActivity.this, "Please enter a dosage!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (hour == 25) {
            Toast.makeText(AddPillActivity.this, "Please select a time!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean dayIsChecked = false;
        if (monday.isChecked()) {
            daysForPills.add("monday");
            dayIsChecked = true;
        }
        if (tuesday.isChecked()) {
            daysForPills.add("tuesday");
            dayIsChecked = true;
        }
        if (wednesday.isChecked()) {
            daysForPills.add("wednesday");
            dayIsChecked = true;
        }
        if (thursday.isChecked()) {
            daysForPills.add("thursday");
            dayIsChecked = true;
        }
        if (friday.isChecked()) {
            daysForPills.add("friday");
            dayIsChecked = true;
        }
        if (saturday.isChecked()) {
            daysForPills.add("saturday");
            dayIsChecked = true;
        }
        if (sunday.isChecked()) {
            daysForPills.add("sunday");
            dayIsChecked = true;
        }
        if (!dayIsChecked) {
            Toast.makeText(AddPillActivity.this, "Please select one or more days!", Toast.LENGTH_SHORT).show();
            return;
        }

        String pillName = inputTextName.getText().toString();
        double pillAmount = Double.parseDouble(inputTextAmount.getText().toString());
        double pillDosage = Double.parseDouble(inputTextDosage.getText().toString());

        for (int i = 0; i < MainActivity.getPillObjectArray().size(); i++) {
            System.out.println("ADD ACTIVITY...");
            System.out.println(MainActivity.getPillObjectArray().get(i).getPillName());
            System.out.println(pillName);

            System.out.println(MainActivity.getPillObjectArray().get(i).getPillAmount());
            System.out.println(pillAmount);

            if (MainActivity.getPillObjectArray().get(i).getPillName().equals(pillName)) {
                System.out.println("PILL NAME IS THE SAME. SETTING TRUE...");
                editChecker = true;
            }
        }
        if (editChecker) {
            for (int i = 0; i < MainActivity.getPillObjectArray().size(); i++) {
                if (MainActivity.getPillObjectArray().get(i).getPillName().equals(pillName)) {
                    System.out.println("PILL NAME IS THE SAME. UPDATING INFO...");
                    MainActivity.getPillObjectArray().get(i).setPillAmount(pillAmount);
                    MainActivity.getPillObjectArray().get(i).setPillDosage(pillDosage);
                    MainActivity.getPillObjectArray().get(i).setPillHour(hour);
                    MainActivity.getPillObjectArray().get(i).setPillMinute(minute);
                    MainActivity.getPillObjectArray().get(i).setDaysForPill(daysForPills);
                }
            }
        } else {
            System.out.println("ADDING NEW PILL TO MAIN ARRAY LIST...");
            Pill newPill = new Pill(pillName, pillAmount, pillDosage, hour, minute, daysForPills);
            MainActivity.addPillInObjectArray(newPill);
        }
        triggerNotification(pillName);
        openViewPillsActivity();
    }

    private void triggerNotification(String pillName) {
        String title = "Time to take your pills";
        String message = "Did you take " + pillName + "?";

        Intent emptyIntent = new Intent();
        Context temp = this;

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, PendingIntent.FLAG_IMMUTABLE);

        // Create an Intent for the first option
        Intent doneIntent = new Intent(this, DoneNotification.class);
        doneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent doneActionIntent = PendingIntent.getBroadcast(this, 0, doneIntent, PendingIntent.FLAG_IMMUTABLE);

        // Create an Intent for the second option
        Intent intent2 = new Intent(this, SkipNotification.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent skipActionIntent = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.pills_solid)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Done", doneActionIntent)
                .addAction(R.mipmap.ic_launcher, "Skip", skipActionIntent)
                .setAutoCancel(true);

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        //temp
        //if (ActivityCompat.checkSelfPermission(temp, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
        //    return;
        //}
        //notificationManager.notify(notificationID, builder.build());

        // Schedule a delayed notification
        long delayMillis = 2 * 60 * 1000; // 2 minutes
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ActivityCompat.checkSelfPermission(temp, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                notificationManager.notify(notificationID, builder.build());
            }
        }, delayMillis);
    }

    public void selectTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                String hourString = Integer.toString(hour);
                String minuteString = Integer.toString(minute);
                timeButton.setText(hourString + ":" + minuteString);
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    public void openViewPillsActivity() {

        Intent intent = new Intent(this, ViewPillsActivity.class);
        startActivity(intent);

    }

    // SEND NOTIFICATION ---------------------------------------------------------------------------
    public void sendNotification(String pillName, int hourOfDay, int minute, String dayOfWeek) {
        // Create a Calendar object with the desired notification time and day of week
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, getDayOfWeekFromString(dayOfWeek));

        NotificationChannel channel = new NotificationChannel(channelID, "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        // Create an intent for the notification
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent broadcastIntent_done = new Intent(this, DoneNotification.class);
        Intent broadcastIntent_skip = new Intent(this, SkipNotification.class);
        PendingIntent doneActionIntent = PendingIntent.getBroadcast(this,
                11, broadcastIntent_done, PendingIntent.FLAG_IMMUTABLE);
        PendingIntent skipActionIntent = PendingIntent.getBroadcast(this,
                12, broadcastIntent_skip, PendingIntent.FLAG_IMMUTABLE);

        String notification_title = "Medicine Reminder";
        String notification_reminder_text = "Did you consume " + pillName + "?";
        Notification notification = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.medicine_logo)
                .setContentTitle(notification_title)
                .setContentText(notification_reminder_text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Done", doneActionIntent)
                .addAction(R.mipmap.ic_launcher, "Skip", skipActionIntent)
                .build();

        // Schedule the notification to show at the specified time and day of week
        Intent notificationIntent = new Intent(this, NotificationReceiver.class);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION_ID, notificationID);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notificationID, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
    }

    private static int getDayOfWeekFromString(String dayOfWeek) {
        switch (dayOfWeek.toLowerCase()) {
            case "sunday":
                return Calendar.SUNDAY;
            case "monday":
                return Calendar.MONDAY;
            case "tuesday":
                return Calendar.TUESDAY;
            case "wednesday":
                return Calendar.WEDNESDAY;
            case "thursday":
                return Calendar.THURSDAY;
            case "friday":
                return Calendar.FRIDAY;
            case "saturday":
                return Calendar.SATURDAY;
            default:
                throw new IllegalArgumentException("Invalid day of week: " + dayOfWeek);
        }
    }

    //----------------------------------------------------------------------------------------------


}
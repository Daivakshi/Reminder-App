package ca.yorku.eecs.mack.ReminderApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

/**
 *   References:
 *
 *   Android Developers CalendarView Docs
 *   https://developer.android.com/reference/android/widget/CalendarView
 *
 *   Creating a CalendarView
 *   https://www.geeksforgeeks.org/android-creating-a-calendar-view-app/
 *   (Used to create a CalendarView to display a Calendar)
 *
 *   Adding CalendarView Events
 *   https://www.geeksforgeeks.org/how-to-set-calendar-event-in-android/
 *   (Used to add Calendar events)
 *
 *   Demo_Android Comments
 *   https://www.eecs.yorku.ca/course_archive/2021-22/W/4443/Labs/Lab1/lab-1.html
 *   (Used as references for method comments)
 *
 */

public class CalendarActivity extends AppCompatActivity {

    // Define the variables for the CalendarView and TextView
    
    CalendarView calendar;
    TextView dateTextView;
    
    // Called when the activity is first created
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Get references to Calendar and Text View from the Layout Manager
        calendar = (CalendarView) findViewById(R.id.calendarView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        
        
    }
}

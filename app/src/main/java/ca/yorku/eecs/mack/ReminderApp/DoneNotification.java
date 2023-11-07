package ca.yorku.eecs.mack.ReminderApp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.View;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;

public class DoneNotification extends BroadcastReceiver {

    /**
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("DONE NOTIFICATION RECEIVER...");
        // do action

        if (MainActivity.todayList.size() != 0) {
            //MainActivity main = new MainActivity();
            //View temp = R.layout.single_pill_item_today;
            int position = 0;//MainActivity.recyclerView.getChildAdapterPosition(temp.findViewById(R.id.pill_name));
            //MainActivity.recyclerViewAdapter.getItemCount()
            MainActivity.todayList.get(position).setLastDateTaken(LocalDate.now());
            MainActivity.recyclerViewAdapter.listToday.remove(position);
            MainActivity.recyclerViewAdapter.notifyItemRemoved(position);
            MainActivity.recyclerViewAdapter.notifyItemRangeChanged(position, MainActivity.recyclerViewAdapter.getItemCount());

            MainActivity.updateRecyclerView();

        }

    }

}

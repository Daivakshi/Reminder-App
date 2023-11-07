package ca.yorku.eecs.mack.ReminderApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

import ca.yorku.eecs.mack.ReminderApp.Model.Pill;
import ca.yorku.eecs.mack.ReminderApp.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter< RecyclerViewAdapter.MyViewHolder> {

    Context context;

    public List<Pill> listToday;

    public RecyclerViewAdapter(Context context, List<Pill> listToday) {
        this.context = context;
        this.listToday = listToday;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_pill_item_today, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        String pillTextPrefix = "Take " + listToday.get(position).getPillAmount() + " or " + listToday.get(position).getPillDosage() + "mg of ";
        holder.pillName.setText(pillTextPrefix + listToday.get(position).getPillName());

        DateTimeFormatter formatterTime1 = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern("hh:mma").toFormatter(Locale.US);
        LocalTime time = LocalTime.of(listToday.get(position).getPillHour(), listToday.get(position).getPillMinute());
        holder.pillTime.setText(time.format(formatterTime1));
    }

    @Override
    public int getItemCount() {
        return listToday.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pillName;
        TextView pillTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pillName = itemView.findViewById(R.id.pill_name);
            pillTime = itemView.findViewById(R.id.pill_time);
        }
    }
}

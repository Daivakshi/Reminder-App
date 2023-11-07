package ca.yorku.eecs.mack.ReminderApp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Pill implements Parcelable {

    private LocalDate lastDateTaken;

    private String pillName;
    private double pillAmount;
    private double pillDosage;
    private int pillHour;
    private int pillMinute;
    private ArrayList<String> daysForPill = new ArrayList<>();

    public Pill(String pillName, double amount, double dosage, int hour, int minute, ArrayList<String> days) {
        this.pillName = pillName;
        this.pillAmount = amount;
        this.pillDosage = dosage;
        this.pillHour = hour;
        this.pillMinute = minute;
        for(int i = 0; i < days.size(); i++) {
            daysForPill.add(days.get(i));
        }
    }

    public ArrayList<String> getDaysForPill() {
        return daysForPill;
    }

    public void addDaysForPill(String newDay) {
        this.daysForPill.add(newDay);
    }

    public void setDaysForPill(ArrayList<String> daysForPill) {
        this.daysForPill = daysForPill;
    }
    public int getPillMinute() {
        return pillMinute;
    }

    public void setPillMinute(int pillMinute) {
        this.pillMinute = pillMinute;
    }
    public int getPillHour() {
        return pillHour;
    }

    public void setPillHour(int pillHour) {
        this.pillHour = pillHour;
    }
    public double getPillDosage() {
        return pillDosage;
    }

    public void setPillDosage(double pillDosage) {
        this.pillDosage = pillDosage;
    }

    public double getPillAmount() {
        return pillAmount;
    }
    public void setPillAmount(double pillAmount) {
        this.pillAmount = pillAmount;
    }

    public LocalDate getLastDateTaken() {
        return lastDateTaken;
    }

    public void setLastDateTaken(LocalDate lastDateTaken) {
        this.lastDateTaken = lastDateTaken;
    }
    protected Pill(Parcel in) {
        pillName = in.readString();
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public static final Creator<Pill> CREATOR = new Creator<Pill>() {
        @Override
        public Pill createFromParcel(Parcel in) {
            return new Pill(in);
        }

        @Override
        public Pill[] newArray(int size) {
            return new Pill[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(pillName);
    }
}

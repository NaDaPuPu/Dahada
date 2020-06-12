package com.d2w.dahada.data.activity_main;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.d2w.dahada.R;
import com.d2w.dahada.data.activity_main.fragment_calendar.EventDecorator;
import com.d2w.dahada.data.activity_main.fragment_calendar.OneDayDecorator;
import com.d2w.dahada.data.activity_main.fragment_calendar.SaturdayDecorator;
import com.d2w.dahada.data.activity_main.fragment_calendar.Schedule;
import com.d2w.dahada.data.activity_main.fragment_calendar.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

public class CalendarFragment extends Fragment {
    View v;
    private String shot_Day;
    private Date currentDate;
    private CalendarDay beforeSelectedDate;
    MaterialCalendarView materialCalendarView;
    ConstraintLayout inputContainer, outputContainer;
    EditText kcalText, menuText;
    TextView kcalText2, menuText2, dateText, waterText, waterText2;
    Button buttonInput, buttonCancel, buttonEdit;
    SeekBar seekBar;

    ArrayList<Schedule> scheduleList = new ArrayList<>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_calendar, container, false);
        // 컨테이너
        inputContainer = v.findViewById(R.id.inputContainer);
        outputContainer = v.findViewById(R.id.outputContainer);

        // 텍스트
        kcalText = v.findViewById(R.id.kcal);
        kcalText2 = v.findViewById(R.id.kcal2);
        menuText = v.findViewById(R.id.menu);
        menuText2 = v.findViewById(R.id.menu2);
        dateText = v.findViewById(R.id.date);
        waterText = v.findViewById(R.id.water);
        waterText2 = v.findViewById(R.id.water2);

        // Seekbar
        seekBar = v.findViewById(R.id.seekBar);

        // 버튼
        buttonInput = v.findViewById(R.id.buttonInput);
        buttonCancel = v.findViewById(R.id.buttonCancel);
        buttonEdit = v.findViewById(R.id.buttonEdit);

        materialCalendarView = v.findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getContext().getFilesDir() + "savedCalendar"));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                String[] readedContent = line.split(" ");
                Date rdate = simpleDateFormat.parse(readedContent[0]);
                int rkcal = Integer.parseInt(readedContent[1]);
                String rmenu = readedContent[2];
                int rwater = Integer.parseInt(readedContent[3]);

                Schedule schedule = new Schedule(rdate, rkcal, rmenu, rwater);
                scheduleList.add(schedule);
            }
            bufferedReader.close();

            String[] result = new String[scheduleList.size()];
            for (int i = 0; i < scheduleList.size(); i++) {
                result[i] = simpleDateFormat.format(scheduleList.get(i).getDate());
            }

            ArrayList<String> w_result = new ArrayList<>();
            for (int i = 0; i < scheduleList.size(); i++) {
                if (scheduleList.get(i).getWater() >= 20) {
                    w_result.add(simpleDateFormat.format(scheduleList.get(i).getDate()));
                }
            }

            new ApiSimulator(result, w_result).executeOnExecutor(Executors.newSingleThreadExecutor());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            // 날짜 선택 시
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if (inputContainer.getVisibility() == View.VISIBLE) {
                    materialCalendarView.setSelectedDate(beforeSelectedDate);
                } else {
                    materialCalendarView.setSelectionColor(getResources().getColor(R.color.colorPrimary));
                    boolean ifEquals = false;

                    int Year = date.getYear();
                    int Month = date.getMonth() + 1;
                    int Day = date.getDay();

                    currentDate = date.getDate();
                    shot_Day = Year + "." + Month + "." + Day;

                    dateText.setText("date : " + shot_Day);

                    for (int i = 0; i < scheduleList.size(); i++) {
                        if (simpleDateFormat.format(date.getDate()).equals(simpleDateFormat.format(scheduleList.get(i).getDate()))) {
                            kcalText2.setText("kcal : " + scheduleList.get(i).getKcal());
                            menuText2.setText("menu : " + scheduleList.get(i).getMenu());
                            waterText2.setText("water : " + scheduleList.get(i).getWater() / 10.0 + "L");
                            ifEquals = true;
                        }
                    }

                    if (!ifEquals) {
                        kcalText2.setText("kcal : ");
                        menuText2.setText("menu : ");
                        waterText2.setText("water : ");
                    }
                    
                    beforeSelectedDate = date;
                }
            }
        });

        buttonInput.setOnClickListener(new View.OnClickListener() { // 입력 버튼
            @Override
            public void onClick(View v) {
                String content = "";
                try {
                    File directory = getActivity().getFilesDir();
                    File file = new File(directory, "savedCalendar");
                    FileWriter fileWriter = new FileWriter(getActivity().getFilesDir() + "savedCalendar", false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    Schedule schedule = new Schedule(simpleDateFormat.parse(shot_Day), Integer.parseInt(kcalText.getText().toString()), menuText.getText().toString(), seekBar.getProgress());
                    int currentsize = scheduleList.size();
                    boolean isChanged = false;

                    if (currentsize == 0) {
                        scheduleList.add(schedule);
                        isChanged = true;
                    }

                    for (int i = 0; i < currentsize; i++) {
                        if (simpleDateFormat.format(scheduleList.get(i).getDate()).equals(simpleDateFormat.format(schedule.getDate()))) {
                            scheduleList.set(i, schedule);
                            isChanged = true;
                        } else if (i == currentsize - 1 && !isChanged) {
                            scheduleList.add(schedule);
                        }
                    }

                    for (int i = 0; i < scheduleList.size(); i++) {
                        content += simpleDateFormat.format(scheduleList.get(i).getDate()) + " " + scheduleList.get(i).getKcal() + " " + scheduleList.get(i).getMenu() + " " + scheduleList.get(i).getWater() + "\n";
                    }
                    bufferedWriter.write(content);
                    bufferedWriter.close();

                    ArrayList<CalendarDay> CalendarDays = new ArrayList<>();
                    CalendarDays.add(CalendarDay.from(currentDate));

                    if (seekBar.getProgress() >= 20) {
                        materialCalendarView.addDecorator(new EventDecorator(Color.BLUE, CalendarDays, getActivity()));
                    } else {
                        materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, CalendarDays, getActivity()));
                    }

                    kcalText2.setText("kcal : " + kcalText.getText());
                    menuText2.setText("menu :" + menuText.getText());
                    waterText2.setText("water : " + (float) seekBar.getProgress() / 10 + "L");
                    kcalText.setText("");
                    menuText.setText("");
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }

                inputContainer.setVisibility(View.GONE);
                outputContainer.setVisibility(View.VISIBLE);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputContainer.setVisibility(View.GONE);
                outputContainer.setVisibility(View.VISIBLE);
                kcalText.setText("");
                menuText.setText("");
                seekBar.setProgress(0);
                waterText.setText("이 날 마신 물 : 0.0L");
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputContainer.setVisibility(View.VISIBLE);
                outputContainer.setVisibility(View.GONE);

                if (!kcalText2.getText().toString().equals("kcal : ")) {
                    for (int i = 0; i < scheduleList.size(); i++) {
                        String sdate = simpleDateFormat.format(scheduleList.get(i).getDate());
                        if (simpleDateFormat.format(currentDate).equals(sdate)) {
                            kcalText.setText(scheduleList.get(i).getKcal() + "");
                            menuText.setText(scheduleList.get(i).getMenu() + "");
                            waterText.setText("이 날 마신 물 : " + scheduleList.get(i).getWater() / 10.0 + "L");
                        }
                    }
                } else {
                    kcalText.setText("");
                    menuText.setText("");
                    waterText.setText("이 날 마신 물 : 0.0L");
                    seekBar.setProgress(0);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float water = (float) progress / 10;
                waterText.setText("이 날 마신 물 : " + water + "L");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return v;
    }

    private class L_CalendarDay {
        ArrayList<CalendarDay> Time_Result;
        ArrayList<CalendarDay> Water_Result;

        L_CalendarDay(ArrayList<CalendarDay> time_Result, ArrayList<CalendarDay> water_Result) {
            this.Time_Result = time_Result;
            this.Water_Result = water_Result;
        }

        public ArrayList<CalendarDay> getTime_Result() {
            return Time_Result;
        }

        public void setTime_Result(ArrayList<CalendarDay> time_Result) {
            Time_Result = time_Result;
        }

        public ArrayList<CalendarDay> getWater_Result() {
            return Water_Result;
        }

        public void setWater_Result(ArrayList<CalendarDay> water_Result) {
            Water_Result = water_Result;
        }
    }

    private class ApiSimulator extends AsyncTask<Void, Void, L_CalendarDay> {
        String[] Time_Result;
        ArrayList<String> Water_Result;

        ApiSimulator(String[] Time_Result, ArrayList<String> Water_Result) {
            this.Time_Result = Time_Result;
            this.Water_Result = Water_Result;
        }

        @Override
        protected L_CalendarDay doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();
            ArrayList<CalendarDay> wdates = new ArrayList<>();

            for (int i = 0; i < Time_Result.length; i++) {

                String[] time = Time_Result[i].split("\\."); // "."으로 하면 X
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day = Integer.parseInt(time[2]);

                calendar.set(year, month - 1, day);
                CalendarDay calendarDay = CalendarDay.from(calendar);
                dates.add(calendarDay);
            }

            for (int i = 0; i < Water_Result.size(); i++) {

                String[] time = Water_Result.get(i).split("\\."); // "."으로 하면 X
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day = Integer.parseInt(time[2]);

                calendar.set(year, month - 1, day);
                CalendarDay calendarDay = CalendarDay.from(calendar);
                wdates.add(calendarDay);
            }
            L_CalendarDay l_calendarDay = new L_CalendarDay(dates, wdates);

            return l_calendarDay;
        }

        @Override
        protected void onPostExecute(L_CalendarDay l_calendarDay) {
            super.onPostExecute(l_calendarDay);

            if (isRemoving()) {
                return;
            }
            materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, l_calendarDay.Time_Result, getActivity()));
            materialCalendarView.addDecorator(new EventDecorator(Color.BLUE, l_calendarDay.Water_Result, getActivity()));
        }
    }
}
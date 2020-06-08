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
    MaterialCalendarView materialCalendarView;
    ConstraintLayout inputContainer, outputContainer;
    EditText kcalText;
    TextView kcalText2, dateText;
    Button buttonInput, buttonCancel, buttonEdit;

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
        dateText = v.findViewById(R.id.date);

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
            Log.d("BufferedReader", "나 실행됐어요");

            while ((line = bufferedReader.readLine()) != null) {
                String[] readedContent = line.split(" ");
                String rdate = readedContent[0];
                String rkcal = readedContent[1];
                Log.d("doInBackground", rdate + " " + rkcal);

                Schedule schedule = new Schedule(simpleDateFormat.parse(rdate), Integer.parseInt(rkcal));
                scheduleList.add(schedule);
            }
            bufferedReader.close();

            String[] result = new String[scheduleList.size()];
            for (int i = 0; i < scheduleList.size(); i++) {
                result[i] = simpleDateFormat.format(scheduleList.get(i).getDate());
                Log.d("result", result[i]);
            }

            new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

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
                materialCalendarView.setSelectionColor(getResources().getColor(R.color.colorPrimary));
                boolean ifEquals = false;

                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                shot_Day = Year + "." + Month + "." + Day;

                dateText.setText("date : " + shot_Day);

                for (int i = 0; i < scheduleList.size(); i++) {
                    if (simpleDateFormat.format(date.getDate()).equals(simpleDateFormat.format(scheduleList.get(i).getDate()))) {
                        kcalText2.setText("kcal : " + scheduleList.get(i).getKcal() + "kcal");
                        ifEquals = true;
                    }
                }

                if (!ifEquals) {
                    kcalText2.setText("kcal : ");
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
                    Schedule schedule = new Schedule(simpleDateFormat.parse(shot_Day), Integer.parseInt(kcalText.getText().toString()));
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
                        content += simpleDateFormat.format(scheduleList.get(i).getDate()) + " " + scheduleList.get(i) .getKcal() + "\n";
                    }
                    bufferedWriter.write(content);
                    bufferedWriter.close();

                    String[] result = new String[scheduleList.size()];
                    for (int i = 0; i < scheduleList.size(); i++) {
                        result[i] = simpleDateFormat.format(scheduleList.get(i).getDate());
                    }

                    new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

                    kcalText2.setText("kcal : " + kcalText.getText());
                    kcalText.setText("");
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
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputContainer.setVisibility(View.VISIBLE);
                outputContainer.setVisibility(View.GONE);
            }
        });

        return v;
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
        String[] Time_Result;

        ApiSimulator(String[] Time_Result) {
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            for (int i = 0; i < Time_Result.length; i++) {
                Log.d("time", Time_Result[i]);

                String[] time = Time_Result[i].split("\\."); // "."으로 하면 X
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day = Integer.parseInt(time[2]);

                calendar.set(year, month - 1, day);
                CalendarDay calendarDay = CalendarDay.from(calendar);
                dates.add(calendarDay);
            }
            return dates;
        }

        @Override
        protected void onPostExecute(List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isRemoving()) {
                return;
            }
            materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDays, getActivity()));
        }
    }
}
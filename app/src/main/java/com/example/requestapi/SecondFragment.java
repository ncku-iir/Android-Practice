package com.example.requestapi;


import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.requestapi.model.Weather;
import com.example.requestapi.retrofit.WeatherServerClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondFragment extends Fragment {
    // for ui
    TextView location;
    TextView currentTemp;
    TextView todayTextView;
    TextView date1, date2, date3, date4, date5, date6 ;
    ImageView cond1, cond2, cond3, cond4, cond5, cond6;
    TextView temp1, temp2, temp3, temp4, temp5, temp6;
    ImageButton backBtn;

    //weather condition
    private int[] cond_drawable_id = {R.drawable.cond_sun, R.drawable.cond_suncloud, R.drawable.cond_cloud, R.drawable.cond_rain, R.drawable.cond_thunderrain, R.drawable.cond_wind};
    //各種天氣對應的編號
    Set<String> sun = new HashSet<>(Arrays.asList(new String[]{"01", "07", "08", "43", "46"}));
    Set<String> sun_cloud = new HashSet<>(Arrays.asList(new String[]{"02", "05", "45"}));
    Set<String> cloud = new HashSet<>(Arrays.asList(new String[]{"03", "06", "44", "59", "61"}));
    Set<String> rain = new HashSet<>(Arrays.asList(new String[]{"04", "12", "13", "24", "26", "31", "49", "57", "60"}));
    Set<String> thunder_rain = new HashSet<>(Arrays.asList(new String[]{"17", "18", "29", "34", "36", "58"})); //風暴, 雷陣雨

    private List<Set<String>> forecastCondList = Arrays.asList(sun, sun_cloud, cloud, rain, thunder_rain);

    // get from bundle
    private String city;


    public SecondFragment() {
        // Required empty public constructor
    }

    //for accepting bundle
    public static SecondFragment newInstance(Bundle args){
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = getArguments().getString("city");
            Log.d("SecondFragment", city);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        location = view.findViewById(R.id.location);
        todayTextView  = view.findViewById(R.id.todayTextView);
        backBtn = view.findViewById(R.id.home_Button);

        Calendar rightNow = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String todayDateStr = dateFormat.format(rightNow.getTime());

        location.setText(city);
        todayTextView.setText(todayDateStr);

        backBtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, new FirstFragment()).commit();
            }
        });


        getCityWeather(view, city);



        return view;
    }

    private void getCityWeather(View view, String city){
        Log.d("SecondFragment", "create request: " + city);
        final View framentView = view;

        Retrofit r = new Retrofit.Builder()
                .baseUrl(WeatherServerClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        r.create(WeatherServerClient.class).getWeatherInfo(city).enqueue(
                new Callback<ArrayList<Weather>>(){
                    @Override
                    public void onResponse(Call<ArrayList<Weather>> call, Response<ArrayList<Weather>> response) {
                        if (response.body() != null){
                            Log.d("SecondFragment", "body " + String.valueOf(response.body().get(0).getWeatherTime()));
                            try {
                                setWeather(framentView, response.body());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Weather>> call, Throwable t) {
                        new AlertDialog.Builder(getContext()).setMessage("今天天氣預測休息哦").show();
                        Log.d("SecondFragment", String.valueOf(t));
                    }
                }
        );
    }

    private void setWeather(View view, ArrayList<Weather> weathers) throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd");


        for(int i=0; i<weathers.size(); i++){
            // today
            if(i == 0){
                currentTemp = view.findViewById(R.id.temp);
                currentTemp.setText(weathers.get(i).getTemparature());

            }
            else{
                // others
                rightNow.add(Calendar.DAY_OF_YEAR, 1);
                String newDate = dateFormat.format(rightNow.getTime());
                String cond = weathers.get(i).getWeather();

                switch (i) {
                    case 1:
                        date1 = view.findViewById(R.id.dayTextView1);
                        date1.setText(newDate);
                        cond1 = view.findViewById(R.id.condImageView1);
                        cond1.setImageResource(getForecastIconId(cond));
                        temp1 = view.findViewById(R.id.tempTextView1);
                        temp1.setText(weathers.get(i).getTemparature());
                    case 2:
                        date2 = view.findViewById(R.id.dayTextView2);
                        date2.setText(newDate);
                        cond2 = view.findViewById(R.id.condImageView2);
                        cond2.setImageResource(getForecastIconId(cond));
                        temp2 = view.findViewById(R.id.tempTextView2);
                        temp2.setText(weathers.get(i).getTemparature());
                    case 3:
                        date3 = view.findViewById(R.id.dayTextView3);
                        date3.setText(newDate);
                        cond3 = view.findViewById(R.id.condImageView3);
                        cond3.setImageResource(getForecastIconId(cond));
                        temp3 = view.findViewById(R.id.tempTextView3);
                        temp3.setText(weathers.get(i).getTemparature());
                    case 4:
                        date4 = view.findViewById(R.id.dayTextView4);
                        date4.setText(newDate);
                        cond4 = view.findViewById(R.id.condImageView4);
                        cond4.setImageResource(getForecastIconId(cond));
                        temp4 = view.findViewById(R.id.tempTextView4);
                        temp4.setText(weathers.get(i).getTemparature());
                    case 5:
                        date5 = view.findViewById(R.id.dayTextView5);
                        date5.setText(newDate);
                        cond5 = view.findViewById(R.id.condImageView5);
                        cond5.setImageResource(getForecastIconId(cond));
                        temp5 = view.findViewById(R.id.tempTextView5);
                        temp5.setText(weathers.get(i).getTemparature());
                    case 6:
                        date6 = view.findViewById(R.id.dayTextView6);
                        date6.setText(newDate);
                        cond6 = view.findViewById(R.id.condImageView6);
                        cond6.setImageResource(getForecastIconId(cond));
                        temp6 = view.findViewById(R.id.tempTextView6);
                        temp6.setText(weathers.get(i).getTemparature());
                }
            }
        }
    }

    private int getForecastIconId(String cond) {
        int drawable_id = cond_drawable_id[0];

        for (int c = 0; c < forecastCondList.size(); c++) {
            if (forecastCondList.get(c).contains(cond)) {
                drawable_id = cond_drawable_id[c];
                break;
            }
        }
        return drawable_id;
    }

}

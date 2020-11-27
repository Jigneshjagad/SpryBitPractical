package com.sprybit.practical.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sprybit.practical.R;
import com.sprybit.practical.activity.MainActivity;
import com.sprybit.practical.adapter.WeatherAdapter;
import com.sprybit.practical.databinding.FragmentQuestionOneBinding;
import com.sprybit.practical.model.Weather;
import com.sprybit.practical.retrofit.APIClient;
import com.sprybit.practical.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionOneFragment extends Fragment {
    private static final String TAG = QuestionOneFragment.class.getSimpleName();

    private FragmentQuestionOneBinding binding;
    private MainActivity mActivity;
    private APIInterface apiInterface;
    private List<Weather> weatherList = new ArrayList<>();
    private WeatherAdapter weatherAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuestionOneBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        mActivity = (MainActivity) getActivity();
        setToolBarView();
        weatherAdapter = new WeatherAdapter(mActivity);
        binding.rvWeather.setLayoutManager(new LinearLayoutManager(mActivity));
        binding.rvWeather.setAdapter(weatherAdapter);

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtQuery.getText().toString().isEmpty()) {
                    Toast.makeText(mActivity, "Please enter string!", Toast.LENGTH_SHORT).show();
                } else {
                    String q = binding.edtQuery.getText().toString().trim();
                    callApi(q);
                }
            }
        });
    }

    private void callApi(String q) {
        Call<Weather> weatherCall = apiInterface.search(q, "f7ae896d963f6d47ee09e3a70ee4ceb5");
        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Weather weather = new Weather();
                        weather = response.body();
                        if (weather != null) {
                            weatherList.add(weather);
                            weatherAdapter.setWeatherList(weatherList);
                        }
                    }
                } else {
                    Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                Toast.makeText(mActivity, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // set Toolbar View
    private void setToolBarView() {
        mActivity.setTitle("Question1");
//        mActivity.setHomeIndicatorIcon(R.drawable.ic_menu_24);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
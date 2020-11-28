package com.sprybit.practical.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sprybit.practical.R;
import com.sprybit.practical.activity.MainActivity;
import com.sprybit.practical.adapter.OrderAdapter;
import com.sprybit.practical.databinding.FragmentQuestionTwoBinding;
import com.sprybit.practical.db.FoodDatabase;
import com.sprybit.practical.db.FoodViewModel;
import com.sprybit.practical.db.table.Order;
import com.sprybit.practical.db.table.User;

import java.util.ArrayList;
import java.util.List;

public class QuestionTwoFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static final String TAG = QuestionTwoFragment.class.getSimpleName();
    private FragmentQuestionTwoBinding binding;
    private MainActivity mActivity;
    private FoodViewModel foodViewModel;
    private ArrayAdapter spinnerAdapter;
    private List<User> userList = new ArrayList<>();
    private List<String> userFilterList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private OrderAdapter orderAdapter;
    private int selectedUserId = -1;
    private int firstCall = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuestionTwoBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        mActivity = (MainActivity) getActivity();
        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);


        setToolBarView();
        initDataAndSetSpinner();
        initSetRecyclerView();

        binding.btnInsertRawData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.rvOrder.setVisibility(View.GONE);
                //insert data in database
                foodViewModel.insetData();

                //manage selection after create database
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        selectedUserId = -1;
                        binding.spinner.setSelection(0);
                        Toast.makeText(mActivity, "Your Data Create!", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedUserId != -1) {
                    //delete select user and order data
                    foodViewModel.deleteData(selectedUserId);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            selectedUserId = -1;
                            binding.spinner.setSelection(0);
                        }
                    }, 1000);
                } else {
                    Toast.makeText(mActivity, "Please select user!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initSetRecyclerView() {
        orderAdapter = new OrderAdapter();
        orderAdapter.setOrderList(orderList);
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        binding.rvOrder.setAdapter(orderAdapter);
    }

    private void initDataAndSetSpinner() {
        binding.spinner.setOnItemSelectedListener(this);
        spinnerAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_spinner_item, userFilterList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(spinnerAdapter);

        foodViewModel.getUserLiveData().observe(mActivity, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (firstCall == 1) {
                    userList = users;
                    userFilterList.add("Select User");
                    for (User user : userList) {
                        userFilterList.add(user.getName());
                    }
                    spinnerAdapter.notifyDataSetChanged();
                    firstCall = 2;
                    return;
                }
                //clear old list and update with new
                userList.clear();
                userFilterList.clear();
                spinnerAdapter.clear();
                userList = users;
                userFilterList.add("Select User");
                for (User user : userList) {
                    userFilterList.add(user.getName());
                }
                spinnerAdapter.notifyDataSetChanged();
            }
        });
    }

    // set Toolbar View
    private void setToolBarView() {
        mActivity.setTitle("Question2");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position != 0) {
            //get id of selected position
            selectedUserId = userList.get(position - 1).getUserId();
            updateOrderList(selectedUserId);
            binding.rvOrder.setVisibility(View.VISIBLE);
        } else {
            //position 0 = selected user
            selectedUserId = -1;
            orderList.clear();
            orderAdapter.setOrderList(orderList);
            binding.rvOrder.setVisibility(View.GONE);
        }
    }

    private void updateOrderList(int selectedUserId) {
        foodViewModel.getOrderLiveData(selectedUserId).observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                //set recyclerview data according spinner selection
                if (binding.spinner.getSelectedItemPosition() != 0) {
                    orderList = orders;
                    Log.e(TAG, "getDataAndSetSpinner: " + orderList.size());
                    orderAdapter.setOrderList(orderList);
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
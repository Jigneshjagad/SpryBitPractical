package com.sprybit.practical.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sprybit.practical.db.table.Order;
import com.sprybit.practical.db.table.User;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private FoodRepository foodRepository;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodRepository = new FoodRepository(application);
    }

    public void insetData() {
        foodRepository.insertData();
    }

    public void deleteData(int uid) {
        foodRepository.deleteData(uid);
    }

    public List<Order> getOrderList(int uid) {
        return foodRepository.getOrderList(uid);
    }

    public LiveData<List<Order>> getOrderLiveData(int uid) {
        return foodRepository.getOrderLiveData(uid);
    }

    public LiveData<List<User>> getUserLiveData() {
        return foodRepository.getUserLiveData();
    }
}

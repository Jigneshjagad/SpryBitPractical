package com.sprybit.practical.db;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.sprybit.practical.db.table.Order;
import com.sprybit.practical.db.table.User;

import java.util.ArrayList;
import java.util.List;

public class FoodRepository {
    public static final String TAG = FoodRepository.class.getSimpleName();
    private final AppDao appDao;

    public FoodRepository(Application application) {
        FoodDatabase foodDatabase = FoodDatabase.getDatabase(application);
        appDao = foodDatabase.appDao();
    }

    public List<Order> getOrderList(int uid) {
        return appDao.getOrderList(uid);
    }

    public LiveData<List<Order>> getOrderLiveData(int uid) {
        return appDao.getOrderLiveData(uid);
    }

    public LiveData<List<User>> getUserLiveData() {
        return appDao.getUserLiveData();
    }

    public void insertData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDao.deleteOrderTable();
                appDao.deleteUserTable();
                for (int i = 0; i < 3; i++) {
                    User user = new User();
                    user.setName("User " + i);
                    appDao.insertUser(user);
                    for (int j = 0; j < 3; j++) {
                        Order order = new Order(i, "Order " + j, 25 + j + i);
                        appDao.insertOrder(order);
                    }
                }
            }
        }).start();
    }

    public void deleteData(int uid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: " + uid);
                appDao.deleteAll(uid);
            }
        }).start();
    }
}

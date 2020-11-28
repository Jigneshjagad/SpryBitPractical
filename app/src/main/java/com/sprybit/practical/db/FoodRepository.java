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

    public LiveData<List<Order>> getOrderLiveData(int uid) {
        return appDao.getOrderLiveData(uid);
    }

    public LiveData<List<User>> getUserLiveData() {
        return appDao.getUserLiveData();
    }

    //remove old data from table and add new data
    public void insertData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDao.deleteOrderTable();
                appDao.deleteUserTable();
                insertRawData();
            }
        }).start();
    }

    private void insertRawData() {
        User user = new User();
        user.setUserId(1);
        user.setName("User 1 ");
        appDao.insertUser(user);
        user = new User();
        user.setUserId(2);
        user.setName("User 2 ");
        appDao.insertUser(user);
        user = new User();
        user.setUserId(3);
        user.setName("User 3 ");
        appDao.insertUser(user);
        user = new User();
        user.setUserId(4);
        user.setName("User 4 ");
        appDao.insertUser(user);

        Order order = new Order();
        order.setOrderId(1);
        order.setItemName("User 1 Item 1");
        order.setItemPrice(25);
        order.setUserId(1);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(2);
        order.setItemName("User 1 Item 2");
        order.setItemPrice(49);
        order.setUserId(1);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(3);
        order.setItemName("User 2 Item 1");
        order.setItemPrice(25);
        order.setUserId(2);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(4);
        order.setItemName("User 2 Item 2");
        order.setItemPrice(125);
        order.setUserId(2);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(5);
        order.setItemName("User 2 Item 3");
        order.setItemPrice(15);
        order.setUserId(2);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(6);
        order.setItemName("User 2 Item 4");
        order.setItemPrice(49);
        order.setUserId(2);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(7);
        order.setItemName("User 2 Item 5");
        order.setItemPrice(49);
        order.setUserId(2);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(8);
        order.setItemName("User 2 Item 3");
        order.setItemPrice(10);
        order.setUserId(2);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(9);
        order.setItemName("User 3 Item 1");
        order.setItemPrice(25);
        order.setUserId(3);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(10);
        order.setItemName("User 4 Item 1");
        order.setItemPrice(10);
        order.setUserId(4);
        appDao.insertOrder(order);

        order = new Order();
        order.setOrderId(11);
        order.setItemName("User 4 Item 2");
        order.setItemPrice(99);
        order.setUserId(4);
        appDao.insertOrder(order);


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

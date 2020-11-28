package com.sprybit.practical.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.sprybit.practical.db.table.Order;
import com.sprybit.practical.db.table.User;

import java.util.List;

@Dao
public abstract class AppDao {

    @Insert
    public abstract void insertUser(User user);

    @Insert
    public abstract void insertOrder(Order order);

    @Query("SELECT * From user_table")
    public abstract LiveData<List<User>> getUserLiveData();

    @Query("SELECT * From order_table WHERE userId LIKE :uid")
    public abstract LiveData<List<Order>> getOrderLiveData(int uid);

    @Query("DELETE From user_table WHERE userId LIKE :uid")
    public abstract void deleteUser(int uid);

    @Query("DELETE From order_table WHERE userId LIKE :uid")
    public abstract void deleteOrder(int uid);

    @Query("DELETE from user_table")
    public abstract void deleteUserTable();

    @Query("DELETE from order_table")
    public abstract void deleteOrderTable();

    @Transaction
    public void deleteAll(int uid) {
        // Anything inside this method runs in a single transaction.
        deleteOrder(uid);
        deleteUser(uid);
    }
}

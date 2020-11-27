package com.sprybit.practical.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sprybit.practical.db.table.Order;
import com.sprybit.practical.db.table.User;

@Database(entities = {User.class, Order.class}, version = 1, exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {

    abstract AppDao appDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile FoodDatabase INSTANCE;

    static FoodDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FoodDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FoodDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}

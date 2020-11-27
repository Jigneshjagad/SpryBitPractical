package com.sprybit.practical.db.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_table")
public class Order {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId")
    private int orderId;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "itemName")
    private String itemName;

    @ColumnInfo(name = "itemPrice")
    private int itemPrice;

    public Order(int userId, String itemName, int itemPrice) {
        this.userId = userId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}

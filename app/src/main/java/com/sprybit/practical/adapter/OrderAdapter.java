package com.sprybit.practical.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sprybit.practical.databinding.ItemFoodOrderBinding;
import com.sprybit.practical.db.table.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<Order> orderList = new ArrayList<>();


    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFoodOrderBinding binding = ItemFoodOrderBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.binding.txtName.setText(order.getItemName());
        holder.binding.txtPrice.setText("Rs. " + order.getItemPrice());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFoodOrderBinding binding;

        public ViewHolder(@NonNull ItemFoodOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }
}

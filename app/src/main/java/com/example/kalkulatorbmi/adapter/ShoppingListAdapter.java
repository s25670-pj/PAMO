package com.example.kalkulatorbmi.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalkulatorbmi.R;
import com.example.kalkulatorbmi.model.ShoppingItem;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemViewHolder> {

    private List<ShoppingItem> shoppingItems;
    private OnItemCheckListener onItemCheckListener;

    public interface OnItemCheckListener {
        void onItemCheck(int position, boolean isChecked);
    }

    public ShoppingListAdapter(List<ShoppingItem> shoppingItems, OnItemCheckListener listener) {
        this.shoppingItems = shoppingItems;
        this.onItemCheckListener = listener;
    }

    @NonNull
    @Override
    public ShoppingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false);
        return new ShoppingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingItemViewHolder holder, int position) {
        ShoppingItem item = shoppingItems.get(position);
        holder.tvItemName.setText(item.getName());
        holder.tvItemQuantity.setText(item.getQuantity());
        holder.cbPurchased.setChecked(item.isChecked());

        if (item.isChecked()) {
            holder.tvItemName.setPaintFlags(holder.tvItemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvItemQuantity.setPaintFlags(holder.tvItemQuantity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvItemName.setPaintFlags(holder.tvItemName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.tvItemQuantity.setPaintFlags(holder.tvItemQuantity.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        holder.cbPurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    boolean isChecked = holder.cbPurchased.isChecked();
                    if (onItemCheckListener != null) {
                        onItemCheckListener.onItemCheck(adapterPosition, isChecked);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public static class ShoppingItemViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbPurchased;
        TextView tvItemName;
        TextView tvItemQuantity;

        public ShoppingItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cbPurchased = itemView.findViewById(R.id.cbPurchased);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemQuantity = itemView.findViewById(R.id.tvItemQuantity);
        }
    }
}
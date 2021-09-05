package com.example.simpletodo;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view.
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    public interface OnLongClickListener {
        void OnItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // wrap inside a view holder and return it
        return new ViewHolder(todoView);
    }

    // Bind data to a particular view holder.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab an item at their position.
        String item = items.get(position);

        //Bind the item to a specify view holder.
        holder.bind(item);
    }

    @Override
    // Count the number of items on this list.
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views that represent each row of the list.
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;
        // Constructor.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update the view holder with this item.
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClickListener.OnItemLongClicked(getAdapterPosition());
                    // Remove the item from the recycler view.
                    return true;
                }
            });
        }
    }

}

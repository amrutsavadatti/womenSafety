package com.example.womensafety.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.womensafety.R;
import com.example.womensafety.model.Contacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Contacts> contactsList;

    public RecyclerViewAdapter(Context context, List<Contacts> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contacts contacts = contactsList.get(position);
        holder.contactName.setText(contacts.getName());
        holder.phoneNumber.setText(contacts.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView contactName;
        public TextView phoneNumber;
        public ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            contactName = itemView.findViewById(R.id.ContactNames);
            phoneNumber = itemView.findViewById(R.id.ContactNumbers);
            delete = itemView.findViewById(R.id.delContact);

            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("recyclerTest", "onClick: ");

        }
    }
}

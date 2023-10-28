package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyJournalAdapter extends RecyclerView.Adapter<MyJournalAdapter.ViewHolder> {
    MyJournalData[] myJournalData;
    Context context;

    public MyJournalAdapter(MyJournalData[] myJournalData, JournalActivity activity) {
        this.myJournalData = myJournalData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.journal_entry_list, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyJournalData myJournalDataList = myJournalData[position];
        holder.textViewName.setText(myJournalDataList.getEntryName());
        holder.textViewDesc.setText(myJournalDataList.getEntryDescription());
        //holder.foodImage.setImageResource(myJournalDataList.getFoodImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            /* TODO Call an intent for OrderActivity allowing you to order food */
            @Override
            public void onClick(View v) {
                /* Remove this and replace it with an intent call*/
                Intent entryIntent = new Intent(context, EntryActivity.class);
                entryIntent.putExtra("entryName", myJournalDataList.getEntryName());
                entryIntent.putExtra("entryDescription", myJournalDataList.getEntryDescription());
                //orderIntent.putExtra("foodImage", myFoodDataList.getFoodImage());
                context.startActivity(entryIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myJournalData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        //ImageView foodImage;
        TextView textViewName;
        TextView textViewDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //foodImage = itemView.findViewById(R.id.entryImage);
            textViewName = itemView.findViewById(R.id.entryName);
            textViewDesc = itemView.findViewById(R.id.entryDescription);
        }
    }
}

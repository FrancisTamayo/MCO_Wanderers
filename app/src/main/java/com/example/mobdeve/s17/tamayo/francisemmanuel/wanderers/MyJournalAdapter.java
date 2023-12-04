package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        DateFormat noteDateFormat = new SimpleDateFormat ("MMM dd, yyyy");
        String strDateDesc = noteDateFormat.format (myJournalDataList.getDateModified());
        Date c = new Date(System.currentTimeMillis ());
        long milliseconds = c.getTime ();

        if (strDateDesc.equals (noteDateFormat.format (milliseconds)))
            strDateDesc = "Today";

        holder.textViewName.setText(myJournalDataList.getTitle());
        holder.textViewDesc.setText("Last Modified: " + strDateDesc);
        //holder.foodImage.setImageResource(myJournalDataList.getFoodImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            /* TODO Call an intent for OrderActivity allowing you to order food */
            @Override
            public void onClick(View v) {
                /* Remove this and replace it with an intent call*/
                long newMilliseconds = new Date (System.currentTimeMillis ()).getTime ();
                String strDateMade = noteDateFormat.format (myJournalDataList.getDateMade ());

                if (strDateMade.equals (noteDateFormat.format (newMilliseconds)))
                    strDateMade = "Today";

                Intent entryIntent = new Intent(context, NoteActivity.class);

                entryIntent.putExtra ("isNewNote", false);
                entryIntent.putExtra (DatabaseHelper._ID, myJournalDataList.getId ());
                entryIntent.putExtra (DatabaseHelper.TITLE, myJournalDataList.getTitle ());
                entryIntent.putExtra (DatabaseHelper.CONTENT, myJournalDataList.getContent ());
                entryIntent.putExtra (DatabaseHelper.DATE_MADE, strDateMade);
                entryIntent.putExtra (DatabaseHelper.LOCATION, myJournalDataList.getLocation ());

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

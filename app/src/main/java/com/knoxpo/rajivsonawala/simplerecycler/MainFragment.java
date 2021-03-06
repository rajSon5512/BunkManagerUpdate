package com.knoxpo.rajivsonawala.simplerecycler;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private List<Bunk> bunks = new ArrayList<>();
    private String TAG="Your_Item";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment, container, false);

        init(view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        BunkAdapterFirst bunkAdapterFirst = new BunkAdapterFirst(bunks);

        mRecyclerView.setAdapter(bunkAdapterFirst);


        return view;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init(View view) {

        mRecyclerView = view.findViewById(R.id.rv_list);

        int ToatalDay=Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        int FirstDay=0;

        Date d;

        for (int i = FirstDay; i < ToatalDay; i++) {

            Calendar c=Calendar.getInstance();
            c.set(Calendar.DATE,1);
            c.add(Calendar.DATE,i);
            d=c.getTime();
            Bunk bunk=new Bunk(d);
            bunks.add(bunk);
        }
    }

    private class BunkHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        private TextView mTextView;
        private TextView mYourDate;
        private CheckBox mCheckBox;

        public BunkHolder(View itemView) {
            super(itemView);
            mTextView =itemView.findViewById(R.id.your_day);
            mYourDate=itemView.findViewById(R.id.your_date);
            mCheckBox=itemView.findViewById(R.id.bunk_choice);
        }

        public void bind(Bunk bunk) {
           // mTextView.setText(bunk.getDay().toString());
            Date date = bunk.getDate();
            CharSequence weekDayName = DateFormat.format("EEEE", date);
            mTextView.setText(weekDayName);
            mYourDate.setText(bunk.getDate().toString());
            mCheckBox.setOnCheckedChangeListener(this);

        }
         @Override
        public void onClick(View v) {


        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



        }
    }

    private class BunkAdapterFirst extends RecyclerView.Adapter<BunkHolder> {

        private ArrayList<Bunk> mBunks;
        private LayoutInflater mLayout;

        BunkAdapterFirst(List<Bunk> mbunks) {
            mBunks = (ArrayList<Bunk>) mbunks;
            mLayout = LayoutInflater.from(getActivity());

        }


        @NonNull
        @Override
        public BunkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = mLayout.inflate(R.layout.simple_text_view, parent, false);

            return new BunkHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull BunkHolder holder, int position) {

            holder.bind(bunks.get(position));

        }

        @Override
        public int getItemCount() {
            return bunks.size();
        }
    }

}

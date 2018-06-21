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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private String TAG = "Your_Item";
    private TextView mAnswer;
    private int mNoOfDay=0;
    private int TotalDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment, container, false);

        init(view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final BunkAdapterFirst bunkAdapterFirst = new BunkAdapterFirst(bunks);

        mRecyclerView.setAdapter(bunkAdapterFirst);
        return view;

    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init(View view) {

        mRecyclerView = view.findViewById(R.id.rv_list);
        mAnswer=view.findViewById(R.id.total_checks);
        mAnswer.setText("Your Check No of Box :"+mNoOfDay);

        final int FirstDay = 0;

        Date d;

        for (int i = FirstDay; i < TotalDay; i++) {

            Calendar c = Calendar.getInstance();
            c.set(Calendar.DATE, 1);
            c.add(Calendar.DATE, i);
            d = c.getTime();
            Bunk bunk = new Bunk(d);
            bunks.add(bunk);
        }

    }

    private class BunkHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        private TextView mTextView;
        private TextView mYourDate;
        private CheckBox mCheckBox;
        private Bunk mBunk;

        public BunkHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.bunk_day);
            mYourDate = itemView.findViewById(R.id.bunk_date);
            mCheckBox = itemView.findViewById(R.id.bunk_choice);

        }


        public void bind(Bunk bunk) {
            // mTextView.setText(bunk.getDay().toString());
            mBunk = bunk;

            Date date = bunk.getDate();
            CharSequence weekDayName = DateFormat.format("EEEE", date);

            mTextView.setText(weekDayName);

            CharSequence Date=DateFormat.format("EEEE,d||MM||yy ",date);

            mYourDate.setText(Date.toString());
            mCheckBox.setChecked(bunk.isChecked());

            mCheckBox.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mBunk.setIsChecked(isChecked);

            mNoOfDay=0;

            for(int i=0;i<TotalDay;i++){

                if(bunks.get(i).isChecked()==true){

                    mNoOfDay=mNoOfDay+1;

                }

                mAnswer.setText("your Number of Check Box:"+mNoOfDay);


            }


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

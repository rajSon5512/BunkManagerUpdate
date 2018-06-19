package com.knoxpo.rajivsonawala.simplerecycler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private List<Bunk> bunks = new ArrayList<>();

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

    private void init(View view) {

        mRecyclerView = view.findViewById(R.id.rv_list);
        Date date = new Date();

        Bunk bunk = new Bunk(date);

        for (int i = 0; i < 30; i++) {

            bunks.add(bunk);

        }
    }

    private class BunkHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextView;

        public BunkHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.simple_text);

        }

        public void bind(Bunk bunk) {
            mTextView.setText(bunk.getDate().toString());

        }
         @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),mTextView.getText().toString(),Toast.LENGTH_SHORT).show();
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

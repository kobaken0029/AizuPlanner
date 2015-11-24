package com.kobaken0029.aizuplanner.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.view.OnListFragmentInteractionListener;
import com.kobaken0029.aizuplanner.view.adapter.dummy.DummyContent;

import java.util.List;

import butterknife.ButterKnife;

public class MyEventRecyclerViewAdapter extends RecyclerView.Adapter<MyEventRecyclerViewAdapter.ViewHolder> {
    private List<DummyContent.DummyItem> mValues;
    private OnListFragmentInteractionListener mListener;

    public MyEventRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
        mValues = items;
    }

    public void setValues(List<DummyContent.DummyItem> values) {
        mValues = values;
        notifyDataSetChanged();
    }

    public void setOnListFragmentInteractionListener(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = ButterKnife.findById(mView, R.id.id);
            mContentView = ButterKnife.findById(mView, R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

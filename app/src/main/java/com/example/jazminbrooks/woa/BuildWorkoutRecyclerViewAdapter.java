package com.example.jazminbrooks.woa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.jazminbrooks.woa.Data.ExerciseContent;
import com.example.jazminbrooks.woa.Data.WorkoutContent;

import java.util.ArrayList;
import java.util.List;

/**

 * TODO: Replace the implementation with code for your data type.
 */
public class BuildWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<BuildWorkoutRecyclerViewAdapter.ViewHolder> {

    private List<ExerciseContent.Exercise> mValues;
    private BuildWorkoutFragment.OnListFragmentInteractionListener mListener;
    private boolean[] mSelected;

    public BuildWorkoutRecyclerViewAdapter(List<ExerciseContent.Exercise> items, BuildWorkoutFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mSelected = new boolean[mValues.size()];

        for (int i = 0; i < mSelected.length; i++) {
            mSelected[i] = false;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_build_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getType());

        holder.mCheckBox.setOnCheckedChangeListener(null);


        holder.mCheckBox.setChecked(mSelected[position]);

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSelected[position] = isChecked;
                WorkoutContent.addRemoveExerciseToWorkout(holder.mItem);
            }
        });


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
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
        public final TextView mNameView;
        public final TextView mContentView;
        public final CheckBox mCheckBox;
        public ExerciseContent.Exercise mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
            mNameView = (TextView) view.findViewById(R.id.build_name);
            mContentView = (TextView) view.findViewById(R.id.build_type);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}


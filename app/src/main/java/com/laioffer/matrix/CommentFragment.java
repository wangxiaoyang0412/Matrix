package com.laioffer.matrix;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {
    private GridView gridView;

    OnItemSelectListener callback;
    // Container Activity must implement this interface
    public interface OnItemSelectListener {
        public void onCommentSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnItemSelectListener) context;
        } catch (ClassCastException e) {
            //do something
        }
    }

    public CommentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        gridView = (GridView) view.findViewById(R.id.comment_grid);
        gridView.setAdapter(new EventAdapter(getActivity()));

        // add click listen to grid item
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.onCommentSelected(position);
            }
        });
        return view;
    }

    // Change background color if the item is selected
    public void onItemSelected(int position){
        for (int i = 0; i < gridView.getChildCount(); i++){
            if (position == i) {
                gridView.getChildAt(i).setBackgroundColor(Color.RED);
            } else {
                gridView.getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        }
    }

    public static CommentFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        if (bundle != null) {
            final int position = (int) bundle.get("position");
            gridView.post(new Runnable() {
                @Override
                public void run() {
                    onItemSelected(position);
                }
            });
        }
    }
}


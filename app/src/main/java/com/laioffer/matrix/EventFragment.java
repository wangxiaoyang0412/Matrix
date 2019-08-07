package com.laioffer.matrix;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {
    OnItemSelectListener callBack;
    private ListView listView;
    // Container Activity must implement this interface
    public interface OnItemSelectListener {
        public void onItemSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callBack = (OnItemSelectListener) context;
        } catch (ClassCastException e) {
            //do something
        }
    }


    public EventFragment() {
        // Required empty public constructor
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        listView = (ListView) view.findViewById(R.id.event_list);
        listView.setAdapter(new EventAdapter(getActivity()));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                getEventNames());

        // Assign adapter to ListView.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                callBack.onItemSelected(i);
            }
        });

        return view;
    }

    public void onItemSelected(int position){
        for (int i = 0; i < listView.getChildCount(); i++){
            if (position == i) {
                listView.getChildAt(i).setBackgroundColor(Color.BLUE);
            } else {
                listView.getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        }
    }

    private String[] getEventNames() {
        String[] names = {
                "Event1", "Event2", "Event3",
                "Event4", "Event5", "Event6",
                "Event7", "Event8", "Event9",
                "Event10", "Event11", "Event12"};
        return names;
    }

}

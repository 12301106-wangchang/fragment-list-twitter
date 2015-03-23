package com.deitel.twittersearches;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.deitel.twittersearches.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment1 extends ListFragment  {

    private OnFragmentInteractionListener mListener;
    private ListView listView;

    private SharedPreferences savedSearches; // user's favorite searches

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity main;
        main = (MainActivity)this.getActivity();


        View v = inflater.inflate(R.layout.fragment_fragment1, container,false);
        ListView list = (ListView)v.findViewById(android.R.id.list);
        list.setOnItemLongClickListener(main.getOnItemLongClickListener());
        list.setAdapter(main.getAdapter());

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event



   public void setSavedSearches(SharedPreferences save){
       savedSearches = save;
   }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


        String tag = ((TextView) v).getText().toString();
        String urlString = getString(R.string.searchURL) +
                Uri.encode(savedSearches.getString(tag, ""), "UTF-8");
        if (mListener != null) {
            mListener.onFragmentInteraction(urlString);
        }

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_holder,new Fragment2())
                .commit();
        // create an Intent to launch a web browser
        // launches web browser to view results
    }







    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String url);
    }



}

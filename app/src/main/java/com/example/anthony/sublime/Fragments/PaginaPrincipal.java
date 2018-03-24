package com.example.anthony.sublime.Fragments;

import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anthony.sublime.R;
import com.example.anthony.sublime.Registrar.user_gs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaginaPrincipal.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaginaPrincipal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaginaPrincipal extends Fragment {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    RecyclerView.Adapter adapter;
    List<user_gs> list = new ArrayList<>();
    LinearLayoutManager mLayoutManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PaginaPrincipal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaginaPrincipal.
     */
    // TODO: Rename and change types and number of parameters
    public static PaginaPrincipal newInstance(String param1, String param2) {
        PaginaPrincipal fragment = new PaginaPrincipal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // init constraintLayout
        View rootView = inflater.inflate(R.layout.fragment_pagina_principal, container, false);

       /* constraintLayout = (ConstraintLayout) rootView.findViewById(R.id.constraint_page);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        // setting enter fade animation duration to 10 seconds
        animationDrawable.//setEnterFadeDuration(0);

        // setting exit fade animation duration to 5 seconds
        animationDrawable.setExitFadeDuration(0);*/

        recyclerView = rootView.findViewById(R.id.recycler_view);

        new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        // Read from the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("users").push();

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userInfo : dataSnapshot.getChildren()){
                    user_gs rc_ad = userInfo.getValue(user_gs.class);

                    list.add(rc_ad);
                }

                adapter = new RecyclerViewAdapter(getContext(), list);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return rootView;
}

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private class GetDataFromFirebase extends AsyncTask<Void,Void,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}

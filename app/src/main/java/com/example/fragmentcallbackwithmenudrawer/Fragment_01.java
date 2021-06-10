package com.example.fragmentcallbackwithmenudrawer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentcallbackwithmenudrawer.utils.Gol;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_01 extends Fragment {

    private static final String TAG = "Fragment_01";
    private static final String emplacement = Fragment_01.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Gol.addLog(emplacement, "onCreateView");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_01, container, false);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gol.addLog(emplacement, "onCreate");
    }


    @Override
    public void onResume() {
        super.onResume();
        Gol.addLog(emplacement, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Gol.addLog(emplacement, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Gol.addLog(emplacement, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Gol.addLog(emplacement, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Gol.addLog(emplacement, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Gol.addLog(emplacement, "onDetach");
    }
}
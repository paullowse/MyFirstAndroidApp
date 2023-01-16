package com.example.myfirstapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfirstapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    TextView showCountTextView;

    /**
     * This function also creates the showCountTextView from the start so u cache it
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return layout view that is currently returned
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        try{
            View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
            showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);

            binding = FragmentFirstBinding.inflate(inflater, container, false);
            return fragmentFirstLayout;

        } catch (Exception e){
            Log.e("TAG", "onCreateView", e);
            throw e;
        }
    }

    //@Override - original
    //public View onCreateView(
    //        LayoutInflater inflater, ViewGroup container,
    //        Bundle savedInstanceState
    //) {

    //    binding = FragmentFirstBinding.inflate(inflater, container, false);
    //    return binding.getRoot();
    //}

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //binding.randomButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        NavHostFragment.findNavController(FirstFragment.this)
        //                .navigate(R.id.action_FirstFragment_to_SecondFragment);
        //    }
        //});



        // findViewByID finds the correct button for toast_button
        view.findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
                myToast.show();

            }
        });

        // findViewByID finds the correct button for toast_button
        view.findViewById(R.id.count_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countMe(view);

            }
        });

        // findViewByID finds the correct button - for random_button
        view.findViewById(R.id.random_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = Integer.parseInt(showCountTextView.getText().toString());
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
                NavHostFragment.findNavController(FirstFragment.this).navigate(action);

            }
        });
    }

    /**
     * Obtains the text value from CountText and adds one to it, and displays that.
     * @param view
     */
    private void countMe(View view) {
        // Get the value of the text view
        String countString = showCountTextView.getText().toString();

        // Convert value to a number and increment it
        Integer count = Integer.parseInt(countString);
        count++;

        // Display the new value in the text view.
        showCountTextView.setText(count.toString());
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
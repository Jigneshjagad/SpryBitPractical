package com.sprybit.practical.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sprybit.practical.R;
import com.sprybit.practical.activity.MainActivity;
import com.sprybit.practical.databinding.FragmentQuestionOneBinding;
import com.sprybit.practical.databinding.FragmentQuestionTwoBinding;

public class QuestionTwoFragment extends Fragment {
    private FragmentQuestionTwoBinding binding;
    private MainActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuestionTwoBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        mActivity = (MainActivity) getActivity();
        setToolBarView();

    }

    // set Toolbar View
    private void setToolBarView() {
        mActivity.setTitle("Question2");
        mActivity.setHomeIndicatorIcon(R.drawable.ic_menu_24);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
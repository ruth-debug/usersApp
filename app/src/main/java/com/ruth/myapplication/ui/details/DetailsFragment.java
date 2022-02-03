package com.ruth.myapplication.ui.details;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruth.myapplication.R;
import com.ruth.myapplication.databinding.FragmentDetailsBinding;
import com.ruth.myapplication.databinding.MainFragmentBinding;
import com.ruth.myapplication.ui.main.MainFragmentDirections;
import com.ruth.myapplication.ui.main.MainViewModel;
import com.ruth.myapplication.ui.main.UserAdapter;


public class DetailsFragment extends Fragment {


    private DetailsViewModel mViewModel;
    private FragmentDetailsBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(DetailsViewModel.class);
        mBinding.setLifecycleOwner(getActivity());
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.setCurrent( DetailsFragmentArgs.fromBundle(getArguments()).getUserId());
        mViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
//           startCountDown();

        });

    }

}
package com.ruth.myapplication.ui.main;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ruth.myapplication.R;
import com.ruth.myapplication.databinding.MainFragmentBinding;
import com.ruth.myapplication.model.User;
import com.ruth.myapplication.network.retrofit.Resource;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding mBinding;
    private UserAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mBinding.setLifecycleOwner(getActivity());
        mBinding.setViewModel(mViewModel);

        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.swipeLayout.setRefreshing(true);

        adapter = new UserAdapter(new ArrayList<>());
        adapter.getPositionClicks().subscribe(response -> {
            navigate(response);
        });
        mBinding.recyclerView.setAdapter(adapter);

        mViewModel.fetchUsers();
        mViewModel.getAllUsers().observe(getViewLifecycleOwner(), result -> {
            setData(result);
        });
        mBinding.swipeLayout.setOnRefreshListener(() -> {
            mViewModel.refreshUsers();
            mViewModel.getAllUsers().observe(getViewLifecycleOwner(), result -> {
                setData(result);
            });
        });

    }

    private void navigate(Long response) {
        MainFragmentDirections.ActionMainFragmentToDetailsFragment directions =
                MainFragmentDirections.actionMainFragmentToDetailsFragment(response);
        NavHostFragment.findNavController(this).navigate(directions);
    }

    private void setData(Resource<List<User>> result) {
        if (result.status == Resource.Status.SUCCESS) {
            adapter.reset(result.data);
        } else {
            Toast.makeText(getContext(), getString(R.string.network_error), Toast.LENGTH_LONG).show();
        }
        mBinding.swipeLayout.setRefreshing(false);
    }

}
package com.ruth.myapplication.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruth.myapplication.databinding.UserItemBinding;
import com.ruth.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<User> users;
    private final PublishSubject<Long> onClickSubject = PublishSubject.create();

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding layout = UserItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.layout.setModel(users.get(position));
        holder.layout.getRoot().setOnClickListener(v -> onClickSubject.onNext(users.get(position).getUser_id()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void reset(List<User> array) {
        users.clear();
        users.addAll(array);
        notifyDataSetChanged();


    }
    public Observable<Long> getPositionClicks(){
        return onClickSubject;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        UserItemBinding layout;

        public ViewHolder(@NonNull UserItemBinding layout) {
            super(layout.getRoot());
            this.layout = layout;
        }
    }
}

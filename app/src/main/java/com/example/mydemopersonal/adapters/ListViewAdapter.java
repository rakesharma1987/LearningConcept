package com.example.mydemopersonal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.roomDb.User;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    List<User> list;
    Context context;

    public ListViewAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
            TextView textView = view.findViewById(R.id.tvListItem);
            textView.setText(list.get(i).uId+"\n"+list.get(i).fName+"\n"+list.get(i).lName+"\n"+list.get(i).email);
        }
        return view;
    }
}

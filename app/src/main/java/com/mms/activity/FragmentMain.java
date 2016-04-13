package com.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.mms.R;
import com.mms.base.BaseFragment;

import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/11.
 */
public class FragmentMain extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.btn_fragment_main_add)
    private Button btnAdd;

    @InjectView(R.id.rl_fragment_main_system_message)
    private RelativeLayout rlSystemMessage;

    @InjectView(R.id.rl_fragment_main_online_office)
    private RelativeLayout rlOnlineOffice;

    @InjectView(R.id.rl_fragment_main_item_new)
    private RelativeLayout rlItemNew;

    @InjectView(R.id.rl_fragment_main_item_todo)
    private RelativeLayout rlItemTodo;

    @InjectView(R.id.rl_fragment_main_item_todo_aite)
    private RelativeLayout rlItemTodoAite;

    @InjectView(R.id.rl_fragment_main_item_book)
    private RelativeLayout rlItemBook;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_main, container,
                false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOCL();

    }

    private void setOCL() {
        rlItemBook.setOnClickListener(this);
        rlItemNew.setOnClickListener(this);
        rlItemTodo.setOnClickListener(this);
        rlItemTodoAite.setOnClickListener(this);
        rlOnlineOffice.setOnClickListener(this);
        rlSystemMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_fragment_main_add:
                break;
            case R.id.rl_fragment_main_system_message:
                break;
            case R.id.rl_fragment_main_online_office:
                intent = new Intent(getActivity(),ActivityOnlineOffice.class);
                startActivity(intent);
                break;
            case R.id.rl_fragment_main_item_new:
                break;
            case R.id.rl_fragment_main_item_todo:
                break;
            case R.id.rl_fragment_main_item_todo_aite:
                break;
            case R.id.rl_fragment_main_item_book:
                break;
        }

    }
}

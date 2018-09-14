package com.test.smartschool.Page;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.smartschool.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button testButton2;
    private Button testButton3;
    private Button testButton4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){

        //跳转到数据分析
        testButton2 = view.findViewById(R.id.testbutton2);
        testButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DataAnalysisActivity.class);
                startActivity(intent);
            }
        });

        testButton3 = view.findViewById(R.id.testbutton3);
        testButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, StudyAnalysisActivity.class);
//                startActivity(intent);

//                NetClient.getInstance().startRequest("1");
            }
        });

        testButton4 = view.findViewById(R.id.testbutton4);
        testButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ClassActivity.class);
                startActivity(intent);
            }
        });

    }

}

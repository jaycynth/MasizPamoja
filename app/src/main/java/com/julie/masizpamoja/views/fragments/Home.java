package com.julie.masizpamoja.views.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.card.MaterialCardView;
import com.julie.masizpamoja.R;
import com.julie.masizpamoja.adapters.MainAdapter;
import com.julie.masizpamoja.adapters.RandomBlogAdapter;
import com.julie.masizpamoja.models.MainAction;
import com.julie.masizpamoja.models.RandomBlogs;
import com.julie.masizpamoja.utils.MainActionData;
import com.julie.masizpamoja.utils.RandomBlogsData;
import com.julie.masizpamoja.views.activities.AboutUs;
import com.julie.masizpamoja.views.activities.OurActivities;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Home extends Fragment {

    private static RecyclerView.LayoutManager layoutManager;

    private RandomBlogAdapter randomBlogAdapter;

    @BindView(R.id.random_blogs_recyclerView)
    RecyclerView random_blogs_rcyclerview;

    private List<RandomBlogs> randomBlogsList= new ArrayList<>();

    private MainAdapter mainAdapter;


    @BindView(R.id.main_actions_recyclerView)
    RecyclerView main_action_recyclerview;


    private List<MainAction> actionList = new ArrayList<>();


    @BindView(R.id.who_we_are)
    MaterialCardView whoWeAre;

    @BindView(R.id.what_do_we_do)
    MaterialCardView whatDoWeDo;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        if (getActivity() != null) {
            getActivity().setTitle("Home");
        }

        randomBlogsList = RandomBlogsData.getRandomBlogs();
        initViewRandomBlogs(randomBlogsList);

        actionList= MainActionData.getMainAction();
        initViewMainActions(actionList);


        whoWeAre.setOnClickListener(v->{
              startActivity(new Intent(getActivity(), AboutUs.class));
        });

        whatDoWeDo.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), OurActivities.class));
        });




        return view;
    }

    private void initViewRandomBlogs(List<RandomBlogs> randomBlogsList) {
        randomBlogAdapter = new RandomBlogAdapter(randomBlogsList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        random_blogs_rcyclerview.setLayoutManager(layoutManager);
        random_blogs_rcyclerview.setAdapter(randomBlogAdapter);
        random_blogs_rcyclerview.setNestedScrollingEnabled(false);
    }

    private void initViewMainActions(List<MainAction> actionList) {
        mainAdapter = new MainAdapter(actionList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        main_action_recyclerview.setLayoutManager(layoutManager);
        main_action_recyclerview.setAdapter(mainAdapter);
        main_action_recyclerview.setNestedScrollingEnabled(false);
    }

}

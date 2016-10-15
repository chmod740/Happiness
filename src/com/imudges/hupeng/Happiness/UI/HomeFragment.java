package com.imudges.hupeng.Happiness.UI;

/**
 * Created by HUPENG on 2016/8/25.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.page_0, container, false);
        init(view);
        Picasso.with(this.getActivity()).load(R.drawable.p1).into(imageView);
        return view;
    }
    public void init(View view){
        imageView = (ImageView)view.findViewById(R.id.imageView);
    }


}

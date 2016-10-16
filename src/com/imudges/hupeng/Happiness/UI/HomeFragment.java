package com.imudges.hupeng.Happiness.UI;

/**
 * Created by HUPENG on 2016/8/25.
 */
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

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
		//System.out.println(getSHA1());
		Intent localIntent = new Intent();
		localIntent.setClass(this.getContext(), LocationService.class); // 销毁时重新启动Service
		this.getContext().startService(localIntent);
    }


    private String getSHA1(){
        //	public static String sHA1(Context context) {
        Context context = this.getContext();
        try {
	        PackageInfo info = context.getPackageManager().getPackageInfo(
	            context.getPackageName(), PackageManager.GET_SIGNATURES);
	        byte[] cert = info.signatures[0].toByteArray();
	        MessageDigest md = MessageDigest.getInstance("SHA1");
	        byte[] publicKey = md.digest(cert);
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < publicKey.length; i++) {
	            String appendString = Integer.toHexString(0xFF & publicKey[i])
	                .toUpperCase(Locale.US);
	            if (appendString.length() == 1)
	                hexString.append("0");
	                hexString.append(appendString);
	                hexString.append(":");
	        }
	        String result = hexString.toString();
	        return result.substring(0, result.length()-1);
	    } catch (PackageManager.NameNotFoundException e) {
	        e.printStackTrace();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return null;
    }

}

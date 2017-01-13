package com.base.commen;

import com.umeng.analytics.MobclickAgent;

import android.support.v4.app.Fragment;

public class FragmentBase extends Fragment{
	
	//统计
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getName());
        MobclickAgent.onResume(this.getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getName());
        MobclickAgent.onPause(this.getActivity());
    }
	//统计
	
}

package com.yinghai.piaowan.module.game.adapter;

import android.content.Context;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.bean.IntegralBean;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/24
 *         Description：每日領取積分的 adapter
 * @Param
 */

public class IntegralAdapter extends BaseDataAdapter<IntegralBean>{
    private String[] days = {"第一天","第二天","第三天","第四天","第五天","第六天","第七天",};
    public IntegralAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_integral;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder baseHolder, int i) {
        baseHolder.setText(R.id.tv_integral_num,getItem(i).getPoint()+"");
        baseHolder.setText(R.id.tv_day,days[getItem(i).getDay()-1]);
    }
}

package com.yinghai.piaowan.module.shop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.bean.ShopBean;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/16
 *         Description：商城商品列表中比较长的 item 的adapter
 * @Param
 */

public class GoodLongAdapter extends BaseDataAdapter<ShopBean> {
    private Context mContext;
    public GoodLongAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public int getLayoutRes(int i) {
        if (i%2 == 0) {
            return R.layout.item_shop_long;
        }
        return R.layout.item_shop_short;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder baseHolder, final int i) {
        Glide.with(mContext).load(getItem(i).getImageUrl()).into((ImageView) baseHolder.getView(R.id.iv_goods_bg));
        baseHolder.setText(R.id.tv_shop_description,getItem(i).getGoodDescrip());
        baseHolder.setText(R.id.tv_price,"$"+getItem(i).getGoodPrice());
        baseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickShopCallback != null) {
                    mClickShopCallback.onSelectShop(getItem(i));
                }
            }
        });
    }

    private ClickShopCallback mClickShopCallback;

    public void setmClickShopCallback(ClickShopCallback mClickShopCallback) {
        this.mClickShopCallback = mClickShopCallback;
    }

    public interface ClickShopCallback{
        void onSelectShop(ShopBean shopBean);
    }
}

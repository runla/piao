package com.yinghai.piaowan.module.shop;

import android.support.v4.content.ContextCompat;

import com.example.fansonlib.utils.StatusBarUtil;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseActivity;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.module.oder.OrderActivity;
import com.yinghai.piaowan.module.person.fragment.AddressFragment;
import com.yinghai.piaowan.module.person.fragment.CollectFragment;
import com.yinghai.piaowan.module.person.fragment.OrderFragment;
import com.yinghai.piaowan.module.shop.fragment.ShopCarFragment;
import com.yinghai.piaowan.module.shop.fragment.ShopDetailFragment;
import com.yinghai.piaowan.module.shop.fragment.ShopFragment;
import com.yinghai.piaowan.module.shop.fragment.ShopPayFragment;

public class ShopActivity extends MyBaseActivity {
    private ShopFragment mShopFragment;
    private ShopDetailFragment mShopDetailFragment;
    private OrderFragment mOrderFragment;
    private CollectFragment mCollectFragment;
    private ShopPayFragment mShopPayFragment;
    private AddressFragment mAddressFragment;
    private ShopCarFragment mShopCarFragment;
    @Override
    protected int getContentView() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void listenEvent() {

    }

    @Override
    protected void initView() {
        super.initView();
        initShopFragment(true);
    }

    private void initShopFragment(boolean isIn) {
        if (mShopFragment == null) {
            mShopFragment = new ShopFragment();
        }
        if (isIn) {
            replaceFragment(R.id.container, mShopFragment);
        } else {
            replaceFragment(R.id.container, mShopFragment, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        }
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.personBg));
        mCurrentFragment = mShopFragment;
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment instanceof ShopFragment){
            super.onBackPressed();
        }else if (mCurrentFragment instanceof CollectFragment
                ||mCurrentFragment instanceof OrderFragment
                ||mCurrentFragment instanceof ShopCarFragment
                ||mCurrentFragment instanceof ShopDetailFragment){
            initShopFragment(false);
        }else if (mCurrentFragment instanceof ShopPayFragment){
            initShopDetailFragment(false);
        }else if (mCurrentFragment instanceof AddressFragment){
            initShopPayFragment(false);
        }
    }

    @Override
    public void onFragmentCallback(Object... object) {
        super.onFragmentCallback(object);
        switch ((int) object[0]) {
            case ConFragmentResponseCode.SHOP_CAR:
                initShopCarFragment(true);
                break;

            case ConFragmentResponseCode.PERSON_MY_ORDER:
                startMyActivity(OrderActivity.class);
                break;

            case ConFragmentResponseCode.PERSON_MY_COLLECT:
                initCollectFragment(true);
                break;

            case ConFragmentResponseCode.SHOP_DETAILS:
                initShopDetailFragment(true);
                break;

            case ConFragmentResponseCode.SHOP_PAY:
                initShopPayFragment(true);
                break;

            case ConFragmentResponseCode.SHOP_SELECT_ADDRESS:
                initAddressFragment(true);
                break;


            default:
                break;
        }
    }

    /**
     * 初始化我的订单界面
     *
     * @param isIn true：无动画 -- false:从左边向右添加
     */
    private void initOrderFragment(boolean isIn) {
        if (mOrderFragment == null) {
            mOrderFragment = new OrderFragment();
        }
        if (isIn) {
            replaceFragment(R.id.container, mOrderFragment, R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
        } else {
            replaceFragment(R.id.container, mOrderFragment, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        }
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.md_grey_100_color_code));
        mCurrentFragment = mOrderFragment;
    }

    /**
     * 初始化我的收藏界面
     *
     * @param isIn true：无动画 -- false:从左边向右添加
     */
    private void initCollectFragment(boolean isIn) {
        if (mCollectFragment == null) {
            mCollectFragment = new CollectFragment();
        }
        if (isIn) {
            replaceFragment(R.id.container, mCollectFragment, R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
        } else {
            replaceFragment(R.id.container, mCollectFragment, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        }
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.md_grey_100_color_code));
        mCurrentFragment = mCollectFragment;
    }

    /**
     * 初始化商品詳情的界面
     *
     * @param isIn true：从右边向左添加 -- false:从左边向右添加
     */
    private void initShopDetailFragment(boolean isIn) {
        if (mShopDetailFragment == null) {
            mShopDetailFragment = new ShopDetailFragment();
        }
        if (isIn) {
            replaceFragment(R.id.container, mShopDetailFragment, R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
        } else {
            replaceFragment(R.id.container, mShopDetailFragment, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        }
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.md_grey_100_color_code));
        mCurrentFragment = mShopDetailFragment;
    }

    /**
     * 初始化商品支付的界面
     *
     * @param isIn true：从右边向左添加 -- false:从左边向右添加
     */
    private void initShopPayFragment(boolean isIn) {
        if (mShopPayFragment == null) {
            mShopPayFragment = new ShopPayFragment();
        }
        if (isIn) {
            replaceFragment(R.id.container, mShopPayFragment, R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
        } else {
            replaceFragment(R.id.container, mShopPayFragment, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        }
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.md_grey_100_color_code));
        mCurrentFragment = mShopPayFragment;
    }

    /**
     * 初始化地址選擇的界面
     *
     * @param isIn true：从右边向左添加 -- false:从左边向右添加
     */
    private void initAddressFragment(boolean isIn) {
        if (mAddressFragment == null) {
            mAddressFragment = new AddressFragment();
        }
        if (isIn) {
            replaceFragment(R.id.container, mAddressFragment, R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
        } else {
            replaceFragment(R.id.container, mAddressFragment, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        }
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.md_grey_100_color_code));
        mCurrentFragment = mAddressFragment;
    }

 /**
     * 初始化購物車的界面
     *
     * @param isIn true：从右边向左添加 -- false:从左边向右添加
     */
    private void initShopCarFragment(boolean isIn) {
        if (mShopCarFragment == null) {
            mShopCarFragment = new ShopCarFragment();
        }
        if (isIn) {
            replaceFragment(R.id.container, mShopCarFragment, R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
        } else {
            replaceFragment(R.id.container, mShopCarFragment, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        }
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.md_grey_100_color_code));
        mCurrentFragment = mShopCarFragment;
    }


}

package com.yinghai.piaowan.module.person;

import android.support.v4.content.ContextCompat;

import com.example.fansonlib.utils.StatusBarUtil;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseActivity;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.module.oder.OrderActivity;
import com.yinghai.piaowan.module.person.fragment.AddressFragment;
import com.yinghai.piaowan.module.person.fragment.BindAccountFragment;
import com.yinghai.piaowan.module.person.fragment.CollectFragment;
import com.yinghai.piaowan.module.person.fragment.OrderFragment;
import com.yinghai.piaowan.module.person.fragment.PersonFragment;

public class PersonActivity extends MyBaseActivity {
    private PersonFragment mPersonFragment;
    private BindAccountFragment mBindAccountFragment;
    private OrderFragment mOrderFragment;
    private CollectFragment mCollectFragment;
    private AddressFragment mAddressFragment;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_person;
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
        initPersonFragment(true);
    }

    /**
     * 初始化个人中心的 fragment
     *
     * @param isIn true：无动画 -- false:从左边向右添加
     */
    private void initPersonFragment(boolean isIn) {
        if (mPersonFragment == null) {
            mPersonFragment = new PersonFragment();
        }
        if (isIn) {
            replaceFragment(R.id.container, mPersonFragment);
        } else {
            replaceFragment(R.id.container, mPersonFragment, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        }
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.personBg));
        mCurrentFragment = mPersonFragment;
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment instanceof PersonFragment) {
            finish();
        } else if (mCurrentFragment instanceof BindAccountFragment
                || mCurrentFragment instanceof OrderFragment
                || mCurrentFragment instanceof CollectFragment
                || mCurrentFragment instanceof AddressFragment) {
            initPersonFragment(false);
        }
    }

    @Override
    public void onFragmentCallback(Object... object) {
        super.onFragmentCallback(object);
        switch ((int) object[0]) {
            case ConFragmentResponseCode.PERSON_MY_ORDER:
//                initOrderFragment(true);
                startMyActivity(OrderActivity.class);
                break;

            case ConFragmentResponseCode.PERSON_MY_ADDRESS:
                initAddressFragment(true);
                break;

            case ConFragmentResponseCode.PERSON_MY_COLLECT:
                initCollectFragment(true);
                break;

            case ConFragmentResponseCode.PERSON_MY_HEADER:
                break;

            case ConFragmentResponseCode.PERSON_MY_ACCOUNT:
                initBindAccountFragment(true);
                break;

            default:
                break;
        }
    }

    /**
     * 初始化b绑定账号的界面
     *
     * @param isIn true：从右边向左添加 -- false:从左边向右添加
     */
    private void initBindAccountFragment(boolean isIn) {
        if (mBindAccountFragment == null) {
            mBindAccountFragment = new BindAccountFragment();
        }
        if (isIn) {
            replaceFragment(R.id.container, mBindAccountFragment, R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
        } else {
            replaceFragment(R.id.container, mBindAccountFragment, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        }
        mCurrentFragment = mBindAccountFragment;
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
        mCurrentFragment = mCollectFragment;
    }

    /**
     * 初始化我的地址管理
     *
     * @param isIn true：无动画 -- false:从左边向右添加
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
        mCurrentFragment = mAddressFragment;
    }


}

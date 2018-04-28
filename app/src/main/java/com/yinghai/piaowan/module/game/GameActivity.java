package com.yinghai.piaowan.module.game;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.WindowManager;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseActivity;
import com.yinghai.piaowan.manager.MyFragmentManager;
import com.yinghai.piaowan.module.game.fragment.GameFragment;
import com.yinghai.piaowan.module.game.fragment.IntegralFragment;
import com.yinghai.piaowan.module.game.fragment.SignUpFragment;

/**
 * 游戏的 Activity
 */
public class GameActivity extends MyBaseActivity {
    private GameFragment mGameFragment;
    private IntegralFragment mIntegralFragment;
    private SignUpFragment mSignUpFragment;
    @Override
    protected int getContentView() {
        return R.layout.activity_game;
    }

    @Override
    protected void initView() {
        super.initView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);

        initSignUpFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void listenEvent() {

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_sign_up:
                    initSignUpFragment();
                    return true;

                case R.id.navigation_game:
                    initGameFragment();
                    return true;

                case R.id.navigation_integral:
                    initIntegralFragment();
                    return true;
                default:
                    break;
            }
            return false;
        }

    };

    /**
     * 初始化每日簽到的 fragment
     */
    private void initSignUpFragment(){
        if (mSignUpFragment == null) {
            mSignUpFragment = new SignUpFragment();
        }
        MyFragmentManager.replaceFragment(getSupportFragmentManager(), R.id.container, mSignUpFragment);
        mCurrentFragment = mSignUpFragment;
    }

    /**
     *  初始化遊戲的 fragment
     */
    private void initGameFragment(){
        if (mGameFragment == null) {
            mGameFragment = new GameFragment();
        }
        MyFragmentManager.replaceFragment(getSupportFragmentManager(), R.id.container, mGameFragment);
        mCurrentFragment = mGameFragment;
    }

    /**
     * 初始化積分領取的 fragment
     */
    private void initIntegralFragment(){
        if (mIntegralFragment == null) {
            mIntegralFragment = new IntegralFragment();
        }
        MyFragmentManager.replaceFragment(getSupportFragmentManager(), R.id.container, mIntegralFragment);
        mCurrentFragment = mIntegralFragment;
    }
}

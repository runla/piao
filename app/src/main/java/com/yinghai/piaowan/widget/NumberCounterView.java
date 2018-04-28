package com.yinghai.piaowan.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yinghai.piaowan.R;


/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/19
 *         Description：购物车中的数量计数器
 * @Param
 */

public class NumberCounterView extends FrameLayout {
    private TextView mTvDecrease;
    private TextView mTvIncrease;
    private EditText mEtAmount;

    private int mDefaultWidth = 210;

    private int mDefaultNum = 0;    //  最开始显示的数量
    private int mMaxNum = -1;       //  最大的计数数量

    private Drawable mBtnBackground;
    private Drawable mEtBackground;
    private int mBtnTextColor;
    private int mEtTextColor;
    private int mBtnTextSize;
    private int mEtTextSize;

    private int mStep = 1;//  步数，每次加减的大小

    public NumberCounterView(@NonNull Context context) {
        this(context, null);
    }

    public NumberCounterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberCounterView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_add_counter, this);
        mTvDecrease = (TextView) findViewById(R.id.tv_decrease);
        mTvIncrease = (TextView) findViewById(R.id.tv_increase);
        mEtAmount = (EditText) findViewById(R.id.et_amount);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberCounterView);
        mBtnBackground = typedArray.getDrawable(R.styleable.NumberCounterView_num_btnBackgroud);
        mDefaultNum = typedArray.getInt(R.styleable.NumberCounterView_num_etDefaultNum,0);
        mStep = typedArray.getInt(R.styleable.NumberCounterView_num_step,1);

        if (mBtnBackground != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mTvDecrease.setBackground(mBtnBackground);
                mTvIncrease.setBackground(mBtnBackground);
            }else{
                mTvIncrease.setBackgroundDrawable(mBtnBackground);
                mTvDecrease.setBackgroundDrawable(mBtnBackground);
            }
        }

        mEtBackground = typedArray.getDrawable(R.styleable.NumberCounterView_num_etBackground);
        if (mEtBackground != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mEtAmount.setBackground(mEtBackground);
            }else{
                mEtAmount.setBackgroundDrawable(mEtBackground);
            }
        }

        mBtnTextSize = typedArray.getDimensionPixelSize(R.styleable.NumberCounterView_num_btnTextSize,0);
        if (mBtnTextSize != 0) {
            mTvDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX,mBtnTextSize);
            mTvIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX,mBtnTextSize);
        }

        mEtTextSize = typedArray.getDimensionPixelSize(R.styleable.NumberCounterView_num_etTextSize,0);
        if (mEtTextSize != 0) {
            mEtAmount.setTextSize(TypedValue.COMPLEX_UNIT_PX,mEtTextSize);
        }

        mBtnTextColor = typedArray.getColor(R.styleable.NumberCounterView_num_btnTextColor, Color.GRAY);
        mTvIncrease.setTextColor(mBtnTextColor);
        mTvDecrease.setTextColor(mBtnTextColor);

        mEtTextColor = typedArray.getColor(R.styleable.NumberCounterView_num_etTextColor, Color.GRAY);
        mEtAmount.setTextColor(mEtTextColor);

        typedArray.recycle();
        init();
        onListenerEvent();
    }

    private void init() {
        mEtAmount.setText(String.valueOf(mDefaultNum));
    }

    private void onListenerEvent() {
        //  减
        mTvDecrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = getCountNum();
                if (count < 0) {
                    mEtAmount.setText(String.valueOf(0));
                    if (mINumChangeCallback != null) {
                        mINumChangeCallback.onNumChangeResult(0);
                    }
                } else {
                    mEtAmount.setText(String.valueOf(count - mStep));
                    if (mINumChangeCallback != null) {
                        mINumChangeCallback.onNumChangeResult(count - mStep);
                    }
                }
            }
        });

        // 加
        mTvIncrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = getCountNum();
                // 不能超过最大计数
                if (mMaxNum != -1 && count > mMaxNum) {
                    mEtAmount.setText(String.valueOf(mMaxNum));
                    if (mINumChangeCallback != null) {
                        mINumChangeCallback.onNumChangeResult(mMaxNum);
                    }
                } else {
                    mEtAmount.setText(String.valueOf(count + mStep));
                    if (mINumChangeCallback != null) {
                        mINumChangeCallback.onNumChangeResult(count + mStep);
                    }
                }
            }
        });

        mEtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mEtAmount.setText(String.valueOf(0));
                    if (mINumChangeCallback != null) {
                        mINumChangeCallback.onNumChangeResult(0);
                    }
                    return;
                }

                // 处理第一个数为 0，如 0122
                String countStr = s.toString().trim();
                if (countStr.startsWith("0") && countStr.length() > 1) {
                    countStr = countStr.replaceFirst("0", "");
                    mEtAmount.setText(countStr);
                    return;
                }

                int count = Integer.parseInt(countStr);
                if (count < 0) {
                    mEtAmount.setText(String.valueOf(0));
                    if (mINumChangeCallback != null) {
                        mINumChangeCallback.onNumChangeResult(0);
                    }
                } else if (mMaxNum != -1 && count > mMaxNum) {
                    mEtAmount.setText(String.valueOf(mMaxNum));
                    if (mINumChangeCallback != null) {
                        mINumChangeCallback.onNumChangeResult(mMaxNum);
                    }
                }
            }
        });
    }

    /**
     * 设置每次加减的数值大小
     * @param mStep
     */
    public void setStep(int mStep) {
        this.mStep = mStep;
    }

    // 设置默认显示的数量
    public void setmDefaultNum(int mDefaultNum) {
        this.mDefaultNum = mDefaultNum;
    }

    /**
     * 获取当前显示的数量
     *
     * @return
     */
    public int getCountNum() {
        if (TextUtils.isEmpty(mEtAmount.getText())) {
            return 0;
        }
        return Integer.parseInt(mEtAmount.getText().toString().trim());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int widthSize = right - left;
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(widthSize * 1 / 4, widthSize * 1 / 4);
        mTvIncrease.setGravity(Gravity.CENTER);
        mTvDecrease.setGravity(Gravity.CENTER);
        mTvIncrease.setLayoutParams(params1);
        mTvDecrease.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(widthSize * 1 / 2-10, widthSize * 1 / 4);
        params2.setMargins(5,0,5,0);
        mEtAmount.setGravity(Gravity.CENTER);
        mEtAmount.setLayoutParams(params2);
        return;
    }

    public void setCurrentNum(int num) {
        mEtAmount.setText(String.valueOf(num));
    }

    public void setMaxNum(int mMaxNum) {
        this.mMaxNum = mMaxNum;
    }

    public void setNumChangeCallback(INumChangeCallback mINumChangeCallback) {
        this.mINumChangeCallback = mINumChangeCallback;
    }

    private INumChangeCallback mINumChangeCallback;
    /**
     * 数字变换接口回调
     */
    public interface INumChangeCallback{
        /**
         * 数值发生改变
         * @param num   当前值
         */
        void onNumChangeResult(int num);
    }
}

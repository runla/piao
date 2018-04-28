package com.yinghai.piaowan;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.MyActivityManager;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.http.ThreadPool.ThreadPoolManager;
import com.example.fansonlib.http.retrofit.RetrofitClient;
import com.example.fansonlib.http.retrofit.RetrofitStrategy;
import com.example.fansonlib.image.ImageLoaderConfig;
import com.example.fansonlib.image.ImageLoaderUtils;
import com.example.fansonlib.image.glide.GlideLoaderStrategy;
import com.example.fansonlib.utils.ImageLoaderProxy;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.utils.notification.MyNotificationUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.yinghai.piaowan.constant.Config;
import com.yinghai.piaowan.http.ApiFactoryImpl;
import com.yinghai.piaowan.http.ApiStores;
import com.yinghai.piaowan.utils.LogUtils;

import java.util.List;

import static android.content.ComponentCallbacks2.TRIM_MEMORY_MODERATE;
import static android.content.ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN;

/**
 * Created by：fanson
 * Created on：2018/4/13 15:20
 * Describe：
 */

public class SampleApplicationLike extends DefaultApplicationLike {

    public static final String TAG = SampleApplicationLike.class.getSimpleName();
    private static SampleApplicationLike mInstance;
    private static Application mApplication;

    public SampleApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
    }

    public static synchronized SampleApplicationLike getInstance() {
        return mInstance;
    }

    public static synchronized Application getMyApplication() {
        return mApplication;
    }

/*   private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher() {
        SampleApplicationLike application = SampleApplicationLike.getInstance();
        return application.refWatcher;
    }*/


    @Override
    public void onCreate() {
        mApplication = getApplication();
        String processName = getProcessName(getMyApplication(), android.os.Process.myPid());
        if (processName != null) {
            boolean defaultProcess = processName.equals(Config.PACKAGE_NAME);
            if (defaultProcess) {
                initApp();
            }
        }
        super.onCreate();
    }

    /**
     * 初始化当前应用
     */
    private void initApp() {
        long startTime = System.currentTimeMillis();
        mInstance = this;
        ThreadPoolManager.getThreadPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
//                Bugly.init(getApplication(), Config.BUGLY_ID, true);
//        Bugly.setIsDevelopmentDevice(getApplication(), true);        //true为开发设备
                AppUtils.init(getMyApplication());
                initImageUtil();
                initLogger();
                initHttp();
                MyNotificationUtils.init(mApplication);
//                CrashRestartHelper.getInstance().init(getMyApplication());
                ImageLoaderProxy.initImageLoader(getApplication());
                MyNotificationUtils.init(getApplication());
//                refWatcher = LeakCanary.install(getMyApplication());
                registerActivityListener();
                initShowToast();
                SharePreferenceHelper.getInstance(getApplication());
                initRongIm();
            }
        });
        LogUtils.d(TAG, "启动时间：" + (System.currentTimeMillis() - startTime));
    }

    private void initShowToast() {
        ShowToast.Config.getInstance().setInfoColor(ContextCompat.getColor(mApplication, R.color.colorPrimary)).apply();
    }


    /**
     * 初始化Logger
     */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag(TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    public void initHttp() {
        RetrofitClient.init(ApiStores.API_SERVER_URL);
        RetrofitStrategy strategy = new RetrofitStrategy();
        strategy.setApi(new ApiFactoryImpl());
        HttpUtils.init(strategy);
    }

    private void initImageUtil() {
        //图片框架使用方式，策略模式
        ImageLoaderConfig loaderConfig = new ImageLoaderConfig.Builder().client(new GlideLoaderStrategy())
                .placePicRes(R.mipmap.default_image)
                .errorPicRes(R.mipmap.default_image)
                .setMaxDiskCache(1024 * 1024 * 50)
                .setMaxMemoryCache(1024 * 1024 * 10)
                .build();
        ImageLoaderUtils.init(loaderConfig);
    }


    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    /**
     * Authorization not used，重启App
     */
    public void retryLaunch() {
        SharePreferenceHelper.clear();
        Intent intent = getApplication().getPackageManager()
                .getLaunchIntentForPackage(getApplication().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }

    private void initRongIm() {
//        RongIM.init(getApplication());
//        // 消息监听
//        RongIM.setOnReceiveMessageListener(new MyReceiveMessageListener());
//        // 连接状态监听
//        RongIM.setConnectionStatusListener(new MyConnectionStatusListener());
//        // 未读消息的监听
//        MyUnReadMessageListener.register();
//
//        //TODO 这个状态判断需要放欢迎页
//        if (SharePreferenceHelper.getBoolean(ConstantPreference.B_USER_LOGIN, false)) {
//            // IM 登录
//            IMLogin.imCheckLogin(new RongIMClient.ConnectCallback() {
//                @Override
//                public void onTokenIncorrect() {
//                    LoginStateManager.getInstance().setState(new LogoutState());
//                }
//
//                @Override
//                public void onSuccess(String s) {
//                    LoginStateManager.getInstance().setState(new LoginState());
//                }
//
//                @Override
//                public void onError(RongIMClient.ErrorCode errorCode) {
//                    LoginStateManager.getInstance().setState(new LogoutState());
//                }
//            });
//        } else {
//            LoginStateManager.getInstance().setState(new LogoutState());
//        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                //进行资源释放的操作
                break;
            case TRIM_MEMORY_MODERATE:
                ImageLoaderUtils.clearMemory(getMyApplication());
                break;
            default:
                break;
        }
    }

    /**
     * 监听Activity的生命周期
     */
    private void registerActivityListener() {
        getMyApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                /**
                 *  监听到 Activity创建事件 将该 Activity 加入list
                 */
                MyActivityManager.getAppManager().addActivity(activity);
                LogUtils.d("Add Activity：" + activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                MyActivityManager.getAppManager().removeActivity(activity);
                LogUtils.d("Remove Activity：" + activity);
            }
        });
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.md_grey_100_color_code, R.color.md_grey_400_color_code);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                BallPulseFooter footer = new BallPulseFooter(context);
                footer.setAnimatingColor(ContextCompat.getColor(context, R.color.primaryBlue));
                footer.setBackgroundColor(ContextCompat.getColor(context, R.color.md_grey_100_color_code));
                return footer;
            }
        });

    }
}

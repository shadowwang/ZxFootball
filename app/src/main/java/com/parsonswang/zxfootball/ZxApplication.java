package com.parsonswang.zxfootball;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.facebook.stetho.Stetho;
import com.glidebitmappool.GlideBitmapPool;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.Platform;
import com.idlefish.flutterboost.Utils;
import com.idlefish.flutterboost.interfaces.INativeRouter;
import com.parsonswang.zxfootball.core.ApplicationContextHolder;
import com.parsonswang.zxfootball.core.CrashReportInit;
import com.parsonswang.zxfootball.core.LogInit;
import com.parsonswang.zxfootball.core.MtaInit;
import com.parsonswang.zxfootball.flutter.PageRouter;
import com.parsonswang.zxfootball.flutter.TextPlatformViewFactory;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Map;

import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.StandardMessageCodec;
import timber.log.Timber;


/**
 * Created by parsonswang on 2017/10/13.
 */

public class ZxApplication extends Application {

    public static Application appInstance;

    public FlutterEngine flutterEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        LogInit.initLog();

        MtaInit.initMta(appInstance);

        CrashReportInit.initCrashReport();

        Stetho.initializeWithDefaults(this);

        CrashReport.initCrashReport(getApplicationContext(), "ce5d70e35c", false);

        GlideBitmapPool.initialize(10 * 1024 * 1024) ;

        initFlutterEngine();

        initFlutterBoost();

        Timber.i("---onCreate---");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Timber.i("---attachBaseContext---");
        ApplicationContextHolder.setContext(base);
    }

    private void initFlutterEngine() {
        flutterEngine = new FlutterEngine(this);
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
    }

    private void initFlutterBoost() {
        INativeRouter router = new INativeRouter() {
            @Override
            public void openContainer(Context context, String url, Map<String, Object> urlParams, int requestCode, Map<String, Object> exts) {
                final String assembleUrl = Utils.assembleUrl(url, urlParams);
                PageRouter.openPageByUrl(context, url, urlParams);
            }
        };

        FlutterBoost.BoostLifecycleListener boostLifecycleListener = new FlutterBoost.BoostLifecycleListener() {
            @Override
            public void beforeCreateEngine() {

            }

            @Override
            public void onEngineCreated() {
                MethodChannel methodChannel = new MethodChannel(FlutterBoost.instance().engineProvider().getDartExecutor(), "flutter_native_channel");
                methodChannel.setMethodCallHandler((call, result) -> {

                    if (call.method.equals("getPlatformVersion")) {
                        result.success(Build.VERSION.RELEASE);
                    } else {
                        result.notImplemented();
                    }

                });

                // 注册PlatformView viewTypeId要和flutter中的viewType对应
                FlutterBoost
                        .instance()
                        .engineProvider()
                        .getPlatformViewsController()
                        .getRegistry()
                        .registerViewFactory("plugins.test/view", new TextPlatformViewFactory(StandardMessageCodec.INSTANCE));
            }

            @Override
            public void onPluginsRegistered() {

            }

            @Override
            public void onEngineDestroy() {

            }
        };

        //
        // AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
        //
        //   <meta-data android:name="flutterEmbedding"
        //               android:value="2">
        //    </meta-data>
        // GeneratedPluginRegistrant 会自动生成 新的插件方式　
        //
        // 插件注册方式请使用
        // FlutterBoost.instance().engineProvider().getPlugins().add(new FlutterPlugin());
        // GeneratedPluginRegistrant.registerWith()，是在engine 创建后马上执行，放射形式调用
        //

        Platform platform= new FlutterBoost
                .ConfigBuilder(this, router)
                .isDebug(true)
                .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
                .renderMode(FlutterView.RenderMode.texture)
                .lifecycleListener(boostLifecycleListener)
                .build();
        FlutterBoost.instance().init(platform);

    }

}

package com.kadadevelopers.ads.sdkdemo.helper;

import static com.kadadevelopers.ads.util.Constant.AD_STATUS_ON;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadadevelopers.ads.sdkdemo.BuildConfig;
import com.kadadevelopers.ads.sdkdemo.R;
import com.kadadevelopers.ads.sdkdemo.data.Constant;
import com.kadadevelopers.ads.sdkdemo.database.SharedPref;
import com.kadadevelopers.ads.appopen.AppOpenAd;
import com.kadadevelopers.ads.banner.BannerAd;
import com.kadadevelopers.ads.gdpr.GDPR;
import com.kadadevelopers.ads.initialization.InitializeAd;
import com.kadadevelopers.ads.interstitial.InterstitialAd;
import com.kadadevelopers.ads.listener.OnInterstitialAdDismissedListener;
import com.kadadevelopers.ads.listener.OnRewardedAdCompleteListener;
import com.kadadevelopers.ads.listener.OnRewardedAdDismissedListener;
import com.kadadevelopers.ads.listener.OnRewardedAdErrorListener;
import com.kadadevelopers.ads.listener.OnRewardedAdLoadedListener;
import com.kadadevelopers.ads.listener.OnShowAdCompleteListener;
import com.kadadevelopers.ads.nativead.NativeAd;
import com.kadadevelopers.ads.nativead.NativeAdView;
import com.kadadevelopers.ads.nativead.NativeAdViewHolder;
import com.kadadevelopers.ads.rewarded.RewardedAd;

public class AdsManager {

    Activity activity;
    SharedPref sharedPref;
    InitializeAd adNetwork;
    GDPR gdpr;
    AppOpenAd appOpenAd;
    BannerAd bannerAd;
    InterstitialAd interstitialAd;
    RewardedAd rewardedAd;
    NativeAd nativeAd;
    NativeAdView nativeAdView;

    public AdsManager(Activity activity) {
        this.activity = activity;
        sharedPref = new SharedPref(activity);
        adNetwork = new InitializeAd(activity);
        gdpr = new GDPR(activity);
        appOpenAd = new AppOpenAd(activity);
        bannerAd = new BannerAd(activity);
        interstitialAd = new InterstitialAd(activity);
        rewardedAd = new RewardedAd(activity);
        nativeAd = new NativeAd(activity);
        nativeAdView = new NativeAdView(activity);
    }

    public void initializeAd() {
        adNetwork.setAdStatus(Constant.AD_STATUS)
                .setAdNetwork(Constant.AD_NETWORK)
                .setBackupAdNetwork(Constant.BACKUP_AD_NETWORK)
                .setAppLovinSdkKey(Constant.APPLOVIN_SDK_KEY)
                .setStartappAppId(Constant.STARTAPP_APP_ID)
                .setUnityGameId(Constant.UNITY_GAME_ID)
                .setWortiseAppId(Constant.WORTISE_APP_ID, "MkzVBvyZ2aU2ESAd2Qd29ydGlzZS1hZHMtc2Rrz")
                .setApplicationId(BuildConfig.APPLICATION_ID)
                .setDebug(BuildConfig.DEBUG)
                .build();
    }

    public void updateGdprConsentStatus() {
        gdpr.updateGDPRConsentStatus(Constant.AD_NETWORK, false, false);
    }

    public void loadAppOpenAds(boolean placement, boolean withListener, OnShowAdCompleteListener onShowAdCompleteListener) {
        if (placement) {
            appOpenAd.setAdStatus(Constant.AD_STATUS)
                    .setAdNetwork(Constant.AD_NETWORK)
                    .setBackupAdNetwork(Constant.BACKUP_AD_NETWORK)
                    .setAdMobAppOpenId(Constant.ADMOB_APP_OPEN_AD_ID)
                    .setAdManagerAppOpenId(Constant.GOOGLE_AD_MANAGER_APP_OPEN_AD_ID)
                    .setApplovinAppOpenId(Constant.APPLOVIN_APP_OPEN_AP_ID)
                    .setPangleAppOpenId(Constant.PANGLE_APP_OPEN_AD_ID)
                    .setWithListener(withListener)
                    .build(onShowAdCompleteListener);
        }
    }

    public void showAppOpenAds(OnShowAdCompleteListener onShowAdCompleteListener) {
        appOpenAd.show(onShowAdCompleteListener);
    }

    public void destroyAppOpenAd() {
        appOpenAd.destroyOpenAd();
    }

    public void loadBannerAd() {
        bannerAd.setAdStatus(Constant.AD_STATUS)
                .setAdNetwork(Constant.AD_NETWORK)
                .setBackupAdNetwork(Constant.BACKUP_AD_NETWORK)
                .setAdMobBannerId(Constant.ADMOB_BANNER_ID)
                .setGoogleAdManagerBannerId(Constant.GOOGLE_AD_MANAGER_BANNER_ID)
                .setFanBannerId(Constant.FAN_BANNER_ID)
                .setUnityBannerId(Constant.UNITY_BANNER_ID)
                .setAppLovinBannerId(Constant.APPLOVIN_BANNER_ID)
                .setAppLovinBannerZoneId(Constant.APPLOVIN_BANNER_ZONE_ID)
                .setWortiseBannerId(Constant.WORTISE_BANNER_ID)
                .setIsCollapsibleBanner(false)
                .setDarkTheme(sharedPref.getIsDarkTheme())
                .build();
    }

    public void destroyBannerAd() {
        bannerAd.destroyAndDetachBanner();
    }

    public void resumeBannerAd() {
        if (Constant.AD_STATUS.equals(AD_STATUS_ON) && !Constant.IRONSOURCE_BANNER_ID.equals("0")) {

        }
    }

    public void loadInterstitialAd(OnInterstitialAdDismissedListener onDismiss) {
        interstitialAd.setAdStatus(Constant.AD_STATUS)
                .setAdNetwork(Constant.AD_NETWORK)
                .setBackupAdNetwork(Constant.BACKUP_AD_NETWORK)
                .setAdMobInterstitialId(Constant.ADMOB_INTERSTITIAL_ID)
                .setGoogleAdManagerInterstitialId(Constant.GOOGLE_AD_MANAGER_INTERSTITIAL_ID)
                .setFanInterstitialId(Constant.FAN_INTERSTITIAL_ID)
                .setUnityInterstitialId(Constant.UNITY_INTERSTITIAL_ID)
                .setAppLovinInterstitialId(Constant.APPLOVIN_INTERSTITIAL_ID)
                .setAppLovinInterstitialZoneId(Constant.APPLOVIN_INTERSTITIAL_ZONE_ID)
                .setWortiseInterstitialId(Constant.WORTISE_INTERSTITIAL_ID)
                .setInterval(Constant.INTERSTITIAL_AD_INTERVAL)
                .build()
                .setWithListener(true, onDismiss);
//                .build(() -> {
//                    Log.d(TAG, "onAdDismissed");
//                });
    }

//    public void loadRewardedAd() {
//        rewardedAd.setAdStatus(Constant.AD_STATUS)
//                .setMainAds(Constant.AD_NETWORK)
//                .setBackupAds(Constant.BACKUP_AD_NETWORK)
//                .setAdMobRewardedId(Constant.ADMOB_REWARDED_ID)
//                .setAdManagerRewardedId(Constant.GOOGLE_AD_MANAGER_REWARDED_ID)
//                .setFanRewardedId(Constant.FAN_REWARDED_ID)
//                .setUnityRewardedId(Constant.UNITY_REWARDED_ID)
//                .setApplovinMaxRewardedId(Constant.APPLOVIN_MAX_REWARDED_ID)
//                .setApplovinDiscRewardedZoneId(Constant.APPLOVIN_DISC_REWARDED_ZONE_ID)
//                .setIronSourceRewardedId(Constant.IRONSOURCE_REWARDED_ID)
//                .setWortiseRewardedId(Constant.WORTISE_REWARDED_ID)
//                .setPangleRewardedId(Constant.PANGLE_REWARDED_ID)
//                .setHuaweiRewardedId(Constant.HUAWEI_REWARDED_ID)
//                .setYandexRewardedId(Constant.YANDEX_REWARDED_ID)
//                .build(new OnRewardedAdCompleteListener() {
//                    @Override
//                    public void onRewardedAdComplete() {
//                        Toast.makeText(activity, "Rewarded complete", Toast.LENGTH_SHORT).show();
//                    }
//                }, new OnRewardedAdDismissedListener() {
//                    @Override
//                    public void onRewardedAdDismissed() {
//
//                    }
//                });
//    }

    public void loadAndShowRewardedAd(OnRewardedAdLoadedListener onLoaded, OnRewardedAdErrorListener onError, OnRewardedAdDismissedListener onDismiss, OnRewardedAdCompleteListener onComplete) {
        rewardedAd.setAdStatus(Constant.AD_STATUS)
                .setMainAds(Constant.AD_NETWORK)
                .setBackupAds(Constant.BACKUP_AD_NETWORK)
                .setAdMobRewardedId(Constant.ADMOB_REWARDED_ID)
                .setAdManagerRewardedId(Constant.GOOGLE_AD_MANAGER_REWARDED_ID)
                .setFanRewardedId(Constant.FAN_REWARDED_ID)
                .setUnityRewardedId(Constant.UNITY_REWARDED_ID)
                .setApplovinMaxRewardedId(Constant.APPLOVIN_MAX_REWARDED_ID)
                .setApplovinDiscRewardedZoneId(Constant.APPLOVIN_DISC_REWARDED_ZONE_ID)
                .setWortiseRewardedId(Constant.WORTISE_REWARDED_ID)
                .build(onLoaded, onError, onDismiss, onComplete);
    }

    public void showRewardedAd() {
        rewardedAd.show(new OnRewardedAdCompleteListener() {
            @Override
            public void onRewardedAdComplete() {
                Toast.makeText(activity, "Rewarded complete", Toast.LENGTH_SHORT).show();
            }
        }, new OnRewardedAdDismissedListener() {
            @Override
            public void onRewardedAdDismissed() {

            }
        }, new OnRewardedAdErrorListener() {
            @Override
            public void onRewardedAdError() {
                Toast.makeText(activity, "Rewarded error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showInterstitialAd() {
        interstitialAd.show();
//        interstitialAd.show(() -> {
//            Log.d(TAG, "onAdShowed");
//        }, () -> {
//            Log.d(TAG, "onAdDismissed");
//        });
    }

    public void loadNativeAd() {
        nativeAd.setAdStatus(Constant.AD_STATUS)
                .setAdNetwork(Constant.AD_NETWORK)
                .setBackupAdNetwork(Constant.BACKUP_AD_NETWORK)
                .setAdMobNativeId(Constant.ADMOB_NATIVE_ID)
                .setAdManagerNativeId(Constant.GOOGLE_AD_MANAGER_NATIVE_ID)
                .setFanNativeId(Constant.FAN_NATIVE_ID)
                .setAppLovinNativeId(Constant.APPLOVIN_NATIVE_MANUAL_ID)
                .setAppLovinDiscoveryMrecZoneId(Constant.APPLOVIN_BANNER_MREC_ZONE_ID)
                .setWortiseNativeId(Constant.WORTISE_NATIVE_ID)
                .setNativeAdStyle(Constant.NATIVE_STYLE)
                .setRadius(R.dimen.corner_radius)
                .setStrokeWidth(R.dimen.native_stroke_width)
                .setStrokeColor(R.color.colorNativeStroke)
                .setBackgroundColor(R.color.colorNativeBackgroundLight, R.color.colorNativeBackgroundDark)
                .setMargin(R.dimen.spacing_medium, R.dimen.spacing_medium, R.dimen.spacing_medium, R.dimen.spacing_medium)
                .setDarkTheme(sharedPref.getIsDarkTheme())
                .build();
    }

//    public void showNativeAdIfAvailable() {
//        nativeAd.showNativeAdIfAvailable();
//    }

    public void loadNativeAdView(View view) {
        nativeAdView.setView(view)
                .setAdStatus(Constant.AD_STATUS)
                .setAdNetwork(Constant.AD_NETWORK)
                .setBackupAdNetwork(Constant.BACKUP_AD_NETWORK)
                .setAdMobNativeId(Constant.ADMOB_NATIVE_ID)
                .setAdManagerNativeId(Constant.GOOGLE_AD_MANAGER_NATIVE_ID)
                .setFanNativeId(Constant.FAN_NATIVE_ID)
                .setAppLovinNativeId(Constant.APPLOVIN_NATIVE_MANUAL_ID)
                .setAppLovinDiscoveryMrecZoneId(Constant.APPLOVIN_BANNER_MREC_ZONE_ID)
                .setWortiseNativeId(Constant.WORTISE_NATIVE_ID)
                .setNativeAdStyle(Constant.NATIVE_STYLE)
                .setDarkTheme(sharedPref.getIsDarkTheme())
                .setRadius(R.dimen.corner_radius)
                .setStrokeWidth(R.dimen.native_stroke_width)
                .setStrokeColor(R.color.colorNativeStroke)
                .setBackgroundColor(R.color.colorNativeBackgroundLight, R.color.colorNativeBackgroundDark)
                .setMargin(R.dimen.spacing_medium, R.dimen.spacing_none, R.dimen.spacing_medium, R.dimen.spacing_none)
                .build();
    }

    public RecyclerView.ViewHolder createNativeAdViewHolder(Context context, @NonNull ViewGroup parent) {
        return new NativeAdViewHolder(NativeAdViewHolder.setLayoutInflater(parent, Constant.NATIVE_STYLE))
                .setAdStatus(Constant.AD_STATUS)
                .setAdNetwork(Constant.AD_NETWORK)
                .setBackupAdNetwork(Constant.BACKUP_AD_NETWORK)
                .setAdMobNativeId(Constant.ADMOB_NATIVE_ID)
                .setAdManagerNativeId(Constant.GOOGLE_AD_MANAGER_NATIVE_ID)
                .setFanNativeId(Constant.FAN_NATIVE_ID)
                .setAppLovinNativeId(Constant.APPLOVIN_NATIVE_MANUAL_ID)
                .setAppLovinDiscoveryMrecZoneId(Constant.APPLOVIN_BANNER_MREC_ZONE_ID)
                .setWortiseNativeId(Constant.WORTISE_NATIVE_ID)
                .setNativeAdStyle(Constant.NATIVE_STYLE)
                .setBackgroundColor(R.color.colorNativeBackgroundLight, R.color.colorNativeBackgroundDark)
                .setRadius(context, R.dimen.corner_radius)
                .setStrokeWidth(context, R.dimen.native_stroke_width)
                .setStrokeColor(context, R.color.colorNativeStroke)
                .setMargin(context, R.dimen.spacing_medium, R.dimen.spacing_medium, R.dimen.spacing_medium, R.dimen.spacing_medium)
                .setDarkTheme(sharedPref.getIsDarkTheme());
    }

    public void bindNativeAdViewHolder(Context context, NativeAdViewHolder holder) {
        holder.buildNativeAd(context);
    }

}

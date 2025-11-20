## kadadevelopers-ads-sdk
Android library for displaying multiple Ad Networks developed by [kadadevelopers](https://kadadevelopers.com/)

### Step 1: Add repositories
Add it in your root `settings.gradle (Project Settings)` at the end of repositories:
```gradle
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
        maven { url 'https://android-sdk.is.com/' }
        maven { url 'https://unity3ddist.jfrog.io/artifactory/unity-mediation-mvn-prod-local/' }
        maven { url 'https://maven.wortise.com/artifactory/public' }
        maven { url 'https://artifact.bytedance.com/repository/pangle' }
        maven { url 'https://cboost.jfrog.io/artifactory/chartboost-ads/' }
        maven { url 'https://maven.ogury.co' }
    }
}
```

### Step 2: Add the dependency
```gradle
dependencies {
    implementation 'com.github.kadadevelopers:advertising-multi-sdk:[version]'
}
```

## Ad Network Sdk options
### advertising-multi-sdk
`advertising-multi-sdk:2.+'` with ad network support `AdMob, Ad Manager, Meta Audience Network, AppLovin Max, AppLovin Discovery, Wortise, Start.io, Unity Ads, wortise`

# ForkMe 

Is a GitHub :octocat: Trending 📈 Repository viewer Android app!

WIP: Will make things dynamically update by 2018.

You can browse trending repositories, star repositories and connect with other devs.

This application was my final project for FIT3027: Android & iOS at Monash University.

It makes use of:

- Atlassian/[Java-Commonmark](https://github.com/atlassian/commonmark-java)
- Square/[Retrofit](https://github.com/square/retrofit)
- Square/[Picasso](https://github.com/square/picasso)
- Google/[Gson](https://github.com/google/gson)
- mikepenz/[Materialdrawer](https://github.com/mikepenz/MaterialDrawer)
- flschweiger/[SwipeStack](https://github.com/flschweiger/SwipeStack)
- SufficientlySecure/[html-textview](https://github.com/SufficientlySecure/html-textview)
- geniushkg/[github-oauth](https://github.com/geniushkg/github-oauth)

## To run this

Make a GitHub application and put your application `Client ID` and `Client Secret` in `dlei.forkme.AppCredential`

```java
package dlei.forkme;

public class AppCredentials {
    public static String clientId = "Put your app id here";
    public static String clientSecret = "Put your app secret here";
}
```

## Screenshots

<div>
 <img src="/Screens/TrendingRepositories_1.png" width="200">
 <img src="/Screens/TrendingRepositories_2.png" width="200">
 <img src="/Screens/TrendingRepositories_3.png" width="200">
 <img src="/Screens/RepositoryView_1.png" width="200">
 <img src="/Screens/RepositoryView_2.png" width="200">
 <img src="/Screens/UserView_1.png" width="200">
 <img src="/Screens/MergeMeView.png" width="200">
 <img src="/Screens/ContactEmail.png" width="200">
</div>

More screen shots can be found in `/Screens`

## Where to now

I will plan on working on this occasionally when I have time and use it as an excuse to learn Kotlin/get better at android :fire:

## Contributing

If you feel like contributing please **Fork Me** :wink: 🎉

Note: This application is not associated with GitHub in any way.

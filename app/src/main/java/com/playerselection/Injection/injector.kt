package com.playerselection.Injection



import com.google.gson.GsonBuilder
import com.playerselection.BuildConfig
import com.playerselection.EndPoint.ApiEndPoint
import com.playerselection.mainActivityModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import org.koin.dsl.module

val viewModelModule = module {
    single<UserRepo> { UserRepository(get()) }
//    viewModel { SampleApiModule(get()) }
    viewModel { mainActivityModule(get()) }
}

val networkModule = module {
    single { provideHttpLogging() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

fun provideHttpLogging(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addNetworkInterceptor(logging)
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    val gson = GsonBuilder().setLenient().create()
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}

//class AddHeaderInterceptor : Interceptor {
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//
//        val builder = chain.request().newBuilder()
//        val apikey: String? = SharedPreferenceManager.getString(API_KEY_VALUE, STATIC_API_KEY)
//
//        val accessToken: String? = SharedPreferenceManager.getString(ACCESS_TOKEN, "")
//        val berearToken: String? = SharedPreferenceManager.getString(BEREAR_TOKEN, "")
//        val userRole: String? =
//            SharedPreferenceManager.getString(USER_ROLE, AppConstants.APP_ROLE_USER)
//        val userID: String? = SharedPreferenceManager.getString(LOGGED_IN_USER_ID, "-1")!!
//
////        if (apikey == STATIC_API_KEY) builder.addHeader(
////            "xapikey",
////            "" + apikey
////        ) else builder.addHeader("apikey", "" + apikey)
////        if (accessToken != "") builder.addHeader("accesstoken", "" + accessToken)
////        if (!userID.equals("-1")) builder.addHeader("userid", "" + userID)
////        if (!userID.equals("-1")) builder.addHeader("userID", "" + userID)
//        builder.addHeader("user_role", userRole.toString())
//        builder.addHeader("Authorization", "Bearer $berearToken").build()
////        builder.addHeader("role", userRole.toString())
////        builder.addHeader("Devicetype", "android")
//        builder.addHeader("device_type", "android")
//        builder.addHeader("local_datetime", getCurrentDate())
//
//
//
//        ShowLogToast.ShowLog("http Apikey -->$apikey")
//        ShowLogToast.ShowLog("http accesstoken -->$accessToken")
//        ShowLogToast.ShowLog("http userID -->$userID")
//        ShowLogToast.ShowLog("http role -->$userRole")
//        ShowLogToast.ShowLog("http userID -->$userID")
//        ShowLogToast.ShowLog("http userID -->$userID")
//        return chain.proceed(builder.build())
//    }
//}

fun provideApiService(retrofit: Retrofit): ApiEndPoint = retrofit.create(ApiEndPoint::class.java)

val appModules = viewModelModule + networkModule
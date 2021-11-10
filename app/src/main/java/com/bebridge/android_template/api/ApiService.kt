package com.bebridge.android_template.api

import android.content.Context
import com.bebridge.android_template.BuildConfig
import com.bebridge.android_template.api.response.ApiDistrict
import com.bebridge.android_template.api.response.ApiFarm
import com.bebridge.android_template.api.response.ApiParish
import com.bebridge.android_template.api.response.ApiStatus
import com.bebridge.android_template.api.response.ApiSubcounty
import com.bebridge.android_template.api.response.ApiToken
import com.bebridge.android_template.api.response.ApiUser
import com.bebridge.android_template.api.response.ApiVillage
import com.bebridge.android_template.util.DebugManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import se.ansman.kotshi.KotshiJsonAdapterFactory
import java.util.concurrent.TimeUnit

class ApiService(private val context: Context) {

  val service: IApiService = create(IApiService::class.java)

  lateinit var retrofit: Retrofit

  fun <S> create(serviceClass: Class<S>): S {

    val moshi = Moshi.Builder()
      .add(ApplicationJsonAdapterFactory.INSTANCE)
      .build()

    //create retrofit
    retrofit = Retrofit.Builder()
      .addConverterFactory(ScalarsConverterFactory.create())
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(BuildConfig.BASE_API_PATH)
      .client(httpBuilder.build())
      .build()

    return retrofit.create(serviceClass)
  }

  private val httpBuilder: OkHttpClient.Builder
    get() {
      //create http client
      val httpClient = OkHttpClient.Builder()
        .addInterceptor(CustomInterceptor(context))
        .readTimeout(30, TimeUnit.SECONDS)

      if (DebugManager.instance.isDebug()) {
        httpClient.addNetworkInterceptor(StethoInterceptor())
          .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
          })
      }

      return httpClient
    }

  interface IApiService {

    @FormUrlEncoded
    @POST("user")
    fun signUp(
      @Field("email") email: String,
      @Field("password") password: String,
      @Field("user_name") userName: String,
      @Field("gender") genderCode: String?,
      @Field("birthday") birthday: String?,
      @Field("language") language: String?,
      @Field("currency_code") currencyCode: String?
    ): Single<ApiStatus>

    @FormUrlEncoded
    @POST("user/token")
    fun loginByEmail(
      @Field("email") email: String?,
      @Field("password") password: String
    ): Single<ApiToken>

    @FormUrlEncoded
    @POST("user/token/facebook")
    fun loginWithFacebook(
      @Field("access_token") accessToken: String?,
      @Field("email") email: String?,
      @Field("language") language: String?,
      @Field("currency_code") currencyCode: String?
    ): Single<ApiToken>

    @FormUrlEncoded
    @POST("user/token/line")
    fun loginWithLine(
      @Field("id_token") idToken: String?,
      @Field("email") email: String?,
      @Field("language") language: String?,
      @Field("currency_code") currencyCode: String?
    ): Single<ApiToken>

    @FormUrlEncoded
    @POST("user/token/apple")
    fun loginWithApple(
      @Field("id_token") idToken: String?,
      @Field("email") email: String?,
      @Field("language") language: String?,
      @Field("currency_code") currencyCode: String?,
      @Field("client_type") clientType: String?
    ): Single<ApiToken>

    @FormUrlEncoded
    @POST("user/token/google")
    fun loginWithGoogle(
      @Field("access_token") accessToken: String?,
      @Field("email") email: String?,
      @Field("language") language: String?,
      @Field("currency_code") currencyCode: String?
    ): Single<ApiToken>

    @FormUrlEncoded
    @PUT("user/password_reset")
    fun changePassword(
      @Field("email") email: String,
      @Field("password") password: String
    ): Single<ApiToken>

    @GET("user")
    fun getUserProfile(): Single<ApiUser.Holder>

    //.............................
    @GET("farms")
    fun getFarm(): Single<List<ApiFarm>>

    @GET("districts")
    fun getDistrict(): Single<List<ApiDistrict>>

    @GET("subcountys")
    fun getSubcounty(): Single<List<ApiSubcounty>>

    @GET("parishs")
    fun getParish(): Single<List<ApiParish>>

    @GET("villages")
    fun getVillage(): Single<List<ApiVillage>>

  }

}

@KotshiJsonAdapterFactory
abstract class ApplicationJsonAdapterFactory : JsonAdapter.Factory {
  companion object {
    val INSTANCE: ApplicationJsonAdapterFactory = KotshiApplicationJsonAdapterFactory
  }
}

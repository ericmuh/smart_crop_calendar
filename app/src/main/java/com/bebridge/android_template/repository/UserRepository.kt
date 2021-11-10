package com.bebridge.android_template.repository

import com.bebridge.android_template.api.ApiService
import com.bebridge.android_template.api.response.ApiStatus
import com.bebridge.android_template.api.response.ApiToken
import com.bebridge.android_template.api.toUser
import com.bebridge.android_template.db.AppDatabase
import com.bebridge.android_template.db.entity.GenderEntity
import com.bebridge.android_template.db.entity.UserEntity
import com.bebridge.android_template.util.PreferenceController
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import retrofit2.http.Part
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val service: ApiService.IApiService,
    private val db: AppDatabase
) {
    private val pc: PreferenceController = PreferenceController.instance

    // from Server
    fun getUserProfile(): Single<UserEntity> {
        return service.getUserProfile()
            .map { it.user.toUser() }
            .doOnSuccess {
                db.userDao().insert(it)
                pc.setEmail(it.email)
                pc.setLoginUserId(it.userId)
            }
    }

    // from DB
    fun fetchUserProfile(): Single<UserEntity> {
        return Single.create {emitter ->
            val userId = pc.getLoginUserId()
            if (userId == null) {
                emitter.onError(Throwable())
            } else {
                emitter.onSuccess(db.userDao().selectById(userId))
            }
        }
    }


    fun signUp(
        email: String,
        password: String,
        userName: String,
        gender: GenderEntity?,
        birthday: String?
    ): Single<ApiStatus> =
        service
            .signUp(
                email,
                password,
                userName,
                gender?.code,
                birthday,
                pc.getLanguageCode(),
                pc.getCurrencyCode()
            )

    fun loginByEmail(email: String, password: String): Single<ApiToken> =
        service
            .loginByEmail(email, password)
            .doOnSuccess {
                pc.setAccessToken(it.token)
                pc.setLoginType(PreferenceController.LOGIN_TYPE_EMAIL)
                pc.setEmail(email)
                pc.setPassword(password)
                pc.setLastLoggedInAt(ZonedDateTime.now(ZoneId.systemDefault()))
            }

    fun changePassword(
        email: String,
        password: String
    ): Single<ApiToken> =
        service.changePassword(email, password)

}
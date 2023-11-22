package com.salekart.di

import com.salekart.data.AppConstants
import com.salekart.data.api.ApiServices
import com.salekart.data.datasource.ProductDataSource
import com.salekart.data.datasource.ProductDataSourceImpl
import com.salekart.ui.repository.ProductRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Synchronized
    private fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpBuilder())
            .build()
    }

    @Provides
    @Singleton
    fun okHttpBuilder(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AppInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .callTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .build()

    private val moshi: Moshi = Moshi.Builder()

        .add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun getNetworkApi() = providesRetrofit().create(ApiServices::class.java)

    @Provides
    @Singleton
    fun provideProductsDataSource(apiServices: ApiServices) : ProductDataSource {
        return ProductDataSourceImpl(apiServices)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productDataSource: ProductDataSource) : ProductRepository {
        return  ProductRepository(productDataSource)
    }

}

class AppInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer") // add shared preference token here
                .addHeader("X-localization", "en")
                .addHeader("Accept", "application/json")
                .build()
            return chain.proceed(request)
        } catch (e: Exception) {
            e.printStackTrace()
            val msg: String = when (e) {
                is SocketTimeoutException -> "Timeout - Please check your internet connection"
                is UnknownHostException -> "Unable to make a connection. Please check your internet"
                is ConnectionShutdownException -> "Connection shutdown. Please check your internet"
                is IOException -> "Server is unreachable, please try again later."
                is IllegalStateException -> "${e.message}"
                else -> "${e.message}"
            }

            return Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .message(msg)
                .body("{${e}}".toResponseBody(null)).build()
        }
    }
}


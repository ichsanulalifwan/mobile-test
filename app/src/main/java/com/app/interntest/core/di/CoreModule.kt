package com.app.interntest.core.di

import androidx.room.Room
import com.app.interntest.core.data.PostRepository
import com.app.interntest.core.data.source.local.LocalDataSource
import com.app.interntest.core.data.source.local.room.PostDatabase
import com.app.interntest.core.data.source.remote.RemoteDataSource
import com.app.interntest.core.data.source.remote.network.ApiService
import com.app.interntest.core.domain.repository.IPostRepository
import com.app.interntest.core.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {

    factory { get<PostDatabase>().postDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            PostDatabase::class.java, "Post.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IPostRepository> {
        PostRepository(
            get(),
            get()
        )
    }
}
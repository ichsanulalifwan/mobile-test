package com.app.interntest.di

import androidx.room.Room
import com.app.interntest.data.PostRepository
import com.app.interntest.data.source.local.LocalDataSource
import com.app.interntest.data.source.local.room.PostDatabase
import com.app.interntest.data.source.remote.RemoteDataSource
import com.app.interntest.data.source.remote.network.ApiService
import com.app.interntest.domain.repository.IPostRepository
import com.app.interntest.domain.usecase.PostInteractor
import com.app.interntest.domain.usecase.PostUseCase
import com.app.interntest.utils.AppExecutors
import com.app.interntest.utils.Constants.URL
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
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IPostRepository> {
        PostRepository(
            get(),
            get(),
            get()
        )
    }
}

val useCaseModule = module {
    factory<PostUseCase> { PostInteractor(get()) }
}

val viewModelModule = module {
}
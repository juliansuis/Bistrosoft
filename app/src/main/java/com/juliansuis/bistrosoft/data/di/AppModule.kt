package com.juliansuis.bistrosoft.data.di

import android.content.Context
import androidx.room.Room
import com.juliansuis.bistrosoft.BuildConfig
import com.juliansuis.bistrosoft.data.dao.LifecycleEventDao
import com.juliansuis.bistrosoft.data.db.AppDatabase
import com.juliansuis.bistrosoft.data.network.ApiService
import com.juliansuis.bistrosoft.data.repository.LifecycleRepositoryImpl
import com.juliansuis.bistrosoft.data.repository.TimeRepositoryImpl
import com.juliansuis.bistrosoft.domain.repository.LifecycleRepository
import com.juliansuis.bistrosoft.domain.repository.TimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.ROOT_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideTimeRepository(apiService: ApiService): TimeRepository =
        TimeRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideLifecycleEventsDao(db: AppDatabase): LifecycleEventDao = db.lifecycleEventDao()

    @Provides
    @Singleton
    fun provideLifecycleRepository(dao: LifecycleEventDao): LifecycleRepository =
        LifecycleRepositoryImpl(dao)
}

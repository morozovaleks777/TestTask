package com.example.testtaskforintellias.di

import android.content.Context
import androidx.room.Room
import com.example.testtaskforbootcamp.data.database.AppDatabase
import com.example.testtaskforbootcamp.data.database.WordListRepositoryImpl1
import com.example.testtaskforbootcamp.data.database.WordResponseListDao
import com.example.testtaskforbootcamp.data.network.WordApi
import com.example.testtaskforbootcamp.domain.WordListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn((SingletonComponent::class))
class AppModule {

    @Singleton
    @Provides
    fun provideWordListDao(appDatabase: AppDatabase): WordResponseListDao =
        appDatabase.wordListDao()

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext
        context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context, AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideWordApi(): WordApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")

            .build().create(WordApi::class.java)


    }

    @Singleton
    @Provides
    fun bindRepository(impl: WordListRepositoryImpl1): WordListRepository {
        return impl
    }

}
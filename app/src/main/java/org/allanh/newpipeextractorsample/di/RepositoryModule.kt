package org.allanh.newpipeextractorsample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.allanh.newpipeextractorsample.data.datasource.YoutubeDataSource
import org.allanh.newpipeextractorsample.data.datasource.YoutubeDataSourceImpl
import org.allanh.newpipeextractorsample.data.repository.YoutubeRepository
import org.allanh.newpipeextractorsample.data.repository.YoutubeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideYoutubeDataSource(): YoutubeDataSource {
        return YoutubeDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideYoutubeRepository(youtubeDataSource: YoutubeDataSource): YoutubeRepository {
        return YoutubeRepositoryImpl(youtubeDataSource)
    }
}

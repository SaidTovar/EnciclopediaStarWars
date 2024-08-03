package com.tovars.enciclopediastarwars.di

import androidx.annotation.Keep
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.tovars.enciclopediastarwars.data.NetworkConfig
import com.tovars.enciclopediastarwars.data.firebaseData.response.FirebaseService
import com.tovars.enciclopediastarwars.data.googleData.GoogleApiService
import com.tovars.enciclopediastarwars.data.googleData.GoogleService
import com.tovars.enciclopediastarwars.data.starwarsData.StarWarsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideStarWarsApiService(okHttpClient: OkHttpClient): StarWarsApiService {

         val retrofit = Retrofit.Builder()
             .baseUrl(NetworkConfig.STAR_WARS_BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .client(okHttpClient)
             .build()

        return retrofit.create(StarWarsApiService::class.java)

    }

    @Provides
    fun provideGoogleApiService(okHttpClient: OkHttpClient): GoogleApiService {

        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkConfig.GOOGLE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(GoogleApiService::class.java)

    }

    @Singleton
    @Provides
    fun provideGoogleService(apiServiceGoogle: GoogleApiService): GoogleService {
        return GoogleService(apiServiceGoogle)
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun providerDatabaseReference(database: FirebaseFirestore): CollectionReference {
        return database.collection("starwars")
    }

    @Singleton
    @Provides
    fun provideFirebaseService(firebaseCollectionReference: CollectionReference): FirebaseService {
        return FirebaseService(firebaseCollectionReference)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

}
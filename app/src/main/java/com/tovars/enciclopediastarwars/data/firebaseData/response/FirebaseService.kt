package com.tovars.enciclopediastarwars.data.firebaseData.response

import androidx.annotation.Keep
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Keep
class FirebaseService @Inject constructor(private val firebaseCollectionReference: CollectionReference) {

    suspend fun getImage(query: String): String {

        return withContext(Dispatchers.IO) {

            firebaseCollectionReference
                .whereEqualTo("name", query)
                .get().await()
                .firstOrNull()?.getString("url") ?: ""

        }
    }

    suspend fun setImage(name: String, url: String) {

        withContext(Dispatchers.IO) {

            firebaseCollectionReference.add(
                hashMapOf(
                    "name" to name,
                    "url" to url,
                    "height" to "0",
                    "width" to "0"
                )
            )

        }

    }

    suspend fun getAllImages(query: String): List<String> {

        return withContext(Dispatchers.IO) {

            val images = mutableListOf<String>()

            firebaseCollectionReference.whereEqualTo("name", query)
                .get()
                .await()
                .forEach {
                    it.getString("url")?.let { it1 ->
                        images.add(it1)
                    }
                }

            images //return

        }

    }

}
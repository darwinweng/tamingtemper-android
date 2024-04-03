package com.example.tamingtemper.repository

import com.example.tamingtemper.common.TamingTemperApp.Companion.context
import com.example.tamingtemper.utils.fromJson
import com.example.tamingtemper.api.JourneyApi
import com.example.tamingtemper.vo.Levels
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JourneyRepo @Inject constructor(): JourneyApi {

    override suspend fun readJsonFile(fileName: String): Result<Levels> = withContext(Dispatchers.IO) {
        try {
            val inputStream: InputStream? = context.assets?.open(fileName)

            if (inputStream != null) {
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val levels = Gson().fromJson<Levels>(jsonString)
                Result.success(levels)
            } else {
                Result.failure(Exception("File not found: $fileName"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
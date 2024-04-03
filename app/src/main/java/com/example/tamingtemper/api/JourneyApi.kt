package com.example.tamingtemper.api

import com.example.tamingtemper.vo.Levels

interface JourneyApi {
    suspend fun readJsonFile(fileName: String): Result<Levels>
}
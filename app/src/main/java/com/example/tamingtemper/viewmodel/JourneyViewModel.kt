package com.example.tamingtemper.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tamingtemper.common.TamingTemperApp.Companion.context
import com.example.tamingtemper.repository.JourneyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JourneyViewModel @Inject constructor(private val repo: JourneyRepo): ViewModel() {
    suspend fun getLevels() = repo.readJsonFile(fileName = "levels.json")

}
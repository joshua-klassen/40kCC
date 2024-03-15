package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Mission
import com.example.a40kcc.data.repository.MissionRepository

class MissionViewModel(missionRepository: MissionRepository) : ViewModel() {
    val allMissions: LiveData<List<Mission>> = missionRepository.allMissions.asLiveData()
}

class MissionViewModelFactory(private val missionRepository: MissionRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MissionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MissionViewModel(missionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
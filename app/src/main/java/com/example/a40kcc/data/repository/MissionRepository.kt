package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.MissionDao
import com.example.a40kcc.data.`object`.Mission
import kotlinx.coroutines.flow.Flow

class MissionRepository(private val missionDao: MissionDao) {
    val allMissions: Flow<List<Mission>> = missionDao.getAll()

    @WorkerThread
    fun getMission(missionId: Int): Flow<Mission> {
        return missionDao.getById(missionId)
    }

    @WorkerThread
    fun getMission(missionName: String): Flow<Mission> {
        return missionDao.getByName(missionName)
    }

    @WorkerThread
    fun getMissionByNickname(missionNickname: String): Flow<Mission> {
        return missionDao.getByNickname(missionNickname)
    }
}
package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.ObjectiveDao
import com.example.a40kcc.data.`object`.Objective
import kotlinx.coroutines.flow.Flow

class ObjectiveRepository(private val objectiveDao: ObjectiveDao) {
    val allObjectives: Flow<List<Objective>> = objectiveDao.getAll()

    @WorkerThread
    fun getObjective(objectiveId: Int): Flow<Objective> {
        return objectiveDao.getById(objectiveId)
    }

    @WorkerThread
    fun getObjective(objectiveName: String): Flow<Objective> {
        return objectiveDao.getByName(objectiveName)
    }

    @WorkerThread
    fun getObjectiveByNickname(objectiveNickname: String): Flow<Objective> {
        return objectiveDao.getByNickname(objectiveNickname)
    }
}
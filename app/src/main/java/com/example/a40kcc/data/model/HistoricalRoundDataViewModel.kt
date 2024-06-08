@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.data.`object`.HistoricalRoundData
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.data.repository.HistoricalRoundDataRepository

class HistoricalRoundDataViewModel(private val historicalRoundDataRepository: HistoricalRoundDataRepository) :
    ViewModel() {
    fun getById(historicalRoundDataId: Int): HistoricalRoundData {
        return historicalRoundDataRepository.getById(historicalRoundDataId)
    }

    fun getByPlayerId(playerName: String): List<HistoricalRoundData> {
        return historicalRoundDataRepository.getByPlayerName(playerName)
    }

    suspend fun insert(historicalRoundData: HistoricalRoundData) {
        historicalRoundDataRepository.insert(historicalRoundData)
    }

    suspend fun insert(
        tournament: Tournament?,
        round: Round?,
        game: Game?,
        player01: Player?,
        player02: Player? = null,
        initialPrediction: Prediction? = null,
        prediction: Prediction? = null,
        outcome: Outcome? = null,
        isComplete: Boolean = false
    ) {
        historicalRoundDataRepository.insert(
            HistoricalRoundData(
                tournament = tournament?.name ?: "",
                round = round?.number ?: -1,
                primaryMission = round?.primaryMissionName ?: "",
                secondaryMission = round?.secondaryMissionName ?: "",
                deployment = round?.deploymentName ?: "",
                player01 = player01?.name ?: "",
                player01Faction = game?.player01FactionName ?: "",
                player01Detachment = game?.player01FactionDetachment ?: "",
                player02 = player02?.name ?: "",
                player02Faction = game?.player02FactionName ?: "",
                player02Detachment = game?.player02FactionDetachment ?: "",
                initialPrediction = initialPrediction?.name ?: "",
                prediction = prediction?.name ?: "",
                player01Points = outcome?.player01Points ?: -1,
                player02Points = outcome?.player02Points ?: -1,
                player01TeamPoints = outcome?.player01TeamPoints ?: -1,
                player02TeamPoints = outcome?.player02TeamPoints ?: -1,
                pointDifferential = outcome?.pointDifferential ?: -1,
                isComplete = isComplete
            )
        )
    }

    suspend fun insert(
        game: GameExpanded,
        prediction: Prediction? = null,
        isComplete: Boolean = false
    ) {
        historicalRoundDataRepository.insert(
            HistoricalRoundData(
                tournament = game.round?.tournament?.name ?: "",
                round = game.round?.round?.number ?: -1,
                primaryMission = game.round?.round?.primaryMissionName ?: "",
                secondaryMission = game.round?.round?.secondaryMissionName ?: "",
                deployment = game.round?.round?.deploymentName ?: "",
                player01 = game.player01?.player?.name ?: "",
                player01Faction = game.game.player01FactionName,
                player01Detachment = game.game.player01FactionDetachment ?: "",
                player02 = game.player02?.player?.name ?: "",
                player02Faction = game.game.player02FactionName,
                player02Detachment = game.game.player02FactionDetachment ?: "",
                initialPrediction = game.prediction?.name ?: "",
                prediction = prediction?.name ?: "",
                player01Points = game.outcome?.player01Points ?: -1,
                player02Points = game.outcome?.player02Points ?: -1,
                player01TeamPoints = game.outcome?.player01TeamPoints ?: -1,
                player02TeamPoints = game.outcome?.player02TeamPoints ?: -1,
                pointDifferential = game.outcome?.pointDifferential ?: -1,
                isComplete = isComplete
            )
        )
    }
}

class HistoricalRoundDataViewModelFactory(private val historicalRoundDataRepository: HistoricalRoundDataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoricalRoundDataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoricalRoundDataViewModel(historicalRoundDataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
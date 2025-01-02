package com.example.amphibians.data

import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.retrofitService

interface AmphibiansRepository{
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository: AmphibiansRepository{
    override suspend fun getAmphibians(): List<Amphibian> = retrofitService.getAmphibians()
}
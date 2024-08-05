package com.example.mychallenge3.data.source.remote

import com.example.mychallenge3.data.source.DoaRemoteDataSource
import com.example.mychallenge3.data.source.remote.network.DoaService
import com.example.mychallenge3.domain.model.Doa
import com.example.mychallenge3.domain.model.DoaItem

class DoaRemoteDataSourceImpl(
    private val doaService: DoaService
) : DoaRemoteDataSource {
    override suspend fun getDoa(): Doa {
        return Doa(
            doaService.getDoa().doaResponse.map{ doaResponseItem ->
                DoaItem(
                    doaResponseItem.id,
                    doaResponseItem.ayat,
                    doaResponseItem.artinya,
                    doaResponseItem.latin,
                    doaResponseItem.doa
                )
            }
        )
    }

    override suspend fun getRandomDoa(): DoaItem {
        return DoaItem(
            doaService.getRandomDoa().id,
            doaService.getRandomDoa().ayat,
            doaService.getRandomDoa().artinya,
            doaService.getRandomDoa().latin,
            doaService.getRandomDoa().doa
        )
    }

}
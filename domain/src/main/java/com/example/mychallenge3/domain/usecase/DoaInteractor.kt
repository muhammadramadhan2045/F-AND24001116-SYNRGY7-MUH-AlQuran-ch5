package com.example.mychallenge3.domain.usecase

import com.example.mychallenge3.domain.repository.IDoaRepository

class DoaInteractor (private val doaRepository: IDoaRepository) : DoaUseCase {
    override suspend fun getDoa() = doaRepository.getDoa()

    override suspend fun getRandomDoa() = doaRepository.getRandomDoa()
}
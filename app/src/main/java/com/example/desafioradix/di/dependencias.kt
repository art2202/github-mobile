package com.example.desafioradix.di

import com.example.desafioradix.api.RestApi
import com.example.desafioradix.api.repository.DetalhesRepository
import com.example.desafioradix.api.repository.InformacoesRepository
import com.example.desafioradix.ui.viewModel.DetalhesViewModel
import com.example.desafioradix.ui.viewModel.InformacoesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val modules = module{
    single{ RestApi() }
    single{ InformacoesRepository( get() ) }
    single{ DetalhesRepository( get() ) }
}

val viewModelModule = module{
    viewModel { InformacoesViewModel( get() ) }
    viewModel { DetalhesViewModel( get() ) }
}

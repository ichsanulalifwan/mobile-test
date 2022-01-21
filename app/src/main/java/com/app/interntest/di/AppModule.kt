package com.app.interntest.di

import com.app.interntest.core.domain.usecase.PostInteractor
import com.app.interntest.core.domain.usecase.PostUseCase
import com.app.interntest.ui.detailpost.DetailPostViewModel
import com.app.interntest.ui.listpost.ListPostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PostUseCase> { PostInteractor(get()) }
}

val viewModelModule = module {
    viewModel { ListPostViewModel(get()) }
    viewModel { DetailPostViewModel(get()) }
}
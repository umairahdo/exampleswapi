package exampleswapi.dagger


import dagger.Component
import exampleswapi.network.StarWarsService
import exampleswapi.viewmodels.CategoryViewModel
import exampleswapi.viewmodels.DetailViewModel
import exampleswapi.viewmodels.SearchViewModel
import javax.inject.Singleton


@Component(modules = [ApiModule::class])
interface ApiComponent {

    @Singleton
    fun inject(service: StarWarsService)

    fun inject(viewModel: CategoryViewModel)

    fun inject(viewModel: DetailViewModel)

    fun inject(viewModel: SearchViewModel)
}

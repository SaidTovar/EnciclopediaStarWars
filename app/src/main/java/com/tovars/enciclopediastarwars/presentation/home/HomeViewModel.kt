package com.tovars.enciclopediastarwars.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.tovars.enciclopediastarwars.data.starwarsData.StarWarsRepository
import com.tovars.enciclopediastarwars.presentation.model.People
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(starWarsRepository: StarWarsRepository) : ViewModel() {

    val listPeople: Flow<PagingData<People>> = starWarsRepository.getPeople()

    fun updatePeople() {


    }

}
package com.sachin.arapp.ui.screens.drill

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sachin.arapp.data.Drill
import com.sachin.arapp.data.DrillRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DrillViewModel @Inject constructor(
    private val repository: DrillRepository
) : ViewModel() {

    private val _drills = MutableStateFlow<List<Drill>>(emptyList())
    val drills: StateFlow<List<Drill>> = _drills.asStateFlow()

    var selectedDrill: Drill? by mutableStateOf(null)
        internal set

    init {
        loadDrills()
    }

    private fun loadDrills() {
        _drills.value = repository.getDrills()
    }

}

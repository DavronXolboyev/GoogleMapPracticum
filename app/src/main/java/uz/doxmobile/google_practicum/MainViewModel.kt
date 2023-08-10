package uz.doxmobile.google_practicum

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import uz.doxmobile.google_practicum.usecase.GetLocationUseCase
import javax.inject.Inject

/**
 *   Davron
 *   10.08.2023  |  четверг  |  9:10
 */

@HiltViewModel
class MainViewModel @Inject constructor(private val getLocationUseCase: GetLocationUseCase) :
    ViewModel() {

    val location = getLocationUseCase.getLastLocation()
        .distinctUntilChanged { old, new -> old?.latitude == new?.latitude && old?.longitude == new?.longitude }

}
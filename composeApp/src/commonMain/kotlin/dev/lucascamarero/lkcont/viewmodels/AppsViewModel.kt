package dev.lucascamarero.lkcont.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dev.lucascamarero.lkcont.models.App

class AppsViewModel : ViewModel() {
    // Lista observable
    private val _appList = mutableStateListOf(
        App("Bbva", "luken@gmx.es", "Troy1312#", "", "https://www.bbva.es/nimbus/signin.html"),
        App("La Caixa", "geriporrua@hotmail.com", "gshghjre#", "njjetet", "https://www.bbva.es/nimbus/signin.html"),
        App("Solo Músicos", "luken@gmx.es", "fsgdggdfhd", "hurnk@hfnsdgsdsgngds7g7ddg9", "https://www.solomusicos.com/loggin")
    )

    // Exponer solo como lista inmutable
    val appList: List<App> get() = _appList

    fun getApps(): List<App> = appList

    fun addApp(app: App) {
        _appList.add(app)
    }
}
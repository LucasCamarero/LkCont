package dev.lucascamarero.lkcont.viewmodels

import androidx.lifecycle.ViewModel
import dev.lucascamarero.lkcont.models.App

class AppsViewModel : ViewModel() {
    private val appList = mutableListOf<App>(
        App("Bbva", "luken@gmx.es", "Troy1312#", "", "https://www.bbva.es/nimbus/signin.html"),
        App("La Caixa", "geriporrua@hotmail.com", "gshghjre#", "njjetet", "https://www.bbva.es/nimbus/signin.html"),
        App("Solo Músicos", "luken@gmx.es", "fsgdggdfhd", "hurnk@hfnsdgsdsgngds7g7ddg9", "https://www.solomusicos.com/loggin")
    )

    fun getApps(): List<App> = appList

    fun addApp(app: App) {
        appList.add(app)
    }
}
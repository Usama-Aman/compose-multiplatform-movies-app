package org.sam.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.koin.core.context.startKoin
import org.sam.app.di.appModules
import org.sam.app.pesentation.home.HomeScreen
import org.sam.app.theme.AppTheme

@Composable
internal fun App() = AppTheme {


    // Registering the modules
    startKoin {
        modules(appModules())
    }

    Navigator(HomeScreen())

}

internal expect fun openUrl(url: String?)
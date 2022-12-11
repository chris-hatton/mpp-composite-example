package org.example

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

/**
 * Entry point for the Desktop Application
 */
fun main() {
    application {
        Window(
            title = "Composite Example App",
            onCloseRequest = ::exitApplication,
        ) {
            MainView(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

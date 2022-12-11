package org.example

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Note: Composable functions in shared code must be `internal` due to
 * current limitations of Compose/iOS on Kotlin/Native.
 */
@Composable
internal fun MainView(
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(top = 64.dp, start = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Composite Example App", style = MaterialTheme.typography.h3)
        Text("Running on ${Platform.name}", style = MaterialTheme.typography.h4)
    }
}

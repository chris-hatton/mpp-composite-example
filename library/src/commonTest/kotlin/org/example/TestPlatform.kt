package org.example

import kotlin.test.assertTrue

class TestPlatform {
    fun testPlatform() {
        assertTrue {
            Platform.name.isNotBlank()
        }
    }
}

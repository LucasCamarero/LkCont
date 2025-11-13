package dev.lucascamarero.lkcont

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
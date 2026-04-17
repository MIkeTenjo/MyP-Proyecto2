plugins {
    // El plugin principal para compilar Kotlin
    kotlin("jvm") version "1.9.22"
    // Plugin para crear un ejecutable
    application
    // Plugin oficial para integrar JavaFX
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    //Librería oficial de SQLite para Java/Kotlin
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")

    //Librería mp3agic para leer las etiquetas ID3v2 de los MP3
    implementation("com.mpatric:mp3agic:0.9.1")

    //Librerías para las pruebas unitarias
    testImplementation(kotlin("test"))
}

// Configuración para la interfaz gráfica y el audio
javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.media") 
}

// Punto de entrada del programa
application {
    mainClass.set("com.modelado.appMusical.proyecto2.AppKt") 
}

// JUnit para las pruebas unitarias
tasks.test {
    useJUnitPlatform()
}
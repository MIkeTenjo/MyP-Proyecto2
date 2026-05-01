package com.modelado.appMusical.proyecto2.model.miner

import java.io.File
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey

/**
 * Clase que representa el extractor de rolas, el cual se encarga de extraer la información
 * (metadatos) de las rolas a partir de sus archivos de audio. Será la clase encargada de 
 * devolver un objeto de tipo [ExtractedRola] que contendrá la información de la rola.
 * 
 * Se usá la librería [jaudiotagger] para la extracción así como la clase [File] de Java 
 * para manejar los archivos que se vayan encontrando.
 */
public class Extractor {
    
    /**
     * Función que extraé los metadatos de un archivo de audio (solo si lo es)
     * Retorna un objeto de tipo [ExtractedRola] con la información extraída, o null
     * si es un archivo corrupto o si no es de audio. 
     */
    public fun extract(file: File): ExtractedRola{
        try {
            val audioFile = AudioFileIO.read(file)
            val tag = audioFile.tag
            val header = audioFile.audioHeader

            // Obtenemos el año de la rola, si no se encuentra se asigna el valor "0"
            val yearString = tag?.getFirst(FieldKey.YEAR) ?: "0"
            val yearInt = yearString.take(4).toIntOrNull()?: 0

            // Obtenemos el número de pista de la rola, si no se encuentra se asigna el valor "0"
            val trackString = tag?.getFirst(FieldKey.TRACK) ?: "0"
            val trackInt = trackString.toIntOrNull()?: 0

            return ExtractedRola(
            path = file.absolutePath,
            performer = tag?.getFirst(FieldKey.ARTIST).takeIf{
                !it.isNullOrBlank()
            } ?: "Artista desconocido",
            title = tag?.getFirst(FieldKey.TITLE).takeIf{
                !it.isNullOrBlank()
            } ?: "Título desconocido",
            album = tag?.getFirst(FieldKey.ALBUM).takeIf{
                !it.isNullOrBlank()
            } ?: "Álbum desconocido",
            date = yearInt,
            genre = tag?.getFirst(FieldKey.GENRE).takeIf{
                !it.isNullOrBlank()
            } ?: "Género desconocido",
            track = trackInt,
            duration = header?.trackLength?.toIntOrNull() ?: 0
            )
        }catch(Exception e) {
            println("Error al extraer la información de la rola ${file.name}: ${e.message}")
            return null   
        }
    }
}
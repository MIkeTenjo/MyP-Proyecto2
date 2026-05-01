package com.modelado.appMusical.proyecto2.model.miner

public class Miner {
    private val extractor: Extractor = Extractor()

    public fun mine(file: java.io.File): ExtractedRola? {
        return extractor.extract(file)
    }
}
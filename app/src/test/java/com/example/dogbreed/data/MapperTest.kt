package com.example.dogbreed.data

import org.junit.Assert.*

import org.junit.Test

class MapperTest {

    @Test
    fun transformToEntity() {
        //Given(dado este valor)
        val unUrlEnString = "www.url"
        val unIdEnString = "unId"

        //When(hago esta acción)
        val resultadoTransformacion = unUrlEnString.transformToEntity(unIdEnString)

        //Then(espero este resultado)
        assertEquals(unIdEnString, resultadoTransformacion.dogBreedDetail)
        assertEquals(unUrlEnString, resultadoTransformacion.url)
    }

    @Test
    fun transformToBreedEntity() {
        //Given(dado este valor)
        val unaBreed = "breed"

        //When(hago esta acción)
        val resultadoTransformacionBreed = unaBreed.transformToBreedEntity()

        //Then(espero este resultado)
        assertEquals(unaBreed, resultadoTransformacionBreed.breed)
    }
}
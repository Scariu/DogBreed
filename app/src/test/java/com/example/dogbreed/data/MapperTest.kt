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
}
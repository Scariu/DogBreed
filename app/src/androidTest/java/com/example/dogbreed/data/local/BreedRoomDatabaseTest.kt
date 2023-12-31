package com.example.dogbreed.data.local

import com.google.common.truth.Truth.assertThat
import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.dogbreed.data.local.list.DogBreedEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class BreedRoomDatabaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var dogBreedsDao: DogBreedDAO
    private lateinit var dogDataBase: DogBreedDataBase

    @Before
    fun setUp() {
        //Fake
        val context = ApplicationProvider.getApplicationContext<Context>()
        dogDataBase =
            Room.inMemoryDatabaseBuilder(context, DogBreedDataBase::class.java).build()
        dogBreedsDao = dogDataBase.getBreedDAO()
    }

    @After
    fun tearDown() {
        dogDataBase.close()
    }

    @Test
    fun insertBreeds_empty() = runBlocking {
        // Given
        val breedList = listOf<DogBreedEntity>()

        // When
        dogBreedsDao.insertDogBreed(breedList)

        // Then A
        val it = dogBreedsDao.getDogBreeds().getOrAwaitValue()

        assertThat(it).isNotNull() // equivalente a ---> assertNotEquals(it, null)
        assertThat(it).isEmpty() // equivalente a ---> assertEquals(it.size, 0)


        // Then B
        dogBreedsDao.getDogBreeds().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertBreeds_happyCase_1element() = runBlocking {
        // Given
        val breedList = listOf(DogBreedEntity("breed1"))

        // When
        dogBreedsDao.insertDogBreed(breedList)

        // Then
        dogBreedsDao.getDogBreeds().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertBreeds_happyCase_3elements() = runBlocking {
        // Given
        val breedList =
            listOf(DogBreedEntity("breed1"), DogBreedEntity("breed2"), DogBreedEntity("breed3"))

        // When
        dogBreedsDao.insertDogBreed(breedList)

        // Then
        dogBreedsDao.getDogBreeds().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        try {
            afterObserve.invoke()

            // Don't wait indefinitely if the LiveData is not set.
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }

        } finally {
            this.removeObserver(observer)
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}

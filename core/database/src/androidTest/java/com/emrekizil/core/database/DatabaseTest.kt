package com.emrekizil.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before

internal abstract class DatabaseTest {
    private lateinit var db:SpaceWatchDatabase
    protected lateinit var spaceWatchDao: SpaceWatchDao

    @Before
    fun setup() {
        db = run {
            val context = ApplicationProvider.getApplicationContext<Context>()
            Room.inMemoryDatabaseBuilder(
                context,
                SpaceWatchDatabase::class.java
            ).build()
        }
        spaceWatchDao = db.spaceWatchDao()
    }


    @After
    fun teardown() = db.close()
}


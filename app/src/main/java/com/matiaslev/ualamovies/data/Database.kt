package com.matiaslev.ualamovies.data

import androidx.room.*

@Database(entities = arrayOf(BookLocal::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}

@Dao
interface BookDao {
    @Query("SELECT * FROM BookLocal")
    fun getAll(): List<BookLocal>

    @Insert
    fun insertAll(books: List<BookLocal>)
}
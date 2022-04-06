package com.example.testtaskforintellias.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DBItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordListDao(): WordResponseListDao


}

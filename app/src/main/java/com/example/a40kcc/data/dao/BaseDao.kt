package com.example.a40kcc.data.dao

import android.database.sqlite.SQLiteException
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun insert(obj: T): Long

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun insert(vararg obj: T): Array<Long>

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun update(obj: T): Int

    /**
     * Update an array of objects from the database.
     *
     * @param obj the objects to be updated
     */
    @Update(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun update(vararg obj: T): Int

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    @Throws(SQLiteException::class)
    suspend fun delete(obj: T): Int

    /**
     * Delete an array of objects from the database
     *
     * @param obj the objects to be deleted
     */
    @Delete
    @Throws(SQLiteException::class)
    suspend fun delete(vararg obj: T): Int
}
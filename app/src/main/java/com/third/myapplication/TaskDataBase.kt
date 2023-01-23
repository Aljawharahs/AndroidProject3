package com.third.myapplication
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

    class TaskDataBase (
        context: Context
    ) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            val initTable = "CREATE TABLE $TABLE_NAME " +
                    "($COLUMN_TASK_NAME TEXT, " +
                    "$COLUMN_COMPLETED TEXT)"
            db.execSQL(initTable)
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
            db.execSQL(dropTable)
            onCreate(db)
        }

        fun insertUser(task_name :String, completed :String) {
            val db = writableDatabase
            val values = ContentValues()
            values.put(COLUMN_TASK_NAME, task_name)
            values.put(COLUMN_COMPLETED, completed)
            db.insert(TABLE_NAME, null, values)
        }

        fun getUser(task_name: String): TaskModel? {
            val db = readableDatabase
            val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_TASK_NAME = '$task_name'"
            val cursor = db.rawQuery(query, null)
            var taskinfo: TaskModel? = null

            if (cursor.moveToFirst()) {
                val task_name = cursor.getString(cursor.getColumnIndex(COLUMN_TASK_NAME))
                val completed = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED))
                taskinfo = TaskModel( task_name,completed)
            }
            return taskinfo
        }

        fun getUsers(): List<TaskModel> {
            val db = readableDatabase
            val task = arrayListOf<TaskModel>()
            val query = "SELECT * FROM $TABLE_NAME"

            try {
                val cursor = db.rawQuery(query, null)

                while (cursor.moveToNext()) {
                    val task_name = cursor.getString(cursor.getColumnIndex(COLUMN_TASK_NAME))
                    val completed = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED))
                    val taskModel = TaskModel(task_name,completed)
                    task.add(taskModel)
                }

            } catch (exception: SQLiteException) {
                Log.d("exception", "getUsers: ${exception.message}")
            }
            return task
        }

        fun changeUser(newtask: String, oldtask: String) {
            val db = writableDatabase
            val values = ContentValues()
            val where = "$COLUMN_TASK_NAME = '$oldtask'"

            values.put(COLUMN_TASK_NAME, newtask)
            db.update(TABLE_NAME, values, where, null)
        }

        fun removeUser(task_name: String) {
            val db = writableDatabase
            val where = "$COLUMN_TASK_NAME = $task_name"
            db.delete(TABLE_NAME, where, null)
        }

        companion object {
            // Database Info
            const val DATABASE_NAME = "user_database"
            const val DATABASE_VERSION = 1
            // Table Info
            const val TABLE_NAME = "user_table"
            const val COLUMN_TASK_NAME= "task_name"
            const val COLUMN_COMPLETED = "completed"
        }
    }
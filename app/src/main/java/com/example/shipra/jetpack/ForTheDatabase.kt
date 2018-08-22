package com.example.shipra.jetpack

import android.content.Context
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import org.jetbrains.anko.db.ConflictClause


@Entity
data class Person(@PrimaryKey(autoGenerate = true)var id:Long?,
                  @ColumnInfo(name="name")var name:String,
                  @ColumnInfo(name="age")var age:Int){

    constructor():this(null,"",0)
}

@Dao

interface personDataDao{

    @Query("SELECT * FROM person")
     fun getAll():List<Person>

    @Insert(onConflict = REPLACE)
    fun insertData(person: Person)
}

@Database(entities = [Person::class],version = 1)
abstract class PersonDatabase:RoomDatabase(){

    abstract fun personDao():personDataDao

    companion object {
        private var INSTANCE: PersonDatabase? = null

        fun getInstance(c: Context): PersonDatabase? {
            if (INSTANCE == null) {

                INSTANCE=Room.databaseBuilder(c.applicationContext,PersonDatabase::class.java,"person.DB").build()
            }
            return INSTANCE
        }

        fun destroyInstance(){

            INSTANCE = null

        }

    }
}





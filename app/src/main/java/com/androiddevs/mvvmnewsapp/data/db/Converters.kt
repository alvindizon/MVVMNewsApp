package com.androiddevs.mvvmnewsapp.data.db

import androidx.room.TypeConverter
import com.androiddevs.mvvmnewsapp.data.api.model.Source


class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String = source.name

    @TypeConverter
    fun toSource(name: String): Source = Source(name, name)
}

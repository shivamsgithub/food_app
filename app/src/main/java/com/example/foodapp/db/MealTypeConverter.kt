package com.example.foodapp.db

import androidx.resourceinspection.annotation.Attribute
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromAnyToString (attribute: Any?): String{
        if (attribute == null)
            return ""
            return attribute as String

        //if (attribute == null)
        //            return ""
        //            return attribute as String
    }

    @TypeConverter
    fun v(attribute: String?): Any{
        if (attribute == null)
            return ""
        return attribute
    }
}
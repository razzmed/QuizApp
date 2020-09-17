package com.example.quizapp.db.converter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.example.quizapp.model.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class DateConverter {

   @TypeConverter
    public static Long toRaw(@Nullable Date date) {
       return date.getTime();
   }

   @TypeConverter
   public static Date fromRaw(@Nullable Long timestamp) {
       if (timestamp == null) return null;

       return new Date(timestamp);
   }

}

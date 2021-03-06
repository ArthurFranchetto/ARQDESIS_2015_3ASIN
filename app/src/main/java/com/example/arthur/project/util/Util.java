package com.example.arthur.project.util;

/**
 * Created by Arthur on 13/09/2015.
 */
import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.example.arthur.project.R;

import java.lang.reflect.Field;

/**
 * Created by Arthur on 9/7/15.
 */
public class Util {
    public static Drawable getDrawable(Activity context, String drawableName){
        //procurar a imagem por meio de reflection
        Class<?> c = R.drawable.class;
        try {
            Field idField = c.getDeclaredField(drawableName);
            int id = idField.getInt(idField);
            return context.getResources().getDrawable(id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package pl.coderslab.utils;

import com.google.common.base.CaseFormat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntityFactory<T> {

    private Class<T> type;

    public EntityFactory(Class<T> type) {
        this.type = type;
    }

    public T getEntity(ResultSet set) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        T instance = null;
        if(set.next()) instance = build(set);
        return instance;
    }

    public List<T> getAsList(ResultSet set) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        while (set.next()){
            list.add(build(set));
        }
        return list;
    }

    private T build(ResultSet set) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        T instance = type.getConstructor().newInstance();
        for (Field field : type.getDeclaredFields()) {
            type.getMethod(fieldToSetter(field.getName()), field.getType()).invoke(
                    instance,
                    set.getObject(camelToUnderscore(field.getName()), field.getType()));
        }
        return instance;
    }

    private String camelToUnderscore(String name) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
    }

    private String fieldToSetter(String fieldName) {
        char firstChar = fieldName.charAt(0);
        return "set" + fieldName.replaceFirst(String.valueOf(firstChar), String.valueOf(Character.toUpperCase(firstChar)));
    }
}

package org.example;

import org.example.annotations.Column;
import org.example.annotations.Table;
import org.example.cars.Car;
import org.example.db.DataBase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBase dataBase = new DataBase();
        dataBase.connect("carsales.db");
        List<String[]> table = new ArrayList<>();
        Class carClass = Car.class;
        Field[] fields = carClass.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Column.class)) {
                Column column = f.getAnnotation(Column.class);
                table.add(new String[]{f.getName(), column.type()});
            }
        }
//        dataBase.createTable("cars", table, carClass);
//        dataBase.deleteTable("cars");
        dataBase.request("INSERT INTO cars (brand, model, manufactureYear, price) VALUES ('Suzuki', 'Jimny', 2007, 600000);");
//        dataBase.deleteID("cars", "3");
        dataBase.disconnect();
    }
}
package homework2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.String");
        Method[] methods = clazz.getMethods();
        int count = 1;
        for (Method meth:methods) {
            System.out.printf("Метод №%d: %s\n", count, meth);
            count++;
        }
        String s = "Hello";
        Class<?> strCls = s.getClass();
        Field[] fields = strCls.getFields();
        count = 1;
        for (Field fld:fields) {
            System.out.printf("Поле №%d: %s\n", count, fld);
            count++;
        }
    }
}

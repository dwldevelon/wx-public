package dwl.utils;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * TODO
 *
 * @author cherrytang
 * @version 1.0
 * @date 2018/12/27
 */
@Deprecated
public class ReflectTool {

    public static final BiPredicate<Class,Class> IS_ASSIGNABLE_FROM_PREDICATE = (superClass, subClass)->superClass.isAssignableFrom(subClass);

    public static final List<Class> INT_CLZ_LIST = Arrays.asList(Integer.class,int.class);
    public static final List<Class> LONG_CLZ_LIST = Arrays.asList(Long.class,long.class);
    public static final List<Class> DOUBLE_CLZ_LIST = Arrays.asList(Double.class,double.class);
    public static final List<Class> FLOAT_CLZ_LIST = Arrays.asList(Float.class,float.class);
    public static final List<Class> SHORT_CLZ_LIST = Arrays.asList(Short.class,short.class);
    public static final List<Class> BYTE_CLZ_LIST = Arrays.asList(Byte.class,byte.class);
    public static final List<Class> CHAR_CLZ_LIST = Arrays.asList(Character.class,char.class);
    public static final List<Class> BOOLEAN_CLZ_LIST = Arrays.asList(Boolean.class,boolean.class);
    public static final List<Class> STRING_CLZ_LIST = Collections.singletonList(String.class);
    public static final List<Class> ENUM_CLZ_LIST = Collections.singletonList(Enum.class);
    public static final List<Class> DATE_CLZ_LIST = Collections.singletonList(Date.class);
    
    

    private ReflectTool(){
        throw new Error("no instance");
    }

    public static boolean setFieldValue(Object obj,String fieldName,Object fieldValue) throws NoSuchFieldException {
        if(obj == null || fieldName== null || fieldValue == null){
            return false;
        }
        Class clazz = obj.getClass();
        Field field = getField(clazz,fieldName);
        if(field == null){
            return false;
        }

        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Method writeMethod = pd.getWriteMethod();
            writeMethod.invoke(obj,fieldValue);
        } catch (IntrospectionException e) {
            String writeMethodName = getWriteMethodName(field);
            Method declaredMethod;
            try {
                declaredMethod = clazz.getDeclaredMethod(writeMethodName, field.getType());
                declaredMethod.invoke(obj,fieldValue);
            } catch (NoSuchMethodException |IllegalAccessException |InvocationTargetException e1) {
                throw new Error(e1);
            }
        }catch (IllegalAccessException | InvocationTargetException e){
            throw new Error(e);
        }
        return true;
    }

    private static String getWriteMethodName(Field field){
        List<Class> booleanList = Arrays.asList(Boolean.class,boolean.class);
        if(field == null){
            return null;
        }
        String fieldName = field.getName();
        String first = fieldName.substring(0, 1).toUpperCase();
        final Predicate<Class> predicate = clz->clz.isAssignableFrom(field.getType());
        String prefix = "set";
        return prefix + first +fieldName.substring(1);
    }
    private static String getReadMethodName(Field field){
        List<Class> booleanList = Arrays.asList(Boolean.class,boolean.class);
        if(field == null){
            return null;
        }
        String fieldName = field.getName();
        String first = fieldName.substring(0, 1).toUpperCase();
        final Predicate<Class> predicate = clz->clz.isAssignableFrom(field.getType());

        String prefix = booleanList.stream().anyMatch(predicate)
                ? "is" : "get";
        return prefix + first +fieldName.substring(1);
    }




    public static void main(String[] args) {

    }


    public static Field getField(Class clazz , String fieldName) throws NoSuchFieldException {
        Class cls = clazz;
        Field field = null;
        NoSuchFieldException noSuchFieldException = null;
        do{
            try {
                field = cls.getDeclaredField(fieldName);
            }catch (NoSuchFieldException e){
                noSuchFieldException = e;
                cls = cls.getSuperclass();
            }
        }while (!cls.isAssignableFrom(Object.class) && field == null);
        if(field == null && noSuchFieldException != null){
            throw noSuchFieldException;
        }
        return field;
    }


    public static boolean isEnum(Class clazz){
        return clazz != null && Enum.class.isAssignableFrom(clazz);
    }





}

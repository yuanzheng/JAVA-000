

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.lang.reflect.Method;

public class ClassloaderTest extends ClassLoader {
    

    public static void main(String[] args) {

        try {
            Object hello = new ClassloaderTest().findClass("Hello").newInstance();
            Method md = hello.getClass().getMethod("hello");
            md.invoke(hello);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = decode();
        return defineClass(name, bytes, 0, bytes.length);
    }

    public byte[] decode() {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(new File("./Hello.xlass").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0; i<bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }

        return bytes;
    }

}

package utils;

import java.io.*;

public class ConversorObjetoParaArrayDeBytes {

    /**
     * Converte um Objeto para um array de bytes
     * @param object
     * @return byte[]
     */
    public static byte[] convertObjectToByteArray(Object object) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            byteArrayOutputStream.close();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    /**
     * Converte um array de bytes para um objeto
     * @param bytes
     * @return object
     */
    public static Object convertByteArrayToObject(byte[] bytes) {
        Object object = null;

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }

}

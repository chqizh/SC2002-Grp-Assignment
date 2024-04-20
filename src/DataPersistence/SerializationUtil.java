package DataPersistence;
import java.io.*;

public class SerializationUtil{
    public static void serialize(Object obj, String fileName) throws IOException {
        try (
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(obj);
            oos.close();
        }
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return ois.readObject();
        }
    }
}
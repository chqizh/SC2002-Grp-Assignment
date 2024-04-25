package DataPersistence;

import java.io.*;

/**
 * A utility class for serializing and deserializing objects.
 */
public class SerializationUtil {

    /**
     * Serializes an object to a file.
     *
     * @param obj      The object to serialize.
     * @param fileName The name of the file to which the object will be serialized.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void serialize(Object obj, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        }
    }

    /**
     * Deserializes an object from a file.
     *
     * @param fileName The name of the file from which to deserialize the object.
     * @return The deserialized object.
     * @throws IOException            If an I/O error occurs while reading from the file.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        }
    }
}

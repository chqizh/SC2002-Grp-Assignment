package serializationtest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements Serializable{
    private Animal animal;

    InMemoryDatabase(){
        animal = new Animal();
    }

    public int getTestTable() {
        return animal.test;
    }
}
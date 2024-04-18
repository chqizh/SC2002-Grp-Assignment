package serializationtest;

import java.io.Serializable;

public class InMemoryDatabase implements Serializable{
    private Animal animal;

    InMemoryDatabase(){
        animal = new Animal();
    }

    public int getTestTable() {
        return animal.test;
    }
}
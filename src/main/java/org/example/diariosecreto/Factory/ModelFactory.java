package org.example.diariosecreto.Factory;

public class ModelFactory {

    private static ModelFactory modelFactory;

    private ModelFactory() {

    }

    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

}

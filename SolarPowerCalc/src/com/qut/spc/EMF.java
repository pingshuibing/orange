package com.qut.spc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Entity manager factory
 * 
 * @author QuocViet
 * @see https://developers.google.com/appengine/docs/java/datastore/jpa/overview
 */
public final class EMF {
    private static final EntityManagerFactory instance =
        Persistence.createEntityManagerFactory("transactions-optional");

    private EMF() {}

    public static EntityManagerFactory get() {
        return instance;
    }
}
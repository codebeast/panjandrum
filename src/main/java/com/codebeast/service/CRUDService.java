package com.codebeast.service;


import com.codebeast.exceptions.NoDuplicateException;

public abstract class CRUDService<T> {


    abstract boolean alreadyExists(final T object) throws NoDuplicateException;

    abstract Reposi

    T create(final T object) throws NoDuplicateException {
        final boolean alreadyExists = alreadyExists(object);
        if (alreadyExists) {
            throw noDuplicateException(object);
        }

        return object;
    }


    NoDuplicateException noDuplicateException(T object) {
        return new NoDuplicateException(object.getClass() + " duplicate found");
    }

}

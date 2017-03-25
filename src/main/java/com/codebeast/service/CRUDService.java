package com.codebeast.service;


import com.codebeast.exceptions.NoDuplicateException;
import org.springframework.data.repository.CrudRepository;

public abstract class CRUDService<T> {


    protected abstract boolean alreadyExists(final T object) throws NoDuplicateException;

    protected abstract CrudRepository<T, Long> getRepository();

    public T create(final T object) throws NoDuplicateException {
        final boolean alreadyExists = alreadyExists(object);
        if (alreadyExists) {
            throw noDuplicateException(object);
        }
        return getRepository().save(object);
    }

    private NoDuplicateException noDuplicateException(T object) {
        return new NoDuplicateException(object.getClass() + " duplicate found");
    }

}

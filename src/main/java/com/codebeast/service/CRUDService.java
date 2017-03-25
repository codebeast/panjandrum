package com.codebeast.service;


import com.codebeast.exceptions.NoDuplicatesAllowedException;
import org.springframework.data.repository.CrudRepository;

public abstract class CRUDService<T> {


    protected abstract boolean alreadyExists(final T object) throws NoDuplicatesAllowedException;

    protected abstract CrudRepository<T, Long> getRepository();

    public T create(final T object) throws NoDuplicatesAllowedException {
        final boolean alreadyExists = alreadyExists(object);
        if (alreadyExists) {
            throw new NoDuplicatesAllowedException();
        }
        return getRepository().save(object);
    }

}

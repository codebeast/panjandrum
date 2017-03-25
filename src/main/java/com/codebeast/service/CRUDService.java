package com.codebeast.service;


@FunctionalInterface
public interface CRUDService<T> {
    boolean alreadyExists(final T object);
}

package com.salesianostriana.dam.AepApp.errors.exceptions;

import com.salesianostriana.dam.AepApp.models.Club;

import javax.persistence.EntityNotFoundException;

public class ListEntityNotFoundException extends EntityNotFoundException {

    public ListEntityNotFoundException (Class clazz) {
        super(String.format("No se puede encontrar elementos del tipo %s ", clazz.getName()));
    }
}

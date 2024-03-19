package com.plataforma.portafolios.service;

import com.plataforma.portafolios.exceptions.EntityNotFoundException;

public interface IGenericService<E,K>{
    E getEntity(K k) throws EntityNotFoundException;
    void saveEntity(E e);
    void deleteEntity(K k) throws EntityNotFoundException;
    void editEntity(E e) throws EntityNotFoundException;

}

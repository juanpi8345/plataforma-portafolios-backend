package com.plataforma.portafolios.util;

public interface IGenericCrud<E,K>{

    E getEntity(K k);
    void saveEntity(E e);
    void deleteEntity(K k);
    void editEntity(E e);

}

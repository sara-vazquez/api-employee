package dev.sara.implementations;

import java.util.List;

public interface IGenericService<T, S> {
    
    public List<T> getEntities();

    public T storeEntity(S dto);

    public List<T> getEntitiesSortedByDate();
}